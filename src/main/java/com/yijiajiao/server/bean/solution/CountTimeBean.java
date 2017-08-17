package com.yijiajiao.server.bean.solution;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountTimeBean {

    private int id;
    private double countTime;
    private String communicateTime;

}
