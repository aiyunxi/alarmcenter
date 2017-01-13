package com.ymatou.alarmcenter.admin.web.config;

import com.ymatou.library.datetimeparse.DateTimeParse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * Created by zhangxiaoming on 2017/1/13.
 */
@Configuration
public class StringToDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        if (StringUtils.isBlank(s))
            return null;
        return DateTimeParse.parse(s);
    }
}


