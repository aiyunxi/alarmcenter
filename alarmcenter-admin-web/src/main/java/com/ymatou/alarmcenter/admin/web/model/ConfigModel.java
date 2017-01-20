package com.ymatou.alarmcenter.admin.web.model;

/**
 * Created by zhangxiaoming on 2016/12/23.
 */
public class ConfigModel {
    private String appId;
    private String emails;
    private String mobiles;
    private String whitelist;
    private int sendEmailTimeInterval;
    private int sendEmailNumLimit;
    private int sendSmsTimeInterval;
    private int sendSmsNumLimit;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getSendEmailTimeInterval() {
        return sendEmailTimeInterval;
    }

    public void setSendEmailTimeInterval(int sendEmailTimeInterval) {
        this.sendEmailTimeInterval = sendEmailTimeInterval;
    }

    public int getSendEmailNumLimit() {
        return sendEmailNumLimit;
    }

    public void setSendEmailNumLimit(int sendEmailNumLimit) {
        this.sendEmailNumLimit = sendEmailNumLimit;
    }

    public int getSendSmsTimeInterval() {
        return sendSmsTimeInterval;
    }

    public void setSendSmsTimeInterval(int sendSmsTimeInterval) {
        this.sendSmsTimeInterval = sendSmsTimeInterval;
    }

    public int getSendSmsNumLimit() {
        return sendSmsNumLimit;
    }

    public void setSendSmsNumLimit(int sendSmsNumLimit) {
        this.sendSmsNumLimit = sendSmsNumLimit;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getMobiles() {
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles;
    }

    public String getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(String whitelist) {
        this.whitelist = whitelist;
    }
}
