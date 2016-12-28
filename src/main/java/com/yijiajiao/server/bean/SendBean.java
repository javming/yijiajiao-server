package com.yijiajiao.server.bean;

public class SendBean {

  private String openId;
  private String password; 
  private String requestId = "a";
  private String phone;
  private String sendType;
  public String getOpenId() {
    return openId;
  }
  public void setOpenId(String openId) {
    this.openId = openId;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public String getSendType() {
    return sendType;
  }
  public void setSendType(String sendType) {
    this.sendType = sendType;
  }
public String getRequestId() {
	return requestId;
}
public void setRequestId(String requestId) {
	this.requestId = requestId;
}
  
  
}
