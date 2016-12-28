package com.yijiajiao.server.bean;

import java.util.List;

public class IdBean {
	private int msgType;
	private List<Long> ids;

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}


}
