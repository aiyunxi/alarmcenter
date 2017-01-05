package com.ymatou.alarmcenter.admin.web.common;

import com.ymatou.alarmcenter.admin.web.common.result.AlertDialog;
import com.ymatou.alarmcenter.admin.web.common.result.JavaScriptResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by zhangxiaoming on 2016/11/21.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JavaScriptResult exception(Exception exception, WebRequest request) {
        logger.error(exception.getMessage(), exception);
        logger.info("error:" + exception.getMessage());
        AlertDialog alertDialog = new AlertDialog("操作失败:" + exception.getMessage());
        return alertDialog;
    }
}

