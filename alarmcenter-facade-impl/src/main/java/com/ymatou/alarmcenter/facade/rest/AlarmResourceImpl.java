package com.ymatou.alarmcenter.facade.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ymatou.alarmcenter.domain.service.ErrorLogService;
import com.ymatou.alarmcenter.facade.AlarmFacade;
import com.ymatou.alarmcenter.facade.common.FacadeAspect;
import com.ymatou.alarmcenter.facade.enums.AppErrorLevel;
import com.ymatou.alarmcenter.facade.model.SaveSingleRequest;
import com.ymatou.alarmcenter.facade.model.SaveSingleResponse;
import com.ymatou.alarmcenter.facade.rest.model.SaveBatchRequest;
import com.ymatou.alarmcenter.facade.rest.model.SaveBatchResponse;
import com.ymatou.alarmcenter.facade.rest.model.SaveSingleFormRequest;
import com.ymatou.alarmcenter.infrastructure.config.CustomObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.jboss.resteasy.annotations.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by zhangxiaoming on 2016/11/23.
 */
@Component("alarmResource")
@Path("/")
@Produces({"application/json; charset=UTF-8"})
@Consumes({MediaType.APPLICATION_JSON})
public class AlarmResourceImpl implements AlarmResource {
    private static final Logger logger = LoggerFactory.getLogger(FacadeAspect.class);
    @Resource
    private AlarmFacade alarmFacade;

    @Resource
    private ErrorLogService errorLogService;

    @POST
    //@Path("/Alarm/SaveSingle")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{Alarm:(?i:Alarm)}/{SaveSingle:(?i:SaveSingle)}")
    @Override
    public SaveSingleResponse saveSingleJson(SaveSingleFormRequest request) {
        return saveSingle(request);
    }


    @POST
    //@Produces({"application/x-www-form-urlencoded; charset=UTF-8"})
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    //@Path("/Alarm/SaveSingle")
    @Path("/{Alarm:(?i:Alarm)}/{SaveSingle:(?i:SaveSingle)}")
    @Override
    public SaveSingleResponse saveSingle(@Form SaveSingleFormRequest request) {
        if(StringUtils.isBlank(request.getAppId())){
            return new SaveSingleResponse();
        }
        SaveSingleRequest saveSingleRequest = new SaveSingleRequest();
        saveSingleRequest.setAppId(request.getAppId().toLowerCase());
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

    @POST
    //@Path("/Alarm/SaveBatch")
    @Path("/{Alarm:(?i:Alarm)}/{SaveBatch:(?i:SaveBatch)}")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public SaveBatchResponse saveBatch(@Form SaveBatchRequest request) {
        SaveBatchResponse response = new SaveBatchResponse();
        response.setStatus(0);
        if (request == null)
            return response;
        try {
            String value = request.getError();
            if (StringUtils.isBlank(value))
                return response;
            ObjectMapper mapper = new CustomObjectMapper();
            List<SaveSingleFormRequest> list = mapper.readValue(value, new TypeReference<List<SaveSingleFormRequest>>() {
            });
            if (list != null) {
                for (SaveSingleFormRequest item : list) {
                    if (item == null)
                        continue;
                    try {
                        saveSingle(item);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return response;
    }

}
