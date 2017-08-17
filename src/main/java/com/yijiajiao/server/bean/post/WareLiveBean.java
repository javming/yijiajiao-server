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
public class WareLiveBean {

	private Integer id;
	private String teacherId;
	private String curriculumName;// 课程名称
	private int curriculumType;
	private String curriculumInfo;// 课程详情
	private String subjectCode;
	private String gradeCode;
	private String bookTypeCode;
	private String cover;
	private String goal;
	private double price;
	private String trialCrowd;
	private String describeInfo;
	private String homework;
	private String smallCourseware;
	private String isYjj;
	private String moduleId;
	private String yjjCode;
	private String yjjName;
	private String curriculumInfoAudio;
	private String curriculumInfoVideo;
	private String curriculumInfoPic;
	private Integer isQuit;
	private Integer quitSupplement;
	private Integer isInsert;
	private Integer insertSupplement;
	private Double insertPrice;
	private List<SlaveBean> slaves;
	private Integer maxNumber;
	private String isHomework;

}
