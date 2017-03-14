/*
 *
 *  (C) Copyright 2017 Ymatou (http://www.ymatou.com/).
 *  All rights reserved.
 *
 */
package com.ymatou.alarmcenter.facade.common;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

/**
 * @author luoshiqian
 */
public class DynamicTraceBinding implements DynamicFeature {

    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        context.register(DynamicTraceInterceptor.class);
    }
}