package com.ymatou.alarmcenter.domain.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.Date;

/**
 * Created by zhangxiaoming on 2016/11/24.
 */
@Entity(value = "AppErrorConfig",noClassnameStored = true)
//@Indexes(
//        @Index(value = "AppId", fields = @Field("AppId"), unique = true, background = true)
//)
public class AppErrorConfig {
    @Id
    private ObjectId id;

    //@Indexed(name = "AppId", unique = true)
    @Property("AppId")
    private String appId;
    @Property("LastUpdateDatetime")
    private Date lastUpdateDatetime;
    @Property("SendEmailTimeInterval")
    private int sendEmailTimeInterval;
    @Property("SendEmailNumLimit")
    private int sendEmailNumLimit;
    @Property("SendSmsTimeInterval")
    private int sendSmsTimeInterval;
    @Property("SendSmsNumLimit")
    private int sendSmsNumLimit;
    @Property("LastEmailHandleTime")
    private Date lastEmailHandleTime;
    @Property("LastSmsHandleTime")
    private Date lastSmsHandleTime;

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

    public Date getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    public void setLastUpdateDatetime(Date lastUpdateDatetime) {
        this.lastUpdateDatetime = lastUpdateDatetime;
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

    public Date getLastEmailHandleTime() {
        return lastEmailHandleTime;
    }

    public void setLastEmailHandleTime(Date lastEmailHandleTime) {
        this.lastEmailHandleTime = lastEmailHandleTime;
    }

    public Date getLastSmsHandleTime() {
        return lastSmsHandleTime;
    }

    public void setLastSmsHandleTime(Date lastSmsHandleTime) {
        this.lastSmsHandleTime = lastSmsHandleTime;
    }
}
