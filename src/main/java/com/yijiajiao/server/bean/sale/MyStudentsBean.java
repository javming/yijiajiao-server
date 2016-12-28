package com.yijiajiao.server.bean.sale;

public class MyStudentsBean {

  private String teacherId;
  private int  pageNo;
  private int  pageSize;
  private int  curriculumType;
  public String getTeacherId() {
    return teacherId;
  }
  public void setTeacherId(String teacherId) {
    this.teacherId = teacherId;
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
  public int getCurriculumType() {
    return curriculumType;
  }
  public void setCurriculumType(int curriculumType) {
    this.curriculumType = curriculumType;
  }
  
}
