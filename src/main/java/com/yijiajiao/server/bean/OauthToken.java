package com.yijiajiao.server.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 登录返回的Token
 * 
 * @author songbw
 * 
 */
@XmlRootElement
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OauthToken {

  private String access_token;

  private int    expires_in;

  private String refresh_token;

}
