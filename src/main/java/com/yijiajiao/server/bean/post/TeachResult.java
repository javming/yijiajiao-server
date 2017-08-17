package com.yijiajiao.server.bean.post;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeachResult {
	
	private String openId;
	private String teachResultTime;
	private String teachResultName;//教学成果名称
	private String teachResultDescription;//教学成果描述

}
