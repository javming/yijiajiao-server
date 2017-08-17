package com.yijiajiao.server.bean.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanRegisterUserBean {

    private String accessKey;
    private String secretKey;
    private String openId;
    private String accd;
    private String imToken;
    private String imName;
    private String easeobName;
    private String mobile;

}
