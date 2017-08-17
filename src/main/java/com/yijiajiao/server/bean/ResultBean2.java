package com.yijiajiao.server.bean;

import java.util.UUID;


public class ResultBean2 {
public String requestId =UUID.randomUUID().toString().replace("-", "");
public String httpCode = "";
public String code;
public String message;
public CodeBean result = null;

public String getCode() {
return code;
}
private void setCode(String code) {
this.code = code;
}
public String getMessage() {
return message;
}
private void setMessage(String message) {
this.message = message;
}


public CodeBean getResult() {
return result;
}
public void setResult(CodeBean result) {
this.result = result;
}
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

}
