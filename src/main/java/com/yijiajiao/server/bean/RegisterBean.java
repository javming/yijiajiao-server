package com.yijiajiao.server.bean;

public class RegisterBean {

  private String  telephone;
  private String  password; //md5
  private String  verify_code;
  private String  invite_code;
   public String getVerify_code() {
    return verify_code;
  }
  public void setVerify_code(String verify_code) {
    this.verify_code = verify_code;
  }
  private String client_id;// vip
   private String version;
   
   
  public String getInvite_code() {
    return invite_code;
  }
  public void setInvite_code(String invite_code) {
    this.invite_code = invite_code;
  }
  public String getTelephone() {
    return telephone;
  }
  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getClient_id() {
    return client_id;
  }
  public void setClient_id(String client_id) {
    this.client_id = client_id;
  }
  public String getVersion() {
    return version;
  }
  public void setVersion(String version) {
    this.version = version;
  }
   
   
}
