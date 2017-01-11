package com.ymatou.alarmcenter.admin.web.servcie;

import com.ymatou.alarmcenter.domain.model.AppBaseConfig;
import com.ymatou.alarmcenter.domain.repository.AppBaseConfigRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangxiaoming on 2017/1/10.
 */
@Service
public class CommonServcie {
    @Resource
    private AppBaseConfigRepository appBaseConfigRepository;

    public Map<String, String> getAllAppIdMap() {
        Map<String, String> map = new HashMap<>();
        map.put(null, "请选择应用编号");
        List<AppBaseConfig> appBaseConfigs = appBaseConfigRepository.getAppBaseConfigList();
        if (appBaseConfigs != null) {
            for (AppBaseConfig appBaseConfig : appBaseConfigs) {
                if (appBaseConfig == null)
                    continue;
                map.put(appBaseConfig.getAppId(), appBaseConfig.getAppId());
            }
        }
        return map;
    }
}
