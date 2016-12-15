package com.ymatou.alarmcenter.admin.web.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by zhangxiaoming on 2016/11/21.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(value = Exception.class)
    public Exception exception(Exception exception, WebRequest request) {
        LOGGER.error(exception.getMessage(), exception);
        //LOGGER.info("error:" + exception.getMessage());
        return exception;
    }
}

