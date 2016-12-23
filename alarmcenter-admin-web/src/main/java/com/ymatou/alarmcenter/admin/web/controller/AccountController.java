package com.ymatou.alarmcenter.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by zhangxiaoming on 2016/12/14.
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @RequestMapping(value = "/login", method = GET)
    public String login() {
        return "/account/login";
    }
//
//    @RequestMapping(value = "/login", method = POST)
//    public String login(LoginModel model) {
//        String str = model.getUsername() + model.getPassword() + model.isRemember();
//        return "redirect:/home";
//    }

//    @RequestMapping(value = "/logout", method = GET)
//    public String logout() {
//        return "redirect:/home";
//    }


}
