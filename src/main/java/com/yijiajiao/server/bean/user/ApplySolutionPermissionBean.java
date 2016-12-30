package com.yijiajiao.server.bean.user;

public class ApplySolutionPermissionBean {

  private String subjectCode;
  private String stageCode;

  public ApplySolutionPermissionBean() {
  }

  public ApplySolutionPermissionBean(String subjectCode, String stageCode) {
    this.subjectCode = subjectCode;
    this.stageCode = stageCode;
  }


  public String getSubjectCode() {
    return subjectCode;
  }

  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }

  public String getStageCode() {
    return stageCode;
  }

  public void setStageCode(String stageCode) {
    this.stageCode = stageCode;
  }

  
}
