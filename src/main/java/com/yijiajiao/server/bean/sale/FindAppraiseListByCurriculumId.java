package com.yijiajiao.server.bean.sale;

public class FindAppraiseListByCurriculumId {

  private int curriculumId;
  private int pageNo;
  private int pageSize;
  public int getCurriculumId() {
    return curriculumId;
  }
  public void setCurriculumId(int curriculumId) {
    this.curriculumId = curriculumId;
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
  
}
