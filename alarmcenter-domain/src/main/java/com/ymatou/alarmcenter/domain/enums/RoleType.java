package com.ymatou.alarmcenter.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by zhangxiaoming on 2016/12/14.
 */
public enum RoleType {
    /*
      * 普通用户 1
      */
    User(1, "普通用户"),


    /*
     * 管理员 2
     */

    Admin(2, "管理员");



    /**
     * 枚举值
     */
    private int code;

    /**
     * 枚举描述
     */
    private String message;


    RoleType(int code, String message) {
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
    public static RoleType getByCode(int code) {
        for (RoleType roleType : RoleType.values()) {
            if (roleType.getCode() == code) {
                return roleType;
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
    public static RoleType getByName(String name) {
        for (RoleType roleType : RoleType.values()) {
            if (roleType.name().equalsIgnoreCase(name)) {
                return roleType;
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
    public static RoleType getValue(String value) {
        if (StringUtils.isNumeric(value))
            return getByCode(NumberUtils.toInt(value, 0));
        else
            return getByName(value);
    }
}
