package com.ymatou.alarmcenter.admin.web.controller;

import com.ymatou.alarmcenter.domain.model.AppErrorLog;
import com.ymatou.alarmcenter.domain.repository.AppErrorLogRepository;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by zhangxiaoming on 2017/1/4.
 */
@Controller
@RequestMapping("/exception")
public class ExceptionController {

    @Resource
    private AppErrorLogRepository appErrorLogRepository;

    @RequestMapping(value = "/detail", method = GET)
    public ModelAndView exceptionDetail(@RequestParam("id") String id, @RequestParam("time") String time) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/exception/detail");
        DateTime dt = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(time);
        AppErrorLog appErrorLog = appErrorLogRepository.getAppErrLog(dt.toDate(), id);
        if (appErrorLog == null)
            appErrorLog = new AppErrorLog();
        modelAndView.addObject("errorLog", appErrorLog);
        return modelAndView;
    }

    @RequestMapping(value = "/show", method = GET)
    public ModelAndView exceptionShow(@RequestParam("id") String id, @RequestParam("time") String time) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/exception/show");
        DateTime dt = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(time);
        AppErrorLog appErrorLog = appErrorLogRepository.getAppErrLog(dt.toDate(), id);
        if (appErrorLog == null)
            appErrorLog = new AppErrorLog();
        modelAndView.addObject("errorLog", appErrorLog);
        return modelAndView;
    }

    @RequestMapping(value = "/list", method = GET)
    public ModelAndView searchException(@RequestParam(value = "appId", required = false) String appId,
                                        @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                        @RequestParam(value = "pageIndex", required = false) Integer pageIndex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/exception/list");

        return modelAndView;
    }
}
