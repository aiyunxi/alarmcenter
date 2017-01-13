package com.ymatou.alarmcenter.infrastructure.serialize;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.ymatou.library.datetimeparse.DateTimeParse;

import java.io.IOException;
import java.util.Date;

/**
 * Created by zhangxiaoming on 2016/11/14.
 */
public class DateDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        String value = jp.getValueAsString();
        return DateTimeParse.parse(value);
    }
}
