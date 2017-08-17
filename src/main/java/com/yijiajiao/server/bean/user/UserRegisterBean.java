package com.yijiajiao.server.bean.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterBean {

    private String username;
    private String ak;
    private String sk;
    private String userOpenId;
    private String invite_code;
    private String invite_selfcode;
    private String registDate;
    private String version;

}
