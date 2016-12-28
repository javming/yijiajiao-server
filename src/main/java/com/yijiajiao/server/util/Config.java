package com.yijiajiao.server.util;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Config {
  /**
   * 
   * @author davis.shi
   */
  private static final ResourceBundle config = ResourceBundle.getBundle("config");
  
  
  private static final ResourceBundle uuims_config = ResourceBundle.getBundle("uuims");

  /**
     *
     */
  private Config() {
  }
  
  /**
   * 
   * @param key
   * @return
   */
  public static String getString(String key) {
    return config.getString(key);
  }

  public static int getInt(String key) {
    String temp = config.getString(key);
    int value = Integer.parseInt(temp);
    return value;
  }
  
  
  /**
   * uuims配置文件
   * @param key
   * @return
   */
  public static String uuimsString(String key) {
    return uuims_config.getString(key);
  }

  public static int uuimsInt(String key) {
    String temp = uuims_config.getString(key);
    int value = Integer.parseInt(temp);
    return value;
  }
 
}