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
public class DiagnoseAnswerSubmitBean {

	private String openId;
	private String paperId;
	private String waresId;
	private String waresSlaveId;
	private String subjectCode;
	private Integer submitType;//1 课中练习 ，2 课后作业
	private Integer groupCode;//试卷分类 (0 未分类 1自由学类)  该项不传默认设置为0
	private List<DiagnoseAnswerBean> list= new ArrayList<>();

}
