package com.yijiajiao.server.bean.solution;

public class ExitGrabBean {

  private String openId;
  private int id;

  public ExitGrabBean() {
  }

  public ExitGrabBean(String openId, int id) {
    this.openId = openId;
    this.id = id;
  }

  public String getOpenId() {
return openId;
}
  public void setOpenId(String openId) {
this.openId = openId;
}
  public int getId() {
return id;
}
  public void setId(int id) {
this.id = id;
}
  
}
