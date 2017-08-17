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
public class UpdateAskBean {

	private String solSubject;
	private String solSubjectCode;
	private String solGrades;
	private String solGradesCode;
	private String solDescription;
	private String solPicture;
	private String openId;
	private String teaOpenId;
	private List<Picture> pictureList;
	private String bookTypeName;// 教材版本
	private String bookTypeCode;// 教材编码

}
