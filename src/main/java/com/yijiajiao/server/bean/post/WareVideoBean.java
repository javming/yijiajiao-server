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
public class WareVideoBean {

	private String teacherId;
	private String curriculumName;
	private int curriculumType;// 商品类型，1.直播课，2.视频课，3.一对一课
	private String curriculumInfo;
	private String subjectCode;
	private String gradeCode;
	private String bookTypeCode;
	private double price;
	private String cover;
	private int lookedTime;
	private int trialLookTime;
	private String homework;
	private int tryVideoId;
	private List<VideoSalveBean> slaves;

}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class VideoSalveBean {
	private long vedioId;
	private String homework;
}