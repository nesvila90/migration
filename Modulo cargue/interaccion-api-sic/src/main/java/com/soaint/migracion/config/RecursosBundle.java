package com.soaint.migracion.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class RecursosBundle {
    private static Logger LOGGER = LogManager.getLogger(RecursosBundle.class.getName());
    private static ResourceBundle bundle;

    private RecursosBundle() {
    }

    public static String getProperty(final String key) {


        return bundle.getString(key);
    }

    static {
        try {
            bundle = ResourceBundle.getBundle("endpoint");
        } catch (Exception var1) {
            LOGGER.error("Bundle Util - a system error has occurred", var1);
            throw new RuntimeException(var1);
        }
    }
}
