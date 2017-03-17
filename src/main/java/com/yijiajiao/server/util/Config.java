package com.yijiajiao.server.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class Config {
    private static final ResourceBundle baseConfig = ResourceBundle.getBundle("config");
    private static final Properties config = new Properties();
    private static final Properties uuims_config = new Properties();

    static {
        try {
            config.load(new FileInputStream(baseConfig.getString("localConfig")));
            uuims_config.load(new FileInputStream(baseConfig.getString("uuimsConfig")));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Config() {}

    public static String getString(String key) {
        return config.getProperty(key);
    }

    public static Integer getInt(String key) {
        return Integer.parseInt(config.getProperty(key));
    }

    public static String getBaseString(String key){
        return baseConfig.getString(key);
    }

    /**
     * uuims配置文件
     */
    public static String uuimsString(String key) {
        return uuims_config.getProperty(key);
    }

    public static int uuimsInt(String key) {
        String temp = uuims_config.getProperty(key);
        return Integer.parseInt(temp);

    }

    public static void main(String[] args) {

    }

}