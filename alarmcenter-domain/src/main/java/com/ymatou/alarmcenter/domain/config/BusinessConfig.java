package com.ymatou.alarmcenter.domain.config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import org.springframework.stereotype.Component;

/**
 * Created by zhangxiaoming on 2016/11/24.
 */
//@Configuration
//public class AppConfig {
//
//    @Value("${defaultSendEmailTimeInterval:3}")
//    private int defaultSendEmailTimeInterval;
//
//    @Value("${defaultSendEmailNumLimit:1}")
//    private int defaultSendEmailNumLimit;
//
//    @Value("${defaultSendSmsTimeInterval:5}")
//    private int defaultSendSmsTimeInterval;
//
//    @Value("${defaultSendSmsNumLimit:1}")
//    private int defaultSendSmsNumLimit;
//
//    @Value("${fromDbCount:true}")
//    private boolean fromDbCount;
//
//    @Value("${devName:DEV}")
//    private String devName;
//
//    @Value("${errorManagerCenterUrl:http://alarmmanager.ymatou.cn/Alarm/Err?errid={0}&time={1}}")
//    private String errorManagerCenterUrl;
//
//    public int getDefaultSendEmailTimeInterval() {
//        return defaultSendEmailTimeInterval;
//    }
//
//    public void setDefaultSendEmailTimeInterval(int defaultSendEmailTimeInterval) {
//        this.defaultSendEmailTimeInterval = defaultSendEmailTimeInterval;
//    }
//
//    public int getDefaultSendEmailNumLimit() {
//        return defaultSendEmailNumLimit;
//    }
//
//    public void setDefaultSendEmailNumLimit(int defaultSendEmailNumLimit) {
//        this.defaultSendEmailNumLimit = defaultSendEmailNumLimit;
//    }
//
//    public int getDefaultSendSmsTimeInterval() {
//        return defaultSendSmsTimeInterval;
//    }
//
//    public void setDefaultSendSmsTimeInterval(int defaultSendSmsTimeInterval) {
//        this.defaultSendSmsTimeInterval = defaultSendSmsTimeInterval;
//    }
//
//    public int getDefaultSendSmsNumLimit() {
//        return defaultSendSmsNumLimit;
//    }
//
//    public void setDefaultSendSmsNumLimit(int defaultSendSmsNumLimit) {
//        this.defaultSendSmsNumLimit = defaultSendSmsNumLimit;
//    }
//
//    public boolean getFromDbCount() {
//        return this.fromDbCount;
//    }
//
//    public void setFromDbCount(boolean fromDbCount) {
//        this.fromDbCount = fromDbCount;
//    }
//
//    public boolean isFromDbCount() {
//        return fromDbCount;
//    }
//
//    public String getDevName() {
//        return devName;
//    }
//
//    public void setDevName(String devName) {
//        this.devName = devName;
//    }
//
//    public String getErrorManagerCenterUrl() {
//        return errorManagerCenterUrl;
//    }
//
//    public void setErrorManagerCenterUrl(String errorManagerCenterUrl) {
//        this.errorManagerCenterUrl = errorManagerCenterUrl;
//    }
//}


@Component
@DisconfFile(
        fileName = "business.properties"
)
public class BusinessConfig {


    public BusinessConfig() {
    }

    private int defaultSendEmailTimeInterval;
    private int defaultSendEmailNumLimit;
    private int defaultSendSmsTimeInterval;
    private int defaultSendSmsNumLimit;
    private String devName;
    private String errorManagerCenterUrl;

    @DisconfFileItem(
            name = "defaultSendEmailTimeInterval"
    )
    public int getDefaultSendEmailTimeInterval() {
        return defaultSendEmailTimeInterval;
    }

    public void setDefaultSendEmailTimeInterval(int defaultSendEmailTimeInterval) {
        this.defaultSendEmailTimeInterval = defaultSendEmailTimeInterval;
    }

    @DisconfFileItem(
            name = "defaultSendEmailNumLimit"
    )
    public int getDefaultSendEmailNumLimit() {
        return defaultSendEmailNumLimit;
    }

    public void setDefaultSendEmailNumLimit(int defaultSendEmailNumLimit) {
        this.defaultSendEmailNumLimit = defaultSendEmailNumLimit;
    }

    @DisconfFileItem(
            name = "defaultSendSmsTimeInterval"
    )
    public int getDefaultSendSmsTimeInterval() {
        return defaultSendSmsTimeInterval;
    }

    public void setDefaultSendSmsTimeInterval(int defaultSendSmsTimeInterval) {
        this.defaultSendSmsTimeInterval = defaultSendSmsTimeInterval;
    }

    @DisconfFileItem(
            name = "defaultSendSmsNumLimit"
    )
    public int getDefaultSendSmsNumLimit() {
        return defaultSendSmsNumLimit;
    }

    public void setDefaultSendSmsNumLimit(int defaultSendSmsNumLimit) {
        this.defaultSendSmsNumLimit = defaultSendSmsNumLimit;
    }

    @DisconfFileItem(
            name = "devName"
    )
    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    @DisconfFileItem(
            name = "errorManagerCenterUrl"
    )
    public String getErrorManagerCenterUrl() {
        return errorManagerCenterUrl;
    }

    public void setErrorManagerCenterUrl(String errorManagerCenterUrl) {
        this.errorManagerCenterUrl = errorManagerCenterUrl;
    }
}
