package com.ymatou.alarmcenter.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by zhangxiaoming on 2016/11/11.
 */
//@ComponentScan("com.ymatou.alarmcenter")
//@ImportResource("classpath:applicationContext.xml")
//@SpringBootApplication


@ComponentScan("com.ymatou.alarmcenter")
@ImportResource("classpath:applicationContext.xml")
@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@Configuration
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
