package com.yijiajiao.server.bean.post;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRefundBean {

	private int refundType;
	private String orderNumber;
	private String openId;
	private String refundInfo;

}
