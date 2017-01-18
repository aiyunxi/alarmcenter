package com.ymatou.alarmcenter.admin.web.controller;

import com.ymatou.alarmcenter.admin.web.model.AppErrorLogModel;
import com.ymatou.alarmcenter.admin.web.model.ConvertUtils;
import com.ymatou.alarmcenter.admin.web.servcie.CommonServcie;
import com.ymatou.alarmcenter.domain.model.AppBaseConfig;
import com.ymatou.alarmcenter.domain.model.AppErrorLog;
import com.ymatou.alarmcenter.domain.model.PagingQueryResult;
import com.ymatou.alarmcenter.domain.repository.AppBaseConfigRepository;
import com.ymatou.alarmcenter.domain.repository.AppErrorLogRepository;
import com.ymatou.alarmcenter.facade.enums.AppErrorLevel;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by zhangxiaoming on 2017/1/4.
 */
@Controller
@RequestMapping("/exception")
public class ExceptionController {

    @Resource
    private AppErrorLogRepository appErrorLogRepository;

    @Resource
    private AppBaseConfigRepository appBaseConfigRepository;

    @Resource
    private CommonServcie commonServcie;

    @RequestMapping(value = "/detail", method = GET)
    public ModelAndView exceptionDetail(String id, Date date) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/exception/detail");
        AppErrorLog appErrorLog = appErrorLogRepository.getAppErrLog(date, id);
        if (appErrorLog == null)
            appErrorLog = new AppErrorLog();
        appErrorLog.setHeader(replaceEnter(appErrorLog.getHeader()));
        appErrorLog.setReqForm(replaceEnter(appErrorLog.getReqForm()));
        appErrorLog.setStackTrace(replaceEnter(appErrorLog.getStackTrace()));
        modelAndView.addObject("errorLog", appErrorLog);
        AppErrorLevel errorLevel = AppErrorLevel.getByCode(appErrorLog.getErrorLevel());
        modelAndView.addObject("errorLevel", errorLevel);
        return modelAndView;
    }

    private String replaceEnter(String input) {
        if (StringUtils.isBlank(input))
            return input;
        return input.replace("\r\n", "<br/>").replace("\r", "<br/>").replace("\n", "<br/>");
    }

    @RequestMapping(value = "/chart", method = GET)
    public ModelAndView chart() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/exception/chart");
        Map<Integer, String> errorLevelMap = new HashMap<>();
        errorLevelMap.put(null, "请选择异常类型");
        errorLevelMap.putAll(AppErrorLevel.toMap());
        modelAndView.addObject("errorLevelMap", errorLevelMap);
        modelAndView.addObject("appIdMap", commonServcie.getAllAppIdMap());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/chart", method = POST)
    public Map<String, Long> chart(String appId, Date date, int errorLevel) {
        DateTime dt = new DateTime(date);
        Map<String, Long> countMap = new LinkedHashMap<>();
        for (int i = 0; i < 24; i++) {
            DateTime begin = dt.plusHours(i);
            DateTime end = dt.plusHours(i + 1);
            String key = begin.toString("yyyy-MM-dd HH");
            long count = appErrorLogRepository.getErrorCount(appId, errorLevel, begin.toDate(), end.toDate());
            countMap.put(key, count);
        }
        return countMap;
    }

    @RequestMapping(value = "/ranking", method = GET)
    public String ranking() {
        return "/exception/ranking";
    }

    @ResponseBody
    @RequestMapping(value = "/top", method = GET)
    public Map<String, Long> top() {
        Map<String, Long> countMap = new LinkedHashMap<>();
        List<AppBaseConfig> list = appBaseConfigRepository.getAppBaseConfigList();
        DateTime now = new DateTime();
        for (AppBaseConfig config : list) {
            if (config == null)
                continue;
            long count = appErrorLogRepository.getErrorCount(config.getAppId(), AppErrorLevel.Error.getCode(), now.minusHours(1).toDate(), now.toDate());
            countMap.put(config.getAppId().replace(".ymatou.com",""), count);
        }
        return sortMap(countMap, 10);
    }

    private Map sortMap(Map oldMap, int showNumber) {
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(oldMap.entrySet());
        Collections.sort(list, (o1, o2) -> ((Comparable) ((Map.Entry) (o2)).getValue())
                .compareTo(((Map.Entry) (o1)).getValue()));
        Map newMap = new LinkedHashMap();
        showNumber = list.size() <= showNumber ? list.size() : showNumber;
        for (int i = 0; i < showNumber; i++) {
            newMap.put(list.get(i).getKey(), list.get(i).getValue());
        }
        return newMap;
    }

    @RequestMapping(value = "/list", method = GET)
    public ModelAndView searchException(@RequestParam(value = "appId", required = false) String appId,
                                        @RequestParam(value = "errorLevel", required = false) Integer errorLevel,
                                        @RequestParam(value = "beginTime", required = false) Date beginTime,
                                        @RequestParam(value = "endTime", required = false) Date endTime,
                                        @RequestParam(value = "machineIp", required = false) String machineIp,
                                        @RequestParam(value = "keyWord", required = false) String keyWord,
                                        @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                        @RequestParam(value = "pageIndex", required = false) Integer pageIndex) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/exception/list");
        Map<Integer, String> errorLevelMap = new HashMap<>();
        errorLevelMap.put(null, "请选择异常类型");
        errorLevelMap.putAll(AppErrorLevel.toMap());
        modelAndView.addObject("errorLevelMap", errorLevelMap);
        modelAndView.addObject("appIdMap", commonServcie.getAllAppIdMap());
        if (pageSize == null)
            pageSize = 15;
        if (pageIndex == null)
            pageIndex = 1;


        if (!StringUtils.isBlank(appId))
            appId = appId.toLowerCase();

        DateTime dt = new DateTime();

        DateTime begin = beginTime == null ? null : new DateTime(beginTime);
        DateTime end = endTime == null ? null : new DateTime(endTime);
        if (begin == null) {
            begin = new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), 0, 0, 0);
        }
        if (end == null) {
            dt = new DateTime().plusDays(1);
            end = new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), 0, 0, 0);
        }
        modelAndView.addObject("beginTime", begin.toString("yyyy/MM/dd HH:mm:ss"));
        modelAndView.addObject("endTime", end.toString("yyyy/MM/dd HH:mm:ss"));

        if (StringUtils.isBlank(appId) && errorLevel == null && beginTime == null && beginTime == null
                && StringUtils.isBlank(machineIp) && StringUtils.isBlank(keyWord))
            return modelAndView;

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

        return modelAndView;
    }


}
