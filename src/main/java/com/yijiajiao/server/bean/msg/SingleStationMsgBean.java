package com.yijiajiao.server.bean.msg;

public class SingleStationMsgBean {
	private int textType;
	private int modelId;
	private String headerText;
	private String msg;
	private String senderId;
	private String telPhone;
	public int getTextType() {
		return textType;
	}
	public void setTextType(int textType) {
		this.textType = textType;
	}
	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	public String getHeaderText() {
		return headerText;
	}
	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getTelPhone() {
		return telPhone;
	}
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	
	
}
