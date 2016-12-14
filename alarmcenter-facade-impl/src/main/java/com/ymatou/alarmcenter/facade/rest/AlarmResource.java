package com.ymatou.alarmcenter.facade.rest;

import com.ymatou.alarmcenter.facade.model.SaveSingleResponse;
import com.ymatou.alarmcenter.facade.rest.model.SaveBatchResponse;
import com.ymatou.alarmcenter.facade.rest.model.SaveSingleFormRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangxiaoming on 2016/11/23.
 */
public interface AlarmResource {
    SaveSingleResponse saveSingle(SaveSingleFormRequest request);

    SaveBatchResponse saveBatch(HttpServletRequest servletRequest);
}
