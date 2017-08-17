package com.yijiajiao.server.bean.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateAppraiseBean {

	private int orderId;
	private String openId;
	private double appraiseCause1;
	private double appraiseCause2;
	private double appraiseCause3;
	private String appraiseContent;
	private int isAnonymity;// 0不是匿名1是匿名

}
