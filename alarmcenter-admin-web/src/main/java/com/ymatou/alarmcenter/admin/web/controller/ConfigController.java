package com.ymatou.alarmcenter.admin.web.controller;

import com.ymatou.alarmcenter.admin.web.common.result.AlertDialog;
import com.ymatou.alarmcenter.admin.web.common.result.JavaScriptResult;
import com.ymatou.alarmcenter.admin.web.model.ConfigModel;
import com.ymatou.alarmcenter.admin.web.model.ConvertUtils;
import com.ymatou.alarmcenter.admin.web.servcie.CommonServcie;
import com.ymatou.alarmcenter.domain.model.AppBaseConfig;
import com.ymatou.alarmcenter.domain.model.AppErrorConfig;
import com.ymatou.alarmcenter.domain.model.PagingQueryResult;
import com.ymatou.alarmcenter.domain.repository.AppBaseConfigRepository;
import com.ymatou.alarmcenter.domain.repository.AppErrorConfigRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by zhangxiaoming on 2016/12/23.
 */
@Controller
@RequestMapping("/config")
public class ConfigController {
    @Resource
    private AppBaseConfigRepository appBaseConfigRepository;

    @Resource
    private AppErrorConfigRepository appErrorConfigRepository;

    @Resource
    private CommonServcie commonServcie;

    @RequestMapping(value = "/create", method = GET)
    public String createConfig() {
        return "/config/create";
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = POST)
    public JavaScriptResult createConfig(ConfigModel model) {
        AppBaseConfig appBaseConfig = appBaseConfigRepository.getAppBaseConfigByAppId(model.getAppId());

        if (appBaseConfig != null)
            throw new RuntimeException(String.format("应用编号为：%s 的记录已存在！", model.getAppId()));
        appBaseConfig = new AppBaseConfig();
        AppErrorConfig appErrorConfig = new AppErrorConfig();
        ConvertUtils.toAppBaseConfigAndAppErrorConfig(model, appBaseConfig, appErrorConfig);
        appBaseConfig.setLastUpdateDatetime(new Date());
        appErrorConfig.setLastUpdateDatetime(new Date());
        appErrorConfig.setLastEmailHandleTime(new Date());
        appErrorConfig.setLastSmsHandleTime(new Date());
        appBaseConfigRepository.saveAppBaseConfig(appBaseConfig);
        appErrorConfigRepository.saveAppErrorConfig(appErrorConfig);
        return new AlertDialog("操作成功！", "/config/list");
    }

    @RequestMapping(value = "/edit", method = GET)
    public ModelAndView editConfig(@RequestParam("appId") String appId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/config/edit");
        AppBaseConfig appBaseConfig = appBaseConfigRepository.getAppBaseConfigByAppId(appId);
        AppErrorConfig appErrorConfig = appErrorConfigRepository.getAppErrorConfigByAppId(appId);
        ConfigModel configModel = ConvertUtils.toConfigModel(appBaseConfig, appErrorConfig);
        modelAndView.addObject("config", configModel);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/edit", method = POST)
    public JavaScriptResult editConfig(ConfigModel model) {
        AppBaseConfig appBaseConfig = appBaseConfigRepository.getAppBaseConfigByAppId(model.getAppId());
        if (appBaseConfig == null)
            throw new RuntimeException(String.format("应用编号为：%s 的记录不存在！", model.getAppId()));
        AppErrorConfig appErrorConfig = appErrorConfigRepository.getAppErrorConfigByAppId(model.getAppId());
        ConvertUtils.toAppBaseConfigAndAppErrorConfig(model, appBaseConfig, appErrorConfig);
        if (appBaseConfig != null && appBaseConfig.getLastUpdateDatetime() == null)
            appBaseConfig.setLastUpdateDatetime(new Date());
        if (appErrorConfig != null && appErrorConfig.getLastUpdateDatetime() == null)
            appErrorConfig.setLastUpdateDatetime(new Date());
        if (appErrorConfig != null && appErrorConfig.getLastEmailHandleTime() == null)
            appErrorConfig.setLastEmailHandleTime(new Date());
        if (appErrorConfig != null && appErrorConfig.getLastSmsHandleTime() == null)
            appErrorConfig.setLastSmsHandleTime(new Date());
        appBaseConfigRepository.saveAppBaseConfig(appBaseConfig);
        appErrorConfigRepository.saveAppErrorConfig(appErrorConfig);
        return new AlertDialog("操作成功！", "/config/list");
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = POST)
    public JavaScriptResult deleteConfig(@RequestParam("appId") String appId) {
        AppBaseConfig appBaseConfig = appBaseConfigRepository.getAppBaseConfigByAppId(appId);
        if (appBaseConfig == null)
            throw new RuntimeException(String.format("应用编号为：%s 的记录不存在！", appId));
        appBaseConfigRepository.deleteAppBaseConfigByAppId(appId);
        AppErrorConfig appErrorConfig = appErrorConfigRepository.getAppErrorConfigByAppId(appId);
        if (appErrorConfig != null) {
            appErrorConfigRepository.deleteAppErrorConfig(appId);
        }
        return new AlertDialog("操作成功！", "/config/list");
    }

    @RequestMapping(value = "/list", method = GET)
    public ModelAndView searchConfig(@RequestParam(value = "appId", required = false) String appId,
                                     @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                     @RequestParam(value = "pageIndex", required = false) Integer pageIndex) {
        if (pageSize == null)
            pageSize = 15;
        if (pageIndex == null)
            pageIndex = 1;
        if (!StringUtils.isBlank(appId))
            appId = appId.toLowerCase();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/config/list");
        PagingQueryResult<AppBaseConfig> result = appBaseConfigRepository.getAppBaseConfigList(appId, pageSize, pageIndex);
        modelAndView.addObject("list", result.getList());
        modelAndView.addObject("pageSize", pageSize);
        modelAndView.addObject("pageIndex", pageIndex);
        modelAndView.addObject("totalRecords", result.getTotalRecords());
        modelAndView.addObject("appId", appId);
        modelAndView.addObject("appIdMap", commonServcie.getAllAppIdMap());
        return modelAndView;
    }

    @RequestMapping(value = "/detail", method = GET)
    public ModelAndView configDetail(@RequestParam(value = "appId", required = false) String appId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/config/detail");
        AppBaseConfig appBaseConfig = appBaseConfigRepository.getAppBaseConfigByAppId(appId);
        AppErrorConfig appErrorConfig = appErrorConfigRepository.getAppErrorConfigByAppId(appId);
        if (appBaseConfig == null)
            return modelAndView;
        modelAndView.addObject("appId", appBaseConfig.getAppId());
        modelAndView.addObject("emailTo", appBaseConfig.getEmailTo());
        modelAndView.addObject("phoneNumber", appBaseConfig.getPhoneNumber());
        modelAndView.addObject("lastUpdateDatetime", appBaseConfig.getLastUpdateDatetime());
        modelAndView.addObject("whitelist", appBaseConfig.getWhitelist());
        if (appErrorConfig != null) {
            modelAndView.addObject("sendEmailTimeInterval", appErrorConfig.getSendEmailTimeInterval());
            modelAndView.addObject("sendEmailNumLimit", appErrorConfig.getSendEmailNumLimit());
            modelAndView.addObject("sendSmsTimeInterval", appErrorConfig.getSendSmsTimeInterval());
            modelAndView.addObject("sendSmsNumLimit", appErrorConfig.getSendSmsNumLimit());
        }
        return modelAndView;
    }
}
