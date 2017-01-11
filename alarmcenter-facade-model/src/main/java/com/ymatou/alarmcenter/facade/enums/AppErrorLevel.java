package com.ymatou.alarmcenter.facade.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangxiaoming on 2016/11/23.
 */
public enum AppErrorLevel {

    /**
     * 致命异常 1
     */
    Fatal(1, "致命异常"),

    /**
     * 错误异常 2
     */
    Error(2, "错误异常"),


    /*
     * 警告异常 3
     */
    Warning(3, "警告异常");

    /**
     * 枚举值
     */
    private int code;

    /**
     * 枚举描述
     */
    private String message;


    AppErrorLevel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取枚举值
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * 获取枚举描述
     *
     * @return
     */
    public String getMessage() {
        return message;
    }


    /**
     * 通过代码获取枚举项
     *
     * @param code
     * @return
     */
    public static AppErrorLevel getByCode(int code) {
        for (AppErrorLevel appErrorLevel : AppErrorLevel.values()) {
            if (appErrorLevel.getCode() == code) {
                return appErrorLevel;
            }
        }
        return null;
    }

    /**
     * 通过名称获取枚举项
     *
     * @param name 枚举名称
     * @return
     */
    public static AppErrorLevel getByName(String name) {
        for (AppErrorLevel appErrorLevel : AppErrorLevel.values()) {
            if (appErrorLevel.name().equalsIgnoreCase(name)) {
                return appErrorLevel;
            }
        }
        return null;
    }

    /**
     * 通过枚举值或枚举名称获取枚举项
     *
     * @param value 枚举值或枚举名称
     * @return
     */
    @JsonCreator
    public static AppErrorLevel getValue(String value) {
        if (StringUtils.isNumeric(value))
            return getByCode(NumberUtils.toInt(value, 0));
        else
            return getByName(value);
    }

    public static Map<Integer, String> toMap() {
        Map<Integer, String> map = new HashMap<>();
        for (AppErrorLevel appErrorLevel : AppErrorLevel.values()) {
            map.put(appErrorLevel.getCode(), appErrorLevel.getMessage());
        }
        return map;
    }
}
