package com.ymatou.alarmcenter.admin.web.controller;

import com.ymatou.alarmcenter.admin.web.servcie.CommonServcie;
import com.ymatou.alarmcenter.domain.model.NotifyRecord;
import com.ymatou.alarmcenter.domain.model.PagingQueryResult;
import com.ymatou.alarmcenter.domain.repository.NotifyRecordRepository;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by zhangxiaoming on 2017/1/10.
 */
@Controller
@RequestMapping("/notify")
public class NotifyController {

    @Resource
    private NotifyRecordRepository notifyRecordRepository;
    @Resource
    private CommonServcie commonServcie;

    @RequestMapping(value = "/list", method = GET)
    public ModelAndView searchNotify(@RequestParam(value = "appId", required = false) String appId,
                                     @RequestParam(value = "beginTime", required = false) Date beginTime,
                                     @RequestParam(value = "endTime", required = false) Date endTime,
                                     @RequestParam(value = "notifyType", required = false) Integer notifyType,
                                     @RequestParam(value = "notifyStatus", required = false) Integer notifyStatus,
                                     @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                     @RequestParam(value = "pageIndex", required = false) Integer pageIndex) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/notify/list");
        if (pageSize == null)
            pageSize = 15;
        if (pageIndex == null)
            pageIndex = 1;
        if (!StringUtils.isBlank(appId))
            appId = appId.toLowerCase();

        PagingQueryResult<NotifyRecord> result = notifyRecordRepository.getNotifyRecordList(appId, beginTime, endTime, notifyType,
                notifyStatus, pageSize, pageIndex);

        modelAndView.addObject("list", result.getList());
        modelAndView.addObject("pageSize", pageSize);
        modelAndView.addObject("pageIndex", pageIndex);
        modelAndView.addObject("totalRecords", result.getTotalRecords());
        modelAndView.addObject("appId", appId);
        modelAndView.addObject("beginTime", beginTime == null ? "" : new DateTime(beginTime).toString("yyyy/MM/dd HH:mm:ss"));
        modelAndView.addObject("endTime", endTime == null ? "" : new DateTime(endTime).toString("yyyy/MM/dd HH:mm:ss"));
        modelAndView.addObject("notifyType", notifyType);
        modelAndView.addObject("notifyStatus", notifyStatus);

        Map<Integer, String> notifyTypeMap = new HashMap<>();
        notifyTypeMap.put(null, "请选择类型");
        notifyTypeMap.put(1, "短信");
        notifyTypeMap.put(2, "邮件");
        modelAndView.addObject("notifyTypeMap", notifyTypeMap);

        Map<Integer, String> notifyStatusMap = new HashMap<>();
        notifyStatusMap.put(null, "请择选发送状态");
        notifyStatusMap.put(1, "成功");
        notifyStatusMap.put(2, "失败");
        modelAndView.addObject("notifyStatusMap", notifyStatusMap);

        modelAndView.addObject("appIdMap", commonServcie.getAllAppIdMap());
        return modelAndView;
    }

}
