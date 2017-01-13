package com.ymatou.alarmcenter.infrastructure.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ymatou.alarmcenter.infrastructure.serialize.DateDeserializer;
import com.ymatou.alarmcenter.infrastructure.serialize.DateSerializer;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Date;

/**
 * 自定义的JSON转换MAPPER
 */
@Configuration
public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper() {
        super();
        //设置null转换""
        //getSerializerProvider().setNullValueSerializer(new NullSerializer());
        //设置日期转换yyyy-MM-dd HH:mm:ss

        // 对enum使用toString()方法序列化 如果没有重写toString方法则默认使用Enum.name() 与名称一样
        //configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING,true);
        // 对enum反序列化使用 toString()
        //configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING,true);

        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
        SimpleModule module = new SimpleModule();
        module.addSerializer(Date.class, new DateSerializer());
        module.addDeserializer(Date.class, new DateDeserializer());
        registerModule(module);
    }

    //null的JSON序列
    private class NullSerializer extends JsonSerializer<Object> {
        public void serialize(Object value, JsonGenerator jgen,
                              SerializerProvider provider) throws IOException {
            jgen.writeString("");
        }
    }
}