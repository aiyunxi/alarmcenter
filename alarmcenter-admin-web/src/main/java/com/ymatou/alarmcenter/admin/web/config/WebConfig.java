package com.ymatou.alarmcenter.admin.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Created by zhangxiaoming on 2016/11/21.
 */
@Configuration
@ComponentScan(basePackages = "com.ymatou.alarmcenter.admin.web.controller", useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = RestController.class)
        })
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        //registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public RequestMappingHandlerMapping handlerMapping() {
        //忽略路径大小写
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        RequestMappingHandlerMapping requestMappingHandlerMapping = new RequestMappingHandlerMapping();
        requestMappingHandlerMapping.setOrder(0);
        requestMappingHandlerMapping.setPathMatcher(matcher);
        return requestMappingHandlerMapping;
    }
}
