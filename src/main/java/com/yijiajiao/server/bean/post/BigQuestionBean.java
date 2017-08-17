package com.yijiajiao.server.bean.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BigQuestionBean {

	private String itemContent;
	private int sort;
	private List<SmallQuestionBean> smallQuestions= new ArrayList<SmallQuestionBean>();
	private String itemType;

}
