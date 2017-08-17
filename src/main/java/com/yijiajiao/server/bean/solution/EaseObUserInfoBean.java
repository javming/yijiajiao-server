package com.yijiajiao.server.bean.solution;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EaseObUserInfoBean {

    private String username;
    private String password;
    private String nickname;

}
