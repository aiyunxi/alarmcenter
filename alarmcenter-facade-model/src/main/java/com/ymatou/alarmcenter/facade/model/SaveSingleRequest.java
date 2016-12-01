package com.ymatou.alarmcenter.facade.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ymatou.alarmcenter.facade.enums.AppErrorLevel;

/**
 * Created by zhangxiaoming on 2016/11/23.
 */
public class SaveSingleRequest {
    @JsonProperty("AppId")
    private String appId;
    @JsonProperty("ReqUrl")
    private String reqUrl;
    @JsonProperty("ReqForm")
    private String reqForm;
    @JsonProperty("ErrorLevel")
    private AppErrorLevel errorLevel;//AppErrorLevel enum
    @JsonProperty("MethodName")
    private String methodName;
    @JsonProperty("AssemblyName")
    private String assemblyName;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("ExceptionName")
    private String exceptionName;
    @JsonProperty("Header")
    private String header;
    @JsonProperty("StackTrace")
    private String stackTrace;
    @JsonProperty("AddTime")
    private String addTime;
    @JsonProperty("MachineIp")
    private String machineIp;

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

    public AppErrorLevel getErrorLevel() {
        return errorLevel;
    }

    public void setErrorLevel(AppErrorLevel errorLevel) {
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
    }

    public String getMachineIp() {
        return machineIp;
    }

    public void setMachineIp(String machineIp) {
        this.machineIp = machineIp;
    }
}
