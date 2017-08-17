package com.yijiajiao.server.bean.wares;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-11-15-11:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BackUrlInfoParamBean {

    private String mtgKey;
    private String userName;
    private String configFile;
    private Integer type;

}
