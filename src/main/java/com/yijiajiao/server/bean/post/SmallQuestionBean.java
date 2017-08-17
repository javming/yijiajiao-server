package com.yijiajiao.server.bean.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmallQuestionBean {

	private int sort;
	private String smallQuestionId;
	private String type;
	private String answer;
	private int score;

}
