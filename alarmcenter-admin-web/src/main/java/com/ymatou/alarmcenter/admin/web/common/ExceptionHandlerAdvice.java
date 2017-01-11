package com.ymatou.alarmcenter.admin.web.common;

import com.ymatou.alarmcenter.admin.web.common.result.AlertDialog;
import com.ymatou.alarmcenter.admin.web.common.result.JavaScriptResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangxiaoming on 2016/11/21.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    //WebRequest request,
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JavaScriptResult exception(Exception exception, HttpServletRequest request) {
        logger.error(exception.getMessage(), exception);
        logger.info("error:" + exception.getMessage());
        if (StringUtils.equals(request.getMethod(), "GET")) {
            JavaScriptResult result = new JavaScriptResult();
            result.setScript(exception.getMessage());
            return result;
        }
        AlertDialog alertDialog = new AlertDialog("操作失败:" + exception.getMessage());
        return alertDialog;
    }


}

