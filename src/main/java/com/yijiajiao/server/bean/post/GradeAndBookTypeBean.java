package com.yijiajiao.server.bean.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradeAndBookTypeBean {

	private String gradeName;
	private String gradeCode;
	private String bookTypeCode;
	private String bookTypeName;
	private String subjectCode;
	private String subjectName;
	private int durationId;

}
