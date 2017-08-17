package com.yijiajiao.server.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SyncOrderInfo {

    private String userCode; //用户code
    private String orderNum; //订单号
    private String commodityId; // 商品id
    private double orderPrice; // 订单价格
    private int commodityType;// 商品类型:(1课程,2答疑,3诊断,4诊断）
    private String teacherId; //教师用户id（商品为诊断时可不传）
    private String diagnosisGoodsDetailCode;//诊断商品code
    private String diagnosisGoodsCode;//诊断商品code
    private String multiPaperCode;//综合试卷code
    private String diagnosticRecordsName;//诊断商品名称
    private List<SlaveIdBean> slaves;
    private String examStartDate;
    private String examEndDate;
    private int orderSource;// 订单来源：0易家教 1阳光 2 5184(网站)
    private String used;//(保分计划诊断)
    private Double discountPrice;//折扣后价格
    private String discountYard;// 优惠码
    private String price;//原价

}
