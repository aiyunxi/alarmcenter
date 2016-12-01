package com.ymatou.alarmcenter.task.Scheduling;

import com.ymatou.alarmcenter.domain.service.ErrorLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhangxiaoming on 2016/11/29.
 */
@Service
public class ScheduledTaskService {
    private static final Logger Logger = LoggerFactory.getLogger(ScheduledTaskService.class);
    @Resource
    private ErrorLogService errorLogService;

//    @Scheduled(fixedRate = 1000)
//    public void test() {
//        //System.out.println("test:" + UUID.randomUUID());
//        //throw new RuntimeException("error test!");
//    }

    @Scheduled(fixedRate = 6000)
    public void work() {
        try {
            errorLogService.errorHandler();
            Logger.debug("job working!");
        } catch (Exception ex) {
            Logger.error("job work error:", ex);
        }
    }

}