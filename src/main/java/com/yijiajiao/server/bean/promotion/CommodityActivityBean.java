package com.yijiajiao.server.bean.promotion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-04-13-11:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommodityActivityBean {

    private Integer activityId;
    private String courseId;
    private String startDate ="";
    private String endDate="";
    private double value;

}
