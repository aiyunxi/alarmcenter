package com.ymatou.alarmcenter.domain.service;

import com.ymatou.alarmcenter.domain.config.BusinessConfig;
import com.ymatou.alarmcenter.domain.config.WhitelistConfig;
import com.ymatou.alarmcenter.domain.model.AppBaseConfig;
import com.ymatou.alarmcenter.domain.model.AppErrorConfig;
import com.ymatou.alarmcenter.domain.model.AppErrorLog;
import com.ymatou.alarmcenter.domain.model.NotifyRecord;
import com.ymatou.alarmcenter.domain.repository.AppBaseConfigRepository;
import com.ymatou.alarmcenter.domain.repository.AppErrorConfigRepository;
import com.ymatou.alarmcenter.domain.repository.AppErrorLogRepository;
import com.ymatou.alarmcenter.domain.repository.NotifyRecordRepository;
import com.ymatou.alarmcenter.facade.enums.AppErrorLevel;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangxiaoming on 2016/11/23.
 */
@Service
public class ErrorLogService {
    @Resource
    private AppErrorLogRepository appErrLogRepository;
    @Resource
    private AppBaseConfigRepository appBaseConfigRepository;
    @Resource
    private AppErrorConfigRepository appErrorConfigRepository;
    @Resource
    private NotifyRecordRepository notifyRecordRepository;

    @Resource
    private BusinessConfig businessConfig;
    @Resource
    private EmailService emailService;
    @Resource
    private SmsService smsService;

    @Resource
    private WhitelistConfig whitelistConfig;
    private static final Logger logger = LoggerFactory.getLogger(ErrorLogService.class);


    public void saveAppErrLog(AppErrorLog appErrLog) {
        if (appErrLog == null)
            return;
        boolean flag = isInWhitelist(appErrLog.getAppId(), appErrLog.getTitle(), appErrLog.getMessage(),
                appErrLog.getStackTrace(), appErrLog.getExceptionName());
        if (flag)
            return;
        appErrLogRepository.saveAppErrLog(appErrLog);
    }

    private boolean isInWhitelist(String appId, String title, String message, String stackTrace, String exceptionName) {
        try {
            String content = title + message + stackTrace + exceptionName;
            if (StringUtils.isBlank(appId) || StringUtils.isBlank(content)){
                return false;
            }
//            String words = whitelistConfig.getValue(appId);
            AppBaseConfig appBaseConfig = appBaseConfigRepository.getAppBaseConfigByAppId(appId);

            if (appBaseConfig == null || StringUtils.isBlank(appBaseConfig.getWhitelist())){
                return false;
            }
            String[] array = StringUtils.split(appBaseConfig.getWhitelist(), "[,]");
            if (array == null || array.length <= 0){
                return false;
            }
            for (int i = 0; i < array.length; i++) {
                String word = array[i];
                if (StringUtils.isBlank(word)){
                    continue;
                }
                if (StringUtils.containsIgnoreCase(content,word)){
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return false;
        }

    }

    public long getAppErrorCount(String appId, int errorLevel, Date beginTime, Date endTime) {
        if (StringUtils.isBlank(appId))
            return 0;
        String dbName = appErrLogRepository.getDatabaseName(endTime);
        String collectionName = appErrLogRepository.getCollectionName(endTime);
        return appErrLogRepository.getErrorCount(dbName, collectionName, appId, errorLevel, beginTime, endTime);
    }

    public void errorHandler() {
        List<AppErrorConfig> list = appErrorConfigRepository.getAppErrorConfigList();
        if (list == null || list.size() <= 0)
            return;
        for (AppErrorConfig appErrorConfig : list) {
            errorHandler(appErrorConfig);
        }
    }

    private void errorHandler(AppErrorConfig appErrorConfig) {
        if (appErrorConfig == null)
            return;
        appErrorConfig.setAppId(appErrorConfig.getAppId().toLowerCase());
        AppBaseConfig appBaseConfig = appBaseConfigRepository.getAppBaseConfigByAppId(appErrorConfig.getAppId());
        if (appBaseConfig == null)
            return;
        appBaseConfig.setAppId(appBaseConfig.getAppId().toLowerCase());
        smsHandlerForError(appBaseConfig, appErrorConfig);
        emailHandlerForError(appBaseConfig, appErrorConfig);
        fatalHandler(appBaseConfig, appErrorConfig);
    }


    private void fatalHandler(AppBaseConfig appBaseConfig, AppErrorConfig appErrorConfig) {
        if (appBaseConfig == null || appErrorConfig == null)
            return;
        DateTime beginTime = new DateTime().minusMinutes(1);
        DateTime endTime = new DateTime();
        long fatalCount = getAppErrorCount(appBaseConfig.getAppId(), AppErrorLevel.Fatal.getCode(), beginTime.toDate(), endTime.toDate());
        if (fatalCount <= 0)
            return;
        sendEmail(appBaseConfig.getAppId(), appBaseConfig.getEmailTo(), fatalCount, AppErrorLevel.Fatal, beginTime.toDate(), endTime.toDate());
        sendSms(appBaseConfig.getAppId(), appBaseConfig.getPhoneNumber(), fatalCount, AppErrorLevel.Fatal, beginTime.toDate(), endTime.toDate());
    }


    private void smsHandlerForError(AppBaseConfig appBaseConfig, AppErrorConfig appErrorConfig) {
        if (appBaseConfig == null || appErrorConfig == null)
            return;
        int sendSmsTimeInterval = appErrorConfig.getSendSmsTimeInterval() <= 0 ? businessConfig.getDefaultSendSmsNumLimit() : appErrorConfig.getSendSmsTimeInterval();
        int intSendSmsNumLimit = appErrorConfig.getSendSmsNumLimit() <= 0 ? businessConfig.getDefaultSendSmsNumLimit() : appErrorConfig.getSendSmsNumLimit();
        DateTime currentTime = new DateTime();
        if (appErrorConfig.getLastSmsHandleTime() == null)
            appErrorConfig.setLastSmsHandleTime(new DateTime(1900, 1, 1, 0, 0, 0).toDate());
        DateTime lastSmsHandleTime = new DateTime(appErrorConfig.getLastSmsHandleTime());
        if (currentTime.isAfter(lastSmsHandleTime.plusMinutes(sendSmsTimeInterval))) {
            //处理发送短信逻辑
            DateTime beginTime = currentTime.minusMinutes(sendSmsTimeInterval);
            DateTime endTime = currentTime;
            long errorCount = getAppErrorCount(appBaseConfig.getAppId(), AppErrorLevel.Error.getCode(), beginTime.toDate(), endTime.toDate());
            if (errorCount >= intSendSmsNumLimit) {
                sendSms(appBaseConfig.getAppId(), appBaseConfig.getPhoneNumber(), errorCount, AppErrorLevel.Error, beginTime.toDate(), endTime.toDate());
                appErrorConfigRepository.updateLastSmsHandleTime(appBaseConfig.getAppId());
                logger.info(String.format("appId:%s,send sms!", appBaseConfig.getAppId()));
            } else {
                logger.debug(String.format("appId:%s,send sms!,当前异常数 %s 小于设置值 %s，不发送！", appBaseConfig.getAppId(), errorCount
                        , intSendSmsNumLimit));
            }
        } else {
            logger.debug(String.format("appId:%s,send sms!,当前时间小于发送间隔，不发送！", appBaseConfig.getAppId()));
        }
    }


    private void emailHandlerForError(AppBaseConfig appBaseConfig, AppErrorConfig appErrorConfig) {
        if (appBaseConfig == null || appErrorConfig == null)
            return;
        int intSendEmailTimeInterval = appErrorConfig.getSendEmailTimeInterval() <= 0 ? businessConfig.getDefaultSendEmailTimeInterval() : appErrorConfig.getSendEmailTimeInterval();
        int intSendEmailNumLimit = appErrorConfig.getSendEmailNumLimit() <= 0 ? businessConfig.getDefaultSendEmailNumLimit() : appErrorConfig.getSendEmailNumLimit();
        DateTime currentTime = new DateTime();
        if (appErrorConfig.getLastEmailHandleTime() == null)
            appErrorConfig.setLastEmailHandleTime(new DateTime(1900, 1, 1, 0, 0, 0).toDate());
        DateTime lastEmailHandleTime = new DateTime(appErrorConfig.getLastEmailHandleTime());
        if (currentTime.isAfter(lastEmailHandleTime.plusMinutes(intSendEmailTimeInterval))) {
            //处理Email发送逻辑
            DateTime beginTime = currentTime.minusMinutes(intSendEmailTimeInterval);
            DateTime endTime = currentTime;
            long errorCount = getAppErrorCount(appBaseConfig.getAppId(), AppErrorLevel.Error.getCode(), beginTime.toDate(), endTime.toDate());
            if (errorCount >= intSendEmailNumLimit) {
                sendEmail(appBaseConfig.getAppId(), appBaseConfig.getEmailTo(), errorCount, AppErrorLevel.Error, beginTime.toDate(), endTime.toDate());
                appErrorConfigRepository.updateLastEmailHandleTime(appBaseConfig.getAppId());
                logger.info(String.format("appId:%s,send email!", appBaseConfig.getAppId()));
            } else {
                logger.debug(String.format("appId:%s,send email!,当前异常数 %s 小于设置值 %s，不发送！", appBaseConfig.getAppId(), errorCount
                        , intSendEmailNumLimit));
            }
        } else {
            logger.debug(String.format("appId:%s,send email!,当前时间小于发送间隔，不发送！", appBaseConfig.getAppId()));
        }
    }

    @Async
    public void sendSms(String appId, String sendSmsAddress, long errorCount, AppErrorLevel appErrorLevel, Date beginTime, Date endTime) {
        if (StringUtils.isBlank(appId) || StringUtils.isBlank(sendSmsAddress) || errorCount <= 0)
            return;
        StringBuilder sbTitle = new StringBuilder();
        Exception exception = null;
        try {
            sendSmsAddress = sendSmsAddress.replace(";", ",");
            DateTime dt = new DateTime(endTime);
            sbTitle.append(businessConfig.getDevName()).append(" ").append(appId).append(" 在").append(dt.toString("yyyy-MM-dd HH:mm"));
            sbTitle.append("出现").append(appErrorLevel.getMessage()).append("错误").append(errorCount);
            sbTitle.append("个，请查看邮件--错误告警系统");
            sendSmsAddress = sendSmsAddress.replace(";", ",");
            smsService.SendMessage("alarm.iapi.ymatou.com", sendSmsAddress, sbTitle.toString());
        } catch (Exception ex) {
            exception = ex;
            logger.error("sendSmsError", ex);
        } finally {
            saveNotifyRecordInfo(appId, sbTitle.toString(), sendSmsAddress, 1, exception);
        }
    }

    @Async
    public void sendEmail(String appId, String sendMailAddress, long errorCount, AppErrorLevel appErrorLevel, Date beginTime, Date endTime) {
        if (StringUtils.isBlank(appId) || StringUtils.isBlank((sendMailAddress)))
            return;
        sendMailAddress = sendMailAddress.replace(",", ";");
        StringBuilder sbTitle = new StringBuilder();
        StringBuilder sbBody = new StringBuilder();
        Exception exception = null;
        try {
            DateTime dt = new DateTime(endTime);
            sbTitle.append(businessConfig.getDevName()).append(" ").append(appId).append(" ").append(" 在").append(dt.toString("yyyy-MM-dd HH:mm"));
            sbTitle.append("<br/>产生").append(errorCount).append("个").append(appErrorLevel.getMessage()).append("<br/>");
            sbBody.append(sbTitle.toString());
            String dbName = appErrLogRepository.getDatabaseName(endTime);
            String collectionName = appErrLogRepository.getCollectionName(endTime);
            List<AppErrorLog> list = appErrLogRepository.getErrorList(dbName, collectionName, appId, appErrorLevel.getCode(), beginTime, endTime);
            if (list == null || list.size() <= 0)
                return;

            for (AppErrorLog appErrorLog : list) {
                if (appErrorLog == null || StringUtils.isBlank(appErrorLog.getTitle()))
                    continue;
                String title = appErrorLog.getTitle();
                if (appErrorLog.getTitle().length() > 300) {
                    title = StringUtils.substring(appErrorLog.getTitle(), 0, 300);
                }
                title = title.replace("<br/>", "");
                sbBody.append(appErrorLog.getAddTime());
                sbBody.append(":<a href='");
                String url = businessConfig.getErrorManagerCenterUrl().replace("{0}", appErrorLog.getId().toHexString());
                url = url.replace("{1}", appErrorLog.getAddTimeToDateTime().toString("yyyy-MM-dd"));
                sbBody.append(url);
                sbBody.append("'>");
                sbBody.append(title);
                sbBody.append("</a><br/>");
            }
            sbBody.append("<br />需要连接公司vpn才可访问，邮件中最多显示100条记录。");
            emailService.sendHtmlMail(sendMailAddress, sbTitle.toString().replace("<br/>", ""), sbBody.toString());
        } catch (Exception ex) {
            exception = ex;
            logger.error("sendEmailError", ex);
        } finally {
            saveNotifyRecordInfo(appId, sbTitle.toString(), sendMailAddress, 2, exception);
        }
    }

    private void saveNotifyRecordInfo(String appId, String content, String notifyTo, int notifyType, Exception ex) {
        NotifyRecord notifyRecord = new NotifyRecord();
        notifyRecord.setAppId(appId);
        notifyRecord.setContent(content);
        notifyRecord.setNofityTo(notifyTo);
        notifyRecord.setNotifyType(notifyType);
        if (ex != null) {
            notifyRecord.setNotifyStatus(2);
        } else {
            notifyRecord.setNotifyStatus(1);
        }
        if (notifyType == 1)//sms
        {
            notifyRecord.setMessage(String.format("发送短信：%s : %s : %s", notifyTo, content, ex == null ? "" : ex.getStackTrace()));
        } else if (notifyType == 2) {//email
            notifyRecord.setMessage(String.format("发送Email：%s : %s : %s", notifyTo, content, ex == null ? "" : ex.getStackTrace()));
        }
        notifyRecord.setAddTime(new Date());
        notifyRecordRepository.saveNotifyRecord(notifyRecord);
    }
}
