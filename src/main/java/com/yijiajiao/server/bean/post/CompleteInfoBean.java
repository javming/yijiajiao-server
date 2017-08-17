package com.yijiajiao.server.bean.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompleteInfoBean {

	private String userOpenId;
	private String name;
	private String sex;
	private String birthday;
	private String school;
	private String mail;
	private String parentName;
	private String parentPhone;
	private String provinceCode;
	private String cityCode;
	private String areaCode;
	private String address;
	private String pic;
	private String subjectCode;
	private String subjectName;
	private String stageCode;
	private String stageName;
	private int teachAge;
	private String teacher_grade;

}
