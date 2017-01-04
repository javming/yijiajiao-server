package com.yijiajiao.server.bean.solution;

public class ExitSolutionBean {

  private int id;
  private int grabStatus;

  public ExitSolutionBean() {
  }

  public ExitSolutionBean(int id, int grabStatus) {
    this.id = id;
    this.grabStatus = grabStatus;
  }

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public int getGrabStatus() {
    return grabStatus;
  }
  public void setGrabStatus(int grabStatus) {
    this.grabStatus = grabStatus;
  }
}
