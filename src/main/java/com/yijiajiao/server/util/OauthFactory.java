package com.yijiajiao.server.util;

import com.eeduspace.uuims.api.OauthClient;

public class OauthFactory {

 private static String url = Config.uuimsString("url");
 private static String ak = Config.uuimsString("ak");
 private static String sk = Config.uuimsString("sk");
 private OauthClient client = null;
  
  
  public OauthFactory() {
  }
  public OauthClient getInteance(){
    client = new OauthClient(url,ak,sk);
    return client;
  }
  
  public OauthClient getUpdatePassInteance(String ak,String sk,String token){
    client = new OauthClient(url,ak,sk,token);
    return client;
  }
}
