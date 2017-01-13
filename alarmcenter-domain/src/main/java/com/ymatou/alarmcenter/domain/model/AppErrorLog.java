package com.ymatou.alarmcenter.domain.model;

import com.ymatou.library.datetimeparse.DateTimeParse;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.util.Date;

import static com.ymatou.alarmcenter.infrastructure.common.Utils.getTimeStamp;

/**
 * Created by zhangxiaoming on 2016/11/23.
 */
//@Entity(value = "AppErrLog", noClassnameStored = true)
//@Indexes({
//        @Index(fields = @Field("AppId"), options = @IndexOptions(background = true))
//})
@Entity(value = "AppErrLog", noClassnameStored = true)
public class AppErrorLog {
    @Id
    private ObjectId id;

    //@Indexed(name = "AppId", background = true)
    @Property("AppId")
    private String appId;
    @Property("ReqUrl")
    private String reqUrl;
    @Property("ReqForm")
    private String reqForm;

    @Property("ErrorLevel")
    private int errorLevel;//AppErrorLevel enum
    @Property("MethodName")
    private String methodName;
    @Property("AssemblyName")
    private String assemblyName;
    @Property("Title")
    private String title;
    @Property("Message")
    private String message;

    //@Indexed(name = "ExceptionName", background = true)
    @Property("ExceptionName")
    private String exceptionName;
    @Property("Header")
    private String header;
    @Property("StackTrace")
    private String stackTrace;
    @Property("AddTime")
    private String addTime;
    @Property("MachineIp")
    private String machineIp;
    @Property("AddTimeStamp")
    private long addTimeStamp;
    @Property("RecordTimeStamp")
    private long recordTimeStamp;
    @Property("IdForShow")
    private String idForShow;


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

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    public String getReqForm() {
        return reqForm;
    }

    public void setReqForm(String reqForm) {
        this.reqForm = reqForm;
    }

    public int getErrorLevel() {
        return errorLevel;
    }

    public void setErrorLevel(int errorLevel) {
        this.errorLevel = errorLevel;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getAssemblyName() {
        return assemblyName;
    }

    public void setAssemblyName(String assemblyName) {
        this.assemblyName = assemblyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
        DateTime dt = getAddTimeToDateTime();
        this.addTimeStamp = getTimeStamp(dt);
        this.addTime = dt.toString("yyyy-MM-dd HH:mm:ss");
    }

    public String getMachineIp() {
        return machineIp;
    }

    public void setMachineIp(String machineIp) {
        this.machineIp = machineIp;
    }

    public long getAddTimeStamp() {
        return addTimeStamp;
    }

    public void setAddTimeStamp(long addTimeStamp) {
        this.addTimeStamp = addTimeStamp;
    }

    public long getRecordTimeStamp() {
        return recordTimeStamp;
    }

    public void setRecordTimeStamp(long recordTimeStamp) {
        this.recordTimeStamp = recordTimeStamp;
    }

    public String getIdForShow() {
        return idForShow;
    }

    public void setIdForShow(String idForShow) {
        this.idForShow = idForShow;
    }

    public DateTime getAddTimeToDateTime() {
        // DateTime dt = new DateTime();
//        try {
//            dt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(getAddTime());
//        } catch (Exception ex1) {
//            try {
//                dt = DateTime.parse(getAddTime());
//            } catch (Exception ex2) {
//                try {
//                    String str = getAddTime();
//                    if (!StringUtils.isBlank(str)) {
//                        str = str.replace("T", " ");
//                        String[] array = StringUtils.split(str, ".");
//                        str = array[0];
//                        dt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(str);
//                    }
//                } catch (Exception ex3) {
//                    dt = new DateTime();
//                }
//            }
//        }

        try {
            Date date = DateTimeParse.parse(getAddTime());
            return new DateTime(date);
        } catch (Exception ex) {
            return new DateTime();
        }
    }

    public Date getAddTimeToDate() {
        return getAddTimeToDateTime().toDate();
    }

}
