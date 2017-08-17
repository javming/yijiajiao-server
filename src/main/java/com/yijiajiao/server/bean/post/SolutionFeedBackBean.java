package com.yijiajiao.server.bean.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolutionFeedBackBean {

	private int solId;// 答疑id
	private int refuseId;// 反馈基础信息id
	private String description;// 反馈理由描述
	private String openId; //教师openid

}
