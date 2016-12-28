package com.yijiajiao.server.bean.sale;

public class StuAppraiseQueryBean {


private String openId;
private int pageNo = 1;
private int pageSize;
private String appraiseType;
private String curriculumType;

public String getOpenId() {
  return openId;
}
public void setOpenId(String openId) {
  this.openId = openId;
}

public int getPageNo() {
	return pageNo;
}
public void setPageNo(int pageNo) {
	this.pageNo = pageNo;
}
public int getPageSize() {
  return pageSize;
}
public void setPageSize(int pageSize) {
  this.pageSize = pageSize;
}
public String getAppraiseType() {
  return appraiseType;
}
public void setAppraiseType(String appraiseType) {
  this.appraiseType = appraiseType;
}

public String getCurriculumType() {
  return curriculumType;
}
public void setCurriculumType(String curriculumType) {
  this.curriculumType = curriculumType;
}

    
  
}
