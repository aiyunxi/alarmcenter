package com.ymatou.alarmcenter.facade.rest;

/**
 * Created by zhangxiaoming on 2016/11/29.
 */
public interface SystemResource {
    /**
     * 返回系统状态
     *
     * @return
     */
    String status();

    /**
     * 返回系统版本
     *
     * @return
     */
    String version();

    /**
     * 默认页
     * @return
     */
    String index();
}
