package com.yijiajiao.server.bean.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamDetail {

	private String examCode;
	private String examName;
	private int isdel;
	private int pageId;
	private String qtype;
	private String questionCode;
	private int score;
	private int time;

}
