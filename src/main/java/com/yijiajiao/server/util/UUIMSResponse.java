package com.yijiajiao.server.util;

public class UUIMSResponse {
  private String requestId;
  private String code;
  private String message;
   private String httpCode;
   private Object result;
  public String getRequestId() {
    return requestId;
  }
  public void setRequestId(String requestId) {
    this.requestId = requestId;
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
  public String getHttpCode() {
    return httpCode;
  }
  public void setHttpCode(String httpCode) {
    this.httpCode = httpCode;
  }
  public Object getResult() {
    return result;
  }
  public void setResult(Object result) {
    this.result = result;
  }
   
   
}
