package com.ymatou.alarmcenter.facade.rest;

import com.ymatou.alarmcenter.facade.model.SaveSingleResponse;
import com.ymatou.alarmcenter.facade.rest.model.SaveSingleFormRequest;

/**
 * Created by zhangxiaoming on 2016/11/23.
 */
public interface AlarmResource {
    SaveSingleResponse saveSingle(SaveSingleFormRequest request);
}
