package com.yijiajiao.server.bean.wares;

public class PopularityBean {
	private String popType;
	private int   waresId;

	public PopularityBean() {
	}

	public PopularityBean(String popType, int waresId) {
		this.popType = popType;
		this.waresId = waresId;
	}

	public String getPopType() {
		return popType;
	}
	public void setPopType(String popType) {
		this.popType = popType;
	}
	public int getWaresId() {
		return waresId;
	}
	public void setWaresId(int waresId) {
		this.waresId = waresId;
	}
	
}
