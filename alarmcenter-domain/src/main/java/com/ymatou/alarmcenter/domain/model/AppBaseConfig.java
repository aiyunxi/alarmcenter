package com.ymatou.alarmcenter.domain.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

/**
 * Created by zhangxiaoming on 2016/11/23.
 */
@Entity(value = "AppBaseConfig")
public class AppBaseConfig {
    @Id
    private ObjectId id;
    //@Indexed(name = "AppId", unique = true)
    @Property("AppId")
    private String appId;
    @Property("EmailTo")
    private String emailTo;
    @Property("PhoneNumber")
    private String phoneNumber;
    @Property("LastUpdateDatetime")
    private String lastUpdateDatetime;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    public void setLastUpdateDatetime(String lastUpdateDatetime) {
        this.lastUpdateDatetime = lastUpdateDatetime;
    }
}
