package com.ymatou.alarmcenter.task.Scheduling;

import com.ymatou.alarmcenter.domain.repository.AppErrorLogRepository;
import com.ymatou.alarmcenter.domain.service.ErrorLogService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangxiaoming on 2016/11/29.
 */
@Service
public class ScheduledTaskService {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskService.class);
    private static ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(5);

    public ScheduledTaskService() {
        scheduExec.scheduleWithFixedDelay(() -> work(), 0, 60, TimeUnit.SECONDS);
    }

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
    //@Scheduled(fixedRate = 60000)
    public void work() {
        try {
            ResourceBundle disconf = ResourceBundle.getBundle("disconf");
            String env = disconf == null ? "" : disconf.getString("env");
            if (StringUtils.equalsIgnoreCase("STG", env))
                return;
            logger.info("env:" + env + ",job running");
        } catch (Exception ex) {
            logger.error("errorHandler,ResourceBundle", ex);
        }
        try {
            errorLogService.errorHandler();
        } catch (Exception ex) {
            logger.error("job work error:", ex);
        }
    }

    /**
     * 每月的15号执行删除上一个月数据库
     */
    @Scheduled(cron = "0 0 0 15 * ? ")
    public void deleteDatabase() {
        try {
            DateTime dt = new DateTime();
            dt.minusMonths(1);
            String dbName = appErrorLogRepository.getDatabaseName(dt.toDate());
            appErrorLogRepository.deleteDatabse(dbName);
            logger.info("job deleteDatabase:" + dbName);

        } catch (Exception ex) {
            logger.error("job deleteDatabase error:", ex);
        }
    }
}