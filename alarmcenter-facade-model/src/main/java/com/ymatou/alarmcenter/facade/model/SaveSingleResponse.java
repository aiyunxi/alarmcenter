package com.ymatou.alarmcenter.facade.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhangxiaoming on 2016/11/23.
 */
public class SaveSingleResponse {
    @JsonProperty("Result")
    private boolean result;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
