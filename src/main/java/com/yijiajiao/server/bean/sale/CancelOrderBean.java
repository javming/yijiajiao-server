package com.yijiajiao.server.bean.sale;

public class CancelOrderBean {
private String status;//calloff,delete
private int id;
private String cancelReason;
private String cancelNeiRong;

public String getCancelNeiRong() {
  return cancelNeiRong;
}
public void setCancelNeiRong(String cancelNeiRong) {
  this.cancelNeiRong = cancelNeiRong;
}
public String getStatus() {
  return status;
}
public void setStatus(String status) {
  this.status = status;
}
public int getId() {
  return id;
}
public void setId(int id) {
  this.id = id;
}
public String getCancelReason() {
  return cancelReason;
}
public void setCancelReason(String cancelReason) {
  this.cancelReason = cancelReason;
}

}
