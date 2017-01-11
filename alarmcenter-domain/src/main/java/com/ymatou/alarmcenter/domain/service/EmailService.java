package com.ymatou.alarmcenter.domain.service;


import com.ymatou.alarmcenter.domain.config.EmailConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/**
 * Created by zhangxiaoming on 2016/11/24.
 */
@Service
public class EmailService implements InitializingBean {
    protected static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    @Resource
    private EmailConfig emailConfig;

    public void sendHtmlMail(String toAddress, String title, String text) {
        if (StringUtils.isBlank(toAddress) || StringUtils.isBlank(title) || StringUtils.isBlank(text))
            return;
        String[] tos = StringUtils.split(toAddress, ";");
        sendHtmlMail(tos, title, text);
    }

    /**
     * 发送html邮件
     *
     * @param to
     * @param title
     * @param text
     * @throws AddressException
     * @throws MessagingException
     */
    public void sendHtmlMail(String[] to, String title, String text) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            String nick = MimeUtility.encodeText("报警系统");
            InternetAddress[] toArray = new InternetAddress[to.length];
            for (int i = 0; i < to.length; i++) {
                toArray[i] = new InternetAddress(to[i]);
            }

            messageHelper.setFrom(new InternetAddress(String.format("%s<%s>", nick, emailConfig.getFromMailAddress())));
            messageHelper.setTo(toArray);
            messageHelper.setSubject(title);
            messageHelper.setText(text, true);
            mimeMessage = messageHelper.getMimeMessage();
            mailSender.send(mimeMessage);

        } catch (Exception ex) {
            StringBuilder sb = new StringBuilder();
            sb.append("mailto:");
            sb.append(StringUtils.join(to, ";"));
            sb.append(ex.getMessage());
            logger.error(sb.toString(), ex);
            //Throwables.propagate(ex);
        }

    }


    /**
     * 设置邮箱host，用户名和密码
     */
    @Override
    public void afterPropertiesSet() throws Exception {

        mailSender.setHost(emailConfig.getSmtpHost());
        mailSender.setUsername(emailConfig.getFromMailAddress());
        mailSender.setPassword(emailConfig.getFromMailPassword());
    }


}
