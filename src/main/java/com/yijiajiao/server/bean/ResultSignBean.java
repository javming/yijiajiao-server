package com.yijiajiao.server.bean;

public class ResultSignBean {
  public String requestId ="";
  public String httpCode = "";
   public String code;
   public String message;
   public Object  result;
public String getRequestId() {
	return requestId;
}
public void setRequestId(String requestId) {
	this.requestId = requestId;
}
public String getHttpCode() {
	return httpCode;
}
public void setHttpCode(String httpCode) {
	this.httpCode = httpCode;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public Object getResult() {
	return result;
}
public void setResult(Object result) {
	this.result = result;
}

   
}
