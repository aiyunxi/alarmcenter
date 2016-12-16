package com.ymatou.alarmcenter.test.service;

import com.ymatou.alarmcenter.domain.service.ErrorLogService;
import com.ymatou.alarmcenter.test.BaseTest;
import org.joda.time.DateTime;
import org.junit.Test;

import javax.annotation.Resource;

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



}
