package com.yijiajiao.server.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanUserBean {

    private String telephone="";
    private String openId="";
    private String client_id="";
    private String version;

}
