package com.yijiajiao.server.bean.post;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateExamBean {

	private String subjectCode;
	private String subject;
	private String gradeCode;
	private String grade;
	private String booktype;
	private int useType;
	private int totalTime;
	private String paperName;
	private String paperDesc;
	private int totalScore;
	private String teacherId;
	private int use;
	private int star;
	private int questionCount;

}
