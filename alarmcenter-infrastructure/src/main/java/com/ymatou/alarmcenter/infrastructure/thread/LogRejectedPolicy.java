package com.ymatou.alarmcenter.infrastructure.thread;

/**
 * Created by zhangxiaoming on 2016/11/22.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池对拒绝任务（无线程可用）的处理策略 记录日志
 *
 * @author luoshiqian 2016/5/18 10:27
 */
public class LogRejectedPolicy implements RejectedExecutionHandler {

    private Logger logger = LoggerFactory.getLogger(LogRejectedPolicy.class);

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        logger.error("Task " + r.toString() + " rejected from " + executor.toString());
    }
}