package com.ymatou.alarmcenter.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhangxiaoming on 2016/12/14.
 */
@Controller
public class HomeController {
    @RequestMapping({"/", "/default", "/home", "/index"})
    public String home(Model model) {
        //throw new RuntimeException("test error");
        return "/home/index";
    }
}
