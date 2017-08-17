package com.yijiajiao.server.bean.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppraiseSolutionBean {

	private int solId;// 答疑id
	private int communicating;// 沟通互动
	private int enlighten;// 启发引导
	private int solveEffect;// 解答效果
	private String content="";// 评价内容
	private int anonymity;// 0不是匿名，1是匿名

}
