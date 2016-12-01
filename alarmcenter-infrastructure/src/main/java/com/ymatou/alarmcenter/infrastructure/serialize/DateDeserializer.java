package com.ymatou.alarmcenter.infrastructure.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.io.IOException;
import java.util.Date;

/**
 * Created by zhangxiaoming on 2016/11/14.
 */
public class DateDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        String value = jp.getValueAsString();
        if(StringUtils.isBlank(value))
            return null;
        if(value.contains(".")){
            value=StringUtils.split(value,".")[0];
        }
        DateTime dt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss").parseDateTime(value);
        return dt.toDate();
    }
}
