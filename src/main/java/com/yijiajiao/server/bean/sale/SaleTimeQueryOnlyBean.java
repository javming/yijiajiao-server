package com.yijiajiao.server.bean.sale;

public class SaleTimeQueryOnlyBean {
    private String openId;
    private String startTime;

    public SaleTimeQueryOnlyBean() {}

    public SaleTimeQueryOnlyBean(String openId, String startTime) {
        this.openId = openId;
        this.startTime = startTime;
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
  
}
