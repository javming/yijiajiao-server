package com.yijiajiao.server.bean.solution;

public class CountTimeBean {

    private int id;
    private double countTime;
    private String communicateTime;

    public CountTimeBean() {}

    public CountTimeBean(int id, double countTime, String communicateTime) {
      this.id = id;
      this.countTime = countTime;
      this.communicateTime = communicateTime;
    }

    public String getCommunicateTime() {
	return communicateTime;
}
    public void setCommunicateTime(String communicateTime) {
	this.communicateTime = communicateTime;
}
    public int getId() {
    return id;
  }
    public void setId(int id) {
    this.id = id;
  }
    public double getCountTime() {
    return countTime;
  }
    public void setCountTime(double countTime) {
    this.countTime = countTime;
  }
  
}
