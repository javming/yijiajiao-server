package com.yijiajiao.server.bean.sale;

public class TeaAppraiseQueryBean {


private String beiId;
private int pageNo = 1;
private int pageSize;
private String appraiseType;
private String curriculumType;
public String getBeiId() {
  return beiId;
}
public void setBeiId(String beiId) {
  this.beiId = beiId;
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
