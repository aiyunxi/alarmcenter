package com.ymatou.alarmcenter.facade.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.FormParam;

/**
 * Created by zhangxiaoming on 2016/12/13.
 */
public class SaveBatchRequest {
    @FormParam("Error")
    @JsonProperty("Error")
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
