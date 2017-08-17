package com.yijiajiao.server.bean.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmartCreateExamBean {

	private String teacherId;
	private String subjectCode;
	private String gradeCode;
	private String bookTypeCode;
	private String subject;
	private String grade;
	private String bookType;
	private int use;
	private int examTime;
	private String examName;
	private String examDescribe;
	private int totalScore;
	private int questionCount;
	private String knowledges;
	private int examType;

}
