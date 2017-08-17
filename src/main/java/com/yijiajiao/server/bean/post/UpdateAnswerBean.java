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
public class UpdateAnswerBean {

	private int stuId;
	private List<Knowledge> knowledgelist;
	private List<AnalysisProcessBean> analysisProcess;
	private String description;
	private String openId;

}
