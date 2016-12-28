package com.yijiajiao.server.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StatusBean {

  private String status = "success";

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}