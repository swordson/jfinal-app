/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */

package com.jfinal.initalizer.interceptors;


import com.google.common.base.Strings;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.syslog.LogConfig;
import com.jfinal.ext.interceptor.syslog.LogProcessor;
import com.jfinal.ext.interceptor.syslog.SysLog;

import java.util.Map;
import java.util.Set;

/**
 * <p>
 * .
 * </p>
 *
 * @author walter yang
 * @version 1.0 2013-12-12 3:45
 * @since JDK 1.5
 */
public class  SystemLogProcessor implements LogProcessor {
    @Override
    public void process(SysLog sysLog) {
        // todo save log into db or other storage.
    }

    @Override
    public String getUsername(Controller c) {
        // todo get login user.
        return null;
    }

    @Override
    public String formatMessage(LogConfig config, Map<String, String> message) {
        boolean isFormat = Strings.isNullOrEmpty(config.getTitle());
        String result = isFormat ? config.getFormat() : config.getTitle();
        if (message.isEmpty()) {
            return result;
        }
        if (isFormat) {
            Set<Map.Entry<String, String>> entrySet = message.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                String key = entry.getKey();
                String value = entry.getValue();
                result = result.replace("{" + key + "}", value);
            }
        } else {
            result += ", ";
            Set<Map.Entry<String, String>> entrySet = message.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                String key = entry.getKey();
                String value = entry.getValue();
                result += key + ":" + value;
            }
        }
        return result;
    }
}