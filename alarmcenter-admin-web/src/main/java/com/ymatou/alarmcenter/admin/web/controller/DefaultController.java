package com.ymatou.alarmcenter.admin.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangxiaoming on 2016/12/14.
 */
@RestController
public class DefaultController {
    @RequestMapping("/version")
    public String version() {
        return "2016-12-14.2";
    }

    @RequestMapping("/warmup")
    public String status() {
        return "ok";
    }
}
