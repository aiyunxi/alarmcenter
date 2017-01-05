package com.ymatou.alarmcenter.admin.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by zhangxiaoming on 2016/11/9.
 */
@ComponentScan("com.ymatou.alarmcenter")
@ImportResource("classpath:applicationContext.xml")
@SpringBootApplication
@EnableCaching
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}

