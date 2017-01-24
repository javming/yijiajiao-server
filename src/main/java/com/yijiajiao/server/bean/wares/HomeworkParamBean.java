package com.yijiajiao.server.bean.wares;

public class HomeworkParamBean {
	private int id;
	private String type;
	private String paperId;

	public HomeworkParamBean() {
	}

	public HomeworkParamBean(int id, String type, String paperId) {
		this.id = id;
		this.type = type;
		this.paperId = paperId;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
