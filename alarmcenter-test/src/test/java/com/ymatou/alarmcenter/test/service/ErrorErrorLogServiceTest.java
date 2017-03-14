package com.ymatou.alarmcenter.test.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.ymatou.alarmcenter.domain.model.AppErrorLog;
import com.ymatou.alarmcenter.domain.service.ErrorLogService;
import com.ymatou.alarmcenter.test.BaseTest;

/**
 * Created by zhangxiaoming on 2016/11/25.
 */
public class ErrorErrorLogServiceTest extends BaseTest {
    @Resource
    private ErrorLogService errorLogService;

    @Test
    public void test1() {
        errorLogService.errorHandler();
    }


    @Test
    public void test2() {
        AppErrorLog appErrorLog = new AppErrorLog();
        appErrorLog.setAppId("test1.iapi.ymatou.com");
        appErrorLog.setExceptionName("NullPointerException");
        appErrorLog.setErrorLevel(2);
        appErrorLog.setTitle("BargainGroup: BizStatus IllegalArgument. req:BizVo:{\"bizId\":\"3469_4757853407494592656\",\"bizStatus\":-3,\"bizSubType\":1,\"bizType\":1,\"orderId\":121296179}, db:BizVo:{\"bizId\":\"3469_4757853407494592656\",\"bizStatus\":-3,\"bizSubType\":1,\"bizType\":1,\"orderId\":121296179}");
        appErrorLog.setMessage("occur IllegalArgumentException");
        errorLogService.saveAppErrLog(appErrorLog);
    }



}
