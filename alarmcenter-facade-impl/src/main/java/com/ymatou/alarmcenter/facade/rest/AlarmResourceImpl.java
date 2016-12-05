package com.ymatou.alarmcenter.facade.rest;

import com.ymatou.alarmcenter.domain.service.ErrorLogService;
import com.ymatou.alarmcenter.facade.AlarmFacade;
import com.ymatou.alarmcenter.facade.enums.AppErrorLevel;
import com.ymatou.alarmcenter.facade.model.SaveSingleRequest;
import com.ymatou.alarmcenter.facade.model.SaveSingleResponse;
import com.ymatou.alarmcenter.facade.rest.model.SaveSingleFormRequest;
import org.jboss.resteasy.annotations.Form;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by zhangxiaoming on 2016/11/23.
 */
@Component("alarmResource")
@Path("/")
@Produces({"application/json; charset=UTF-8"})
@Consumes({MediaType.APPLICATION_JSON})
public class AlarmResourceImpl implements AlarmResource {

    @Resource
    private AlarmFacade alarmFacade;

    @Resource
    private ErrorLogService errorLogService;


    @POST
    //@Produces({"application/x-www-form-urlencoded; charset=UTF-8"})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    //@Path("/Alarm/SaveSingle")
    @Path("/{Alarm:(?i:Alarm)}/{SaveSingle:(?i:SaveSingle)}")
    @Override
    public SaveSingleResponse saveSingle(@Form SaveSingleFormRequest request) {
        SaveSingleRequest saveSingleRequest = new SaveSingleRequest();
        saveSingleRequest.setAppId(request.getAppId());
        saveSingleRequest.setMessage(request.getMessage());
        saveSingleRequest.setAddTime(request.getAddTime());
        saveSingleRequest.setAssemblyName(request.getAssemblyName());
        saveSingleRequest.setExceptionName(request.getExceptionName());
        saveSingleRequest.setHeader(request.getHeader());
        saveSingleRequest.setMachineIp(request.getMachineIp());
        saveSingleRequest.setMethodName(request.getMethodName());
        saveSingleRequest.setReqForm(request.getReqForm());
        saveSingleRequest.setReqUrl(request.getReqUrl());
        saveSingleRequest.setErrorLevel(AppErrorLevel.getValue(request.getErrorLevel()));
        saveSingleRequest.setStackTrace(request.getStackTrace());
        saveSingleRequest.setTitle(request.getTitle());
        return alarmFacade.saveSingle(saveSingleRequest);
    }


}
