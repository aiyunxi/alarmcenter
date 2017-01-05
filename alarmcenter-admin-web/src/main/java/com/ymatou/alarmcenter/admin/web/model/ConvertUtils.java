package com.ymatou.alarmcenter.admin.web.model;

import com.ymatou.alarmcenter.domain.model.AppBaseConfig;
import com.ymatou.alarmcenter.domain.model.AppErrorConfig;
import org.joda.time.DateTime;

/**
 * Created by zhangxiaoming on 2017/1/4.
 */
public class ConvertUtils {
    public static ConfigModel toConfigModel(AppBaseConfig appBaseConfig, AppErrorConfig appErrorConfig) {
        ConfigModel configModel = new ConfigModel();
        if (appBaseConfig != null) {
            configModel.setAppId(appBaseConfig.getAppId());
            configModel.setEmails(appBaseConfig.getEmailTo());
            configModel.setMobiles(appBaseConfig.getPhoneNumber());
        }
        if (appErrorConfig != null) {
            configModel.setAppId(appErrorConfig.getAppId());
            configModel.setSendEmailTimeInterval(appErrorConfig.getSendEmailTimeInterval());
            configModel.setSendEmailNumLimit(appErrorConfig.getSendEmailNumLimit());
            configModel.setSendSmsTimeInterval(appErrorConfig.getSendSmsTimeInterval());
            configModel.setSendSmsNumLimit(appErrorConfig.getSendSmsNumLimit());
        }
        return configModel;
    }

    public static void toAppBaseConfigAndAppErrorConfig(ConfigModel model, AppBaseConfig appBaseConfig, AppErrorConfig appErrorConfig) {
        appBaseConfig.setAppId(model.getAppId());
        appBaseConfig.setEmailTo(model.getEmails());
        appBaseConfig.setPhoneNumber(model.getMobiles());
        appBaseConfig.setLastUpdateDatetime(new DateTime().toDate());
        appErrorConfig.setAppId(model.getAppId());
        appErrorConfig.setSendSmsNumLimit(model.getSendSmsNumLimit());
        appErrorConfig.setSendSmsTimeInterval(model.getSendSmsTimeInterval());
        appErrorConfig.setSendEmailNumLimit(model.getSendEmailNumLimit());
        appErrorConfig.setSendEmailTimeInterval(model.getSendEmailTimeInterval());
    }
}
