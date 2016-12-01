package com.ymatou.alarmcenter.api.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by zhangxiaoming on 2016/11/9.
 */
@SpringBootApplication
@ComponentScan("com.ymatou.alarmcenter")
@ImportResource("classpath:applicationContext.xml")
@EnableCaching
@EnableAspectJAutoProxy
@EnableAutoConfiguration
@EnableScheduling
@EnableAsync
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
