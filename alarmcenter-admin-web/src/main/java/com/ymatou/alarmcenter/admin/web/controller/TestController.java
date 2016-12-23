package com.ymatou.alarmcenter.admin.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by zhangxiaoming on 2016/12/23.
 */
@Controller
public class TestController {
    @RequestMapping(value = "/test", method = GET)
    public String test(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = authentication.getName();
        model.addAttribute("user", loginId);
        return "/test/index";
    }
}
