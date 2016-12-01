package com.ymatou.alarmcenter.test.service;

import com.ymatou.alarmcenter.domain.service.EmailService;
import com.ymatou.alarmcenter.test.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * Created by zhangxiaoming on 2016/11/24.
 */
public class EmailServiceTest extends BaseTest {
    @Resource
    private EmailService emailService;
//    @Resource
//    private SmsService smsService;

//    @Resource
//    private AppErrLogService appErrLogService;

//    @Resource
//    private ErrorCountService errorCountService;

    //    @Autowired
//    private JavaMailSender mailSender;
    @Test
    public void test1() throws MessagingException, UnsupportedEncodingException {
        String[] to = StringUtils.split("zhangxiaoming@ymatou.com", ",");

        emailService.sendHtmlMail(to,"test", "test!!");
    }
}
