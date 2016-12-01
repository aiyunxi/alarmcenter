package com.ymatou.alarmcenter.facade.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.FormParam;

/**
 * Created by zhangxiaoming on 2016/11/30.
 */
public class SaveSingleFormRequest {
    @FormParam("AppId")
    @JsonProperty("AppId")
    private String appId;

    @FormParam("ReqUrl")
    @JsonProperty("ReqUrl")
    private String reqUrl;

    @FormParam("ReqForm")
    @JsonProperty("ReqForm")
    private String reqForm;

    @FormParam("ErrorLevel")
    @JsonProperty("ErrorLevel")
    private String errorLevel;//AppErrorLevel enum

    @FormParam("MethodName")
    @JsonProperty("MethodName")
    private String methodName;

    @FormParam("AssemblyName")
    @JsonProperty("AssemblyName")
    private String assemblyName;

    @FormParam("Title")
    @JsonProperty("Title")
    private String title;

    @FormParam("Message")
    @JsonProperty("Message")
    private String message;

    @FormParam("ExceptionName")
    @JsonProperty("ExceptionName")
    private String exceptionName;

    @FormParam("Header")
    @JsonProperty("Header")
    private String header;

    @FormParam("StackTrace")
    @JsonProperty("StackTrace")
    private String stackTrace;

    @FormParam("AddTime")
    @JsonProperty("AddTime")
    private String addTime;

    @FormParam("MachineIp")
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

    public String getErrorLevel() {
        return errorLevel;
    }

    public void setErrorLevel(String errorLevel) {
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
