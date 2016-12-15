package com.ymatou.alarmcenter.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhangxiaoming on 2016/12/14.
 */
@Controller
public class AccountController {
    @RequestMapping("/account/login")
    public String login() {
        return "/account/login";
    }
}
