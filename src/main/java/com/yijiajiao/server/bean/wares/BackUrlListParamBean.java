package com.yijiajiao.server.bean.wares;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BackUrlListParamBean {

    private String mtgKey;
    private String pageNo;
    private String pageSize;
    private Integer type;

}
