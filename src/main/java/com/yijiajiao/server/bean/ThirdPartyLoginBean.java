package com.yijiajiao.server.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 第三方登录实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThirdPartyLoginBean {

    private String telephone;
    private String password;
    private String client_id;
    private String version;// 1.0 亿家教自用户  2.0阳光保险用户 3.0中兴用户
    private String thirdPartyUserCode;//第三方用户code

}
