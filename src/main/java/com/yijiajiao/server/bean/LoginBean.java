package com.yijiajiao.server.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 登录Bean
 * 
 * 
 */
@XmlRootElement
public class LoginBean {
  private String telephone;
  private String password;
  private String client_id;
  private String version;


  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getClient_id() {
    return client_id;
  }

  public void setClient_id(String client_id) {
    this.client_id = client_id;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
