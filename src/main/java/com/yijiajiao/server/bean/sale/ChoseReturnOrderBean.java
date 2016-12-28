package com.yijiajiao.server.bean.sale;

public class ChoseReturnOrderBean {

  private String orderNumber;
  private int refundType;
  public String getOrderNumber() {
    return orderNumber;
  }
  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }
  public int getRefundType() {
    return refundType;
  }
  public void setRefundType(int refundType) {
    this.refundType = refundType;
  }
  
}
