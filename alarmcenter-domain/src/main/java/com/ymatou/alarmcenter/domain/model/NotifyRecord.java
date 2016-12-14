package com.ymatou.alarmcenter.domain.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * Created by zhangxiaoming on 2016/11/23.
 */
@Entity(value = "NofityRecord", noClassnameStored = true)//兼容之前的错误单词
public class NotifyRecord {
    @Id
    private ObjectId id;

    //@Indexed(name = "AppId")
    @Property("AppId")
    private String appId;
    @Property("RecordTime")
    private Long recordTime;
    @Property("RecordShowTime")
    private String recordShowTime;
    @Property("NotifyType")
    private Integer notifyType;
    @Property("NofityTo")
    private String nofityTo;
    @Property("Content")
    private String content;
    @Property("RecordStatus")
    private Integer recordStatus;
    @Property("RecordMsg")
    private String recordMsg;

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

    public Long getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Long recordTime) {
        this.recordTime = recordTime;
    }

    public String getRecordShowTime() {
        return recordShowTime;
    }

    public void setRecordShowTime(String recordShowTime) {
        this.recordShowTime = recordShowTime;
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

    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getRecordMsg() {
        return recordMsg;
    }

    public void setRecordMsg(String recordMsg) {
        this.recordMsg = recordMsg;
    }
}
