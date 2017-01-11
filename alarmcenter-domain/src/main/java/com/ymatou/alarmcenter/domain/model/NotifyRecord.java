package com.ymatou.alarmcenter.domain.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

/**
 * Created by zhangxiaoming on 2016/11/23.
 */
@Entity(value = "NotifyRecord", noClassnameStored = true)//兼容之前的错误单词
public class NotifyRecord {
    @Id
    private ObjectId id;
    private String appId;
    private Date addTime;
    private Integer notifyType;
    private String nofityTo;
    private String content;
    private Integer notifyStatus;
    private String message;

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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(Integer notifyType) {
        this.notifyType = notifyType;
    }

    public String getNofityTo() {
        return nofityTo;
    }

    public void setNofityTo(String nofityTo) {
        this.nofityTo = nofityTo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNotifyStatus() {
        return notifyStatus;
    }

    public void setNotifyStatus(Integer notifyStatus) {
        this.notifyStatus = notifyStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
