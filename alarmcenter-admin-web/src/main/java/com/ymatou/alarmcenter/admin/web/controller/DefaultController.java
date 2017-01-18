package com.ymatou.alarmcenter.admin.web.controller;

import com.ymatou.alarmcenter.admin.web.model.DateTestModel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangxiaoming on 2016/12/14.
 */
@RestController
public class DefaultController {
    @RequestMapping("/version")
    public String version() {
        return "2017-01-18.1";
    }

    @RequestMapping("/warmup")
    public String status() {
        return "ok";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public DateTestModel test(@RequestBody DateTestModel model) {
        //model.setDate(new Date());
        return model;
    }
}
