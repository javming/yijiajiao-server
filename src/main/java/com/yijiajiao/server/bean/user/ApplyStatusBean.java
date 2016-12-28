package com.yijiajiao.server.bean.user;

public class ApplyStatusBean {
	
	private String userOpenId;
	private Integer applyStatus=1001;
	
	public String getUserOpenId() {
		return userOpenId;
	}
	public void setUserOpenId(String userOpenId) {
		this.userOpenId = userOpenId;
	}
	public Integer getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}
	
	

}
