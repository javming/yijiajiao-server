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
public class UploadVideoParamBean {

	private String author;
	private String vedioName;
	private String url;
	private String subjectCode;
	private String gradeCode;
	private String bookTypeCode;
	private String useType;
	private List<Knowledge> knowledges = new ArrayList<>();

}
