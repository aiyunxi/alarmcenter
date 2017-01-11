package com.ymatou.alarmcenter.admin.web.controller;

import com.ymatou.alarmcenter.admin.web.model.AppErrorLogModel;
import com.ymatou.alarmcenter.admin.web.model.ConvertUtils;
import com.ymatou.alarmcenter.admin.web.servcie.CommonServcie;
import com.ymatou.alarmcenter.domain.model.AppErrorLog;
import com.ymatou.alarmcenter.domain.model.PagingQueryResult;
import com.ymatou.alarmcenter.domain.repository.AppErrorLogRepository;
import com.ymatou.alarmcenter.facade.enums.AppErrorLevel;
import com.ymatou.alarmcenter.infrastructure.common.Utils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by zhangxiaoming on 2017/1/4.
 */
@Controller
@RequestMapping("/exception")
public class ExceptionController {

    @Resource
    private AppErrorLogRepository appErrorLogRepository;
    @Resource
    private CommonServcie commonServcie;

    @RequestMapping(value = "/detail", method = GET)
    public ModelAndView exceptionDetail(@RequestParam("id") String id, @RequestParam("date") String date) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/exception/detail");
        DateTime dt = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(date);
        AppErrorLog appErrorLog = appErrorLogRepository.getAppErrLog(dt.toDate(), id);
        if (appErrorLog == null)
            appErrorLog = new AppErrorLog();
        modelAndView.addObject("errorLog", appErrorLog);
        AppErrorLevel errorLevel = AppErrorLevel.getByCode(appErrorLog.getErrorLevel());
        modelAndView.addObject("errorLevel", errorLevel);
        return modelAndView;
    }

    @RequestMapping(value = "/chart", method = GET)
    public ModelAndView chart(String appId, String date) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/exception/chart");
        DateTime dt = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(date);
        List<DateTime> timeList = new ArrayList<>();


        return modelAndView;
    }

    @RequestMapping(value = "/list", method = GET)
    public ModelAndView searchException(@RequestParam(value = "appId", required = false) String appId,
                                        @RequestParam(value = "errorLevel", required = false) Integer errorLevel,
                                        @RequestParam(value = "beginTime", required = false) String beginTime,
                                        @RequestParam(value = "endTime", required = false) String endTime,
                                        @RequestParam(value = "machineIp", required = false) String machineIp,
                                        @RequestParam(value = "keyWord", required = false) String keyWord,
                                        @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                        @RequestParam(value = "pageIndex", required = false) Integer pageIndex) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/exception/list");
        if (pageSize == null)
            pageSize = 15;
        if (pageIndex == null)
            pageIndex = 1;
        if (!StringUtils.isBlank(appId))
            appId = appId.toLowerCase();

        DateTime dt = new DateTime();

        DateTime begin = Utils.DateParse(beginTime);
        DateTime end = Utils.DateParse(endTime);
        if (begin == null) {
            begin = new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), 0, 0, 0);
        }
        if (end == null) {
            dt = new DateTime().plusDays(1);
            end = new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), 0, 0, 0);
        }

        PagingQueryResult<AppErrorLog> result = appErrorLogRepository.getAppErrorLogList(appId, errorLevel, begin.toDate(), end.toDate(),
                machineIp, keyWord, pageSize, pageIndex);
        List<AppErrorLogModel> models = new ArrayList<>();
        if (result.getList() != null) {
            for (AppErrorLog log : result.getList()) {
                if (log == null)
                    continue;
                AppErrorLogModel model = ConvertUtils.toAppErrorLogModel(log);
                models.add(model);
            }
        }

        modelAndView.addObject("list", models);
        modelAndView.addObject("pageSize", pageSize);
        modelAndView.addObject("pageIndex", pageIndex);
        modelAndView.addObject("totalRecords", result.getTotalRecords());
        modelAndView.addObject("appId", appId);
        modelAndView.addObject("errorLevel", errorLevel);
        modelAndView.addObject("machineIp", machineIp);
        modelAndView.addObject("keyWord", keyWord);
        modelAndView.addObject("beginTime", begin.toString("yyyy/MM/dd HH:mm:ss"));
        modelAndView.addObject("endTime", end.toString("yyyy/MM/dd HH:mm:ss"));

        Map<Integer, String> errorLevelMap = new HashMap<>();
        errorLevelMap.put(null, "请选择异常类型");
        errorLevelMap.putAll(AppErrorLevel.toMap());
        modelAndView.addObject("errorLevelMap", errorLevelMap);

        modelAndView.addObject("appIdMap", commonServcie.getAllAppIdMap());
        return modelAndView;
    }
}
