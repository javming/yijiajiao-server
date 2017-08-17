package com.yijiajiao.server.bean.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author  zhaoming
 * @since  2017-01-09-17:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateActivityBean {

    private int id;// 活动id
    private String startDate; // 开始时间
    private String endDate;// 结束时间
    private String description;
    private String name; //活动名称
    private String value;// 折扣值
    private String courseIds;
    
}
