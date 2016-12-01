package com.ymatou.alarmcenter.facade.common;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by zhangxiaoming on 2016/11/30.
 */
@Aspect
@Component
public class FacadeAspect {
    private static final Logger logger = LoggerFactory.getLogger(FacadeAspect.class);

    @AfterThrowing(pointcut = "allFacade()", throwing = "ex")
    public void doAfterThrowing(Throwable ex) {
        logger.error(ex.getMessage(), ex);
    }

    @Pointcut("execution(* com.ymatou.alarmcenter.facade.*Facade.*(..))")
    public void allFacade() {

    }
}
