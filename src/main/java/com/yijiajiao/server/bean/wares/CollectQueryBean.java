package com.yijiajiao.server.bean.wares;

public class CollectQueryBean {
private String userOpenId;
private String courseId;
private int type;
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public String getUserOpenId() {
	return userOpenId;
}
public void setUserOpenId(String userOpenId) {
	this.userOpenId = userOpenId;
}
public String getCourseId() {
	return courseId;
}
public void setCourseId(String courseId) {
	this.courseId = courseId;
}

}
