package com.yijiajiao.server.bean.sale;

public class OrderQueryBean {
	
	private int pageNo;
	private int pageSize;
	private String status;
	private String openId;
	private int curriculumType;

	public OrderQueryBean(int pageNo, int pageSize, String status, String openId, int curriculumType) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.status = status;
		this.openId = openId;
		this.curriculumType = curriculumType;
	}

	public OrderQueryBean() {

	}

	public int getCurriculumType() {
		return curriculumType;
	}
	public void setCurriculumType(int curriculumType) {
		this.curriculumType = curriculumType;
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
	public String getStatus() {
	  return status;
	}
	public void setStatus(String status) {
	  this.status = status;
	}
	public String getOpenId() {
	  return openId;
	}
	public void setOpenId(String openId) {
	  this.openId = openId;
	}
  
}
