package com.yijiajiao.server.bean.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-09-16:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddActivityBean {

    private String teacherOpenid;
    private String name; //活动名称
    private String isExclude;// 是否排斥 Y：是N：否,用来判断多个促销是否同时可用
    private String priority; //优先级，多个促销活动时使用
    private String startDate; // 开始时间
    private String endDate;// 结束时间
    private String waresids;// 活动指定课程的id多个以"_"隔开
    private String description;// 描述
    private String value;// 折扣值

}
