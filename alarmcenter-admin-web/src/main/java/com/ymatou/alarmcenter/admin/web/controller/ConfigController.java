package com.ymatou.alarmcenter.admin.web.controller;

import com.ymatou.alarmcenter.admin.web.model.ConfigModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by zhangxiaoming on 2016/12/23.
 */
@Controller
@RequestMapping("/config")
public class ConfigController {
    @RequestMapping(value = "/create", method = GET)
    public String createConfig() {
        return "/config/create";
    }

    @RequestMapping(value = "/create", method = POST)
    public String createConfig(ConfigModel model) {
        return "";
    }

    @RequestMapping(value = "/update", method = GET)
    public String updateConfig() {
        return "/config/update";
    }

    @RequestMapping(value = "/update", method = POST)
    public String updateConfig(ConfigModel model) {
        return "";
    }

    @RequestMapping(value = "/delete", method = POST)
    public String deleteConfig(String id) {
        return "";
    }

    @RequestMapping(value = "/list", method = GET)
    public String searchConfig(@RequestParam(value = "appId", required = false) String appId) {
        return "/config/list";
    }

}
