package com.ymatou.alarmcenter.domain.config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import org.springframework.stereotype.Component;

/**
 * Created by zhangxiaoming on 2016/11/24.
 */
@Component
@DisconfFile(
        fileName = "email.properties"
)
public class EmailConfig {
    private String smtpHost;
    private String fromMailAddress;
    private String fromMailPassword;

    public EmailConfig() {

    }

    @DisconfFileItem(
            name = "smtpHost"
    )
    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    @DisconfFileItem(
            name = "fromMailAddress"
    )
    public String getFromMailAddress() {
        return fromMailAddress;
    }

    public void setFromMailAddress(String fromMailAddress) {
        this.fromMailAddress = fromMailAddress;
    }

    @DisconfFileItem(
            name = "fromMailPassword"
    )
    public String getFromMailPassword() {
        return fromMailPassword;
    }

    public void setFromMailPassword(String fromMailPassword) {
        this.fromMailPassword = fromMailPassword;
    }
}
