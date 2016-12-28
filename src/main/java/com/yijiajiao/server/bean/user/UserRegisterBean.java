package com.yijiajiao.server.bean.user;

public class UserRegisterBean {

private String username;
private String ak;
private String sk;
private String userOpenId;
private String invite_code;
private String invite_selfcode;
private String registDate;
private String version;
public String getUsername() {
  return username;
}
public void setUsername(String username) {
  this.username = username;
}
public String getAk() {
  return ak;
}
public void setAk(String ak) {
  this.ak = ak;
}
public String getSk() {
  return sk;
}
public void setSk(String sk) {
  this.sk = sk;
}
public String getUserOpenId() {
  return userOpenId;
}

public String getInvite_selfcode() {
  return invite_selfcode;
}
public void setInvite_selfcode(String invite_selfcode) {
  this.invite_selfcode = invite_selfcode;
}
public void setUserOpenId(String userOpenId) {
  this.userOpenId = userOpenId;
}
public String getInvite_code() {
  return invite_code;
}
public void setInvite_code(String invite_code) {
  this.invite_code = invite_code;
}
public String getVersion() {
  return version;
}
public void setVersion(String version) {
  this.version = version;
}
public String getRegistDate() {
  return registDate;
}
public void setRegistDate(String registDate) {
  this.registDate = registDate;
}





}
