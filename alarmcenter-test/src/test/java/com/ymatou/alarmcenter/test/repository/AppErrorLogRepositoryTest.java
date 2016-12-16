package com.ymatou.alarmcenter.test.repository;

import com.ymatou.alarmcenter.domain.model.AppErrorLog;
import com.ymatou.alarmcenter.domain.repository.AppErrorLogRepository;
import com.ymatou.alarmcenter.domain.service.ErrorLogService;
import com.ymatou.alarmcenter.facade.enums.AppErrorLevel;
import com.ymatou.alarmcenter.test.BaseTest;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

import static com.ymatou.alarmcenter.infrastructure.common.Utils.getTimeStamp;

/**
 * Created by zhangxiaoming on 2016/11/28.
 */
public class AppErrorLogRepositoryTest extends BaseTest {
    @Resource
    private AppErrorLogRepository appErrorLogRepository;
    @Resource
    private ErrorLogService errorLogService;

    @Test
    public void test1() {
        AppErrorLog appErrorLog = new AppErrorLog();
        appErrorLog.setMessage("test-" + UUID.randomUUID().toString());
        appErrorLog.setAppId("Test");
        appErrorLogRepository.saveAppErrLog(appErrorLog);
    }

    @Test
    public void test2() {
        int count = 10;
        DateTime dt = new DateTime();
        String appId = UUID.randomUUID().toString();
        for (int i = 0; i < count; i++) {
            AppErrorLog appErrorLog = new AppErrorLog();
            appErrorLog.setMessage("test-" + UUID.randomUUID().toString());
            appErrorLog.setAppId(appId);
            appErrorLog.setTitle("test");
            appErrorLog.setErrorLevel(AppErrorLevel.Error.getCode());
            appErrorLog.setAddTime(dt.toString("yyyy-MM-dd HH:mm:ss"));
            appErrorLog.setRecordTimeStamp(getTimeStamp(dt));
            appErrorLogRepository.saveAppErrLog(appErrorLog);
            //System.out.println(String.format("id:%s,timestamp:%s", appErrorLog.getId(), appErrorLog.getAddTimeStamp()));
        }
        String dbName = appErrorLogRepository.getDatabaseName(dt.toDate());
        String collectionName = appErrorLogRepository.getCollectionName(dt.toDate());
        long errorCount = appErrorLogRepository.getErrorCount(dbName, collectionName, appId, AppErrorLevel.Error.getCode(), dt.minusMinutes(1).toDate(), dt.toDate());
        Assert.assertEquals(count, errorCount);

        List<AppErrorLog> list = appErrorLogRepository.getErrorList(dbName, collectionName, appId, AppErrorLevel.Error.getCode(), dt.minusMinutes(1).toDate(), dt.toDate());
        Assert.assertNotNull(list);
        Assert.assertEquals(count, list.size());
        errorLogService.sendEmail(appId, "zhangxiaoming@ymatou.com", errorCount, AppErrorLevel.Error, dt.minusMinutes(1).toDate(), dt.toDate());
        errorLogService.sendSms("Test", "123456789,787874558", errorCount, AppErrorLevel.Error, dt.minusMinutes(1).toDate(), dt.toDate());

    }
}
