package com.ymatou.alarmcenter.facade.impl;

import com.ymatou.alarmcenter.domain.model.AppErrorLog;
import com.ymatou.alarmcenter.domain.service.ErrorLogService;
import com.ymatou.alarmcenter.facade.AlarmFacade;
import com.ymatou.alarmcenter.facade.model.SaveSingleRequest;
import com.ymatou.alarmcenter.facade.model.SaveSingleResponse;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.ymatou.alarmcenter.infrastructure.common.Utils.getTimeStamp;

/**
 * Created by zhangxiaoming on 2016/11/23.
 */

@Component("alarmFacade")
public class AlarmFacadeImpl implements AlarmFacade {
    @Resource
    private ErrorLogService errorLogService;

    @Override
    public SaveSingleResponse saveSingle(SaveSingleRequest request) {
        SaveSingleResponse response = new SaveSingleResponse();
        if (request == null) {
            response.setResult(false);
            return response;
        }
        AppErrorLog appErrLog = new AppErrorLog();
        appErrLog.setAppId(request.getAppId());
        appErrLog.setReqUrl(request.getReqUrl());
        appErrLog.setReqForm(request.getReqForm());
        appErrLog.setErrorLevel(request.getErrorLevel() == null ? 0 : request.getErrorLevel().getCode());
        appErrLog.setMethodName(request.getMethodName());
        appErrLog.setAssemblyName(request.getAssemblyName());
        appErrLog.setTitle(request.getTitle());
        appErrLog.setMessage(request.getMessage());
        appErrLog.setExceptionName(request.getExceptionName());
        appErrLog.setHeader(request.getHeader());
        appErrLog.setStackTrace(request.getStackTrace());
        appErrLog.setAddTime(request.getAddTime());
        appErrLog.setMachineIp(request.getMachineIp());
        appErrLog.setRecordTimeStamp(getTimeStamp(new DateTime()));
        errorLogService.saveAppErrLog(appErrLog);
        response.setResult(true);
        return response;
    }


}
