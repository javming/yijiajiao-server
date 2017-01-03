package com.yijiajiao.server.bean.user;

public class DelCollectBean {

	private String userOpenId;
	private String ids;

	public DelCollectBean() {
	}

	public DelCollectBean(String userOpenId, String ids) {
		this.userOpenId = userOpenId;
		this.ids = ids;
	}

	public String getUserOpenId() {
		return userOpenId;
	}

	public void setUserOpenId(String userOpenId) {
		this.userOpenId = userOpenId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
