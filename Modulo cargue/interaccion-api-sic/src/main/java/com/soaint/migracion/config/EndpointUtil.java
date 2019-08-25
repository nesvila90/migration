package com.soaint.migracion.config;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class EndpointUtil {

    private static Logger LOGGER = LogManager.getLogger(EndpointUtil.class.getName());
    private static ResourceBundle bundle;

    public EndpointUtil() {
    }

    public static String getMessage(String key) {
        return bundle.getString(key);
    }

    static {
        try {
            bundle = ResourceBundle.getBundle("endpoint");
        } catch (Exception var1) {
            LOGGER.error("Endpoint Util - a system error has occurred", var1);
            throw new RuntimeException(var1);
        }
    }

}
