package com.ymatou.alarmcenter.domain.config;

import com.baidu.disconf.client.DisConf;
import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by zhangxiaoming on 2016/12/16.
 */
@Component
@DisconfUpdateService(confFileKeys = {"whitelist.properties"})
public class WhitelistConfig implements IDisconfUpdate {

    private Properties properties;

    @Override
    public void reload() throws Exception {
        File mqProperties = DisConf.getLocalConfig("whitelist.properties");
        Properties props = new Properties();
        props.load(new FileInputStream(mqProperties));
        properties = props;
    }

    public String getValue(String key) {
        if (properties == null)
            return "";
        return properties.getProperty(key, "");
    }
}
