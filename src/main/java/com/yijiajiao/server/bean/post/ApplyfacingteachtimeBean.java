package com.yijiajiao.server.bean.post;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplyfacingteachtimeBean {

	private String day;
	private String time;
	private String userOpenId;
	private String subjectCode;

	
}
