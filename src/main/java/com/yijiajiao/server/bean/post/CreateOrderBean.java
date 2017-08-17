package com.yijiajiao.server.bean.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderBean {

	private String order_number;
	private String commodity_id;
	private double order_price;
	private String open_id;
	private int commodityType;// 商品类型:(1课程2答疑3诊断4保分计划诊断）
	private String diagnosisGoodsDetailCode;//诊断商品code
	private String diagnosisGoodsCode;//诊断商品code
	private String multiPaperCode;//综合试卷code
	private String diagnosticRecordsName;//诊断商品名称
	private List<IdBean> slaves;
	private String used;//(保分计划诊断)
	private double discountPrice;//折扣后价格
	private String examStartDate;
	private String examEndDate;
	private String discountYard;// 优惠码
	private String price;//原价
	private int sunshine;//1阳光 0易家教
	private String teacherId;
	private String studentCode;
	private String inviteCodeUser;//邀请码（用户）
	private String inviteCodeCompany;//邀请码（商户）

}
