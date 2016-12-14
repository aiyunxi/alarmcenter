package com.ymatou.alarmcenter.facade.rest.model;

import org.jboss.resteasy.annotations.Body;

/**
 * Created by zhangxiaoming on 2016/12/13.
 */
public class SaveBatchRequest {

    @Body
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
