package com.ymatou.alarmcenter.test.service;

import com.ymatou.alarmcenter.domain.service.SmsService;
import com.ymatou.alarmcenter.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by zhangxiaoming on 2016/11/24.
 */

public class SmsServiceTest extends BaseTest {
    @Resource
    private SmsService smsService;

    @Test
    public void test1() {
        smsService.SendMessage("Test", "15821097501", "test中文");
    }
}
