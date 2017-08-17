package com.yijiajiao.server.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterBean {

  private String  telephone;
  private String  password; //md5
  private String  verify_code;
  private String  invite_code;
  private String client_id;// vip
  private String version;

}
