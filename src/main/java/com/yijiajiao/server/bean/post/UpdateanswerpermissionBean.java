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
public class UpdateanswerpermissionBean {

	private String answerUrl;
	private String answerInfo;
	private String userAnswerUrl;
	private String userAnswerInfo;
	private String userOpenId;
	private int type;
	private String subjectCode;
	private String subjectName;
	private String stageCode;
	private String stageName;
	private List<Knowledge> userKnowledge;

}
