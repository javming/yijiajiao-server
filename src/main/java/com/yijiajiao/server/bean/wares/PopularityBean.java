package com.yijiajiao.server.bean.wares;

public class PopularityBean {
	private String popType;
	private String waresId;

	public PopularityBean() {
	}

	public PopularityBean(String popType, String waresId) {
		this.popType = popType;
		this.waresId = waresId;
	}

	public String getPopType() {
		return popType;
	}

	public void setPopType(String popType) {
		this.popType = popType;
	}

	public String getWaresId() {
		return waresId;
	}

	public void setWaresId(String waresId) {
		this.waresId = waresId;
	}
}
