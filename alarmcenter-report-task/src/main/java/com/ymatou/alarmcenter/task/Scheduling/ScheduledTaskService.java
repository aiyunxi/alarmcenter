package com.ymatou.alarmcenter.task.Scheduling;

import com.ymatou.alarmcenter.domain.repository.AppErrorLogRepository;
import com.ymatou.alarmcenter.domain.service.ErrorLogService;
import org.joda.time.DateTime;
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

    @Resource
    private AppErrorLogRepository appErrorLogRepository;
//    @Scheduled(fixedRate = 1000)
//    public void test() {
//        //System.out.println("test:" + UUID.randomUUID());
//        //throw new RuntimeException("error test!");
//    }

    /**
     * 每分钟执行一次异常报警处理程序
     */
    @Scheduled(fixedRate = 6000)
    public void work() {
        try {
            errorLogService.errorHandler();
            Logger.debug("job working!");
        } catch (Exception ex) {
            Logger.error("job work error:", ex);
        }
    }

    /**
     * 每月的15号执行删除上一个月数据库
     */
    @Scheduled(cron = "0 0 0 15 * ? *")
    public void deleteDatabase() {
        try {
            DateTime dt = new DateTime();
            dt.minusMonths(1);
            String dbName = appErrorLogRepository.getDatabaseName(dt.toDate());
            appErrorLogRepository.deleteDatabse(dbName);
            Logger.debug("job deleteDatabase:" + dbName);

        } catch (Exception ex) {
            Logger.error("job deleteDatabase error:", ex);
        }
    }
}