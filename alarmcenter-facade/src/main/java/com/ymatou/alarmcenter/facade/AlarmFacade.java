package com.ymatou.alarmcenter.facade;

import com.ymatou.alarmcenter.facade.model.SaveSingleRequest;
import com.ymatou.alarmcenter.facade.model.SaveSingleResponse;

/**
 * Created by zhangxiaoming on 2016/11/23.
 */
public interface AlarmFacade {
    SaveSingleResponse saveSingle(SaveSingleRequest request);
}
