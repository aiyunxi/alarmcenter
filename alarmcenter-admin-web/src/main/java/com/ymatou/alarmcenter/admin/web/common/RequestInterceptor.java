//package com.ymatou.alarmcenter.admin.web.common;
//
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created by zhangxiaoming on 2016/11/21.
// */
//public class RequestInterceptor extends HandlerInterceptorAdapter {
//    @Resource
//    private StatusReportService statusReportService;
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        if (request == null)
//            return;
//        String consumerAppId = request.getHeader("ConsumerAppId");
//        String consumerIp = request.getHeader("ConsumerIp");
//        String consumerAssemblyVersion = request.getHeader("ConsumerAssemblyVersion");
//        statusReportService.headerHandler(consumerAppId, consumerIp, consumerAssemblyVersion);
//    }
//}
