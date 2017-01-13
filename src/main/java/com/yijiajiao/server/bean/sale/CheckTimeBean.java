package com.yijiajiao.server.bean.sale;

public class CheckTimeBean {

  private String openId;
  private String startTime;
  private String endTime;

  public CheckTimeBean() {
  }

  public CheckTimeBean(String openId, String startTime, String endTime) {
    this.openId = openId;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public String getOpenId() {
    return openId;
  }
  public void setOpenId(String openId) {
    this.openId = openId;
  }
  public String getStartTime() {
    return startTime;
  }
  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }
  public String getEndTime() {
    return endTime;
  }
  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }
  
}
