package com.yijiajiao.server.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 登录Bean
 * 
 * 
 */
@XmlRootElement
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginBean {

    private String telephone;
    private String password;
    private String client_id;
    private String version;// 1.0亿家教自用户  2.0阳光保险

}
