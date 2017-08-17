package com.yijiajiao.server.bean.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-09-11:26
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddCouponBean {

    private int type;//0:平台优惠券 1：工作室优惠券
    private String name;// 代金券名称
    private double value;// 优惠券面值
    private int amount;// 开通数量
    private int usageRange; //适用类型0：工作室全部课程1：工作室指定课程
    private String waresids;// 优惠券指定课程的id多个课程以"_"隔开
    private int usageCondition;// 使用条件0：无条件 1：满足最低使用价格
    private double minPrice; //最低使用价格
    private String startDate;// 开始时间
    private String endDate;// 结束时间
    private String teacherOpenid;// 工作室教师openid
    private String description;// 描述

}
