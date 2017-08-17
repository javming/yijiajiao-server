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
public class WareOne2OneBean {

	private String teacherId;
	private String curriculumName;
	private int curriculumType;
	private String curriculumInfo;
	private String subjectCode;
	private String gradeCode;
	private String bookTypeCode;
	private String cover;
	private int teachMode;
	private double price121Online;
	private double price121Offline;
	private int isQuit;// 退班
	private int quitSupplement;
	private String location;// 地图坐标
	private double scope;// 上门服务范围
	private String homework;
	private String slaveTimeId;
	private List<SlaveOne2OneBean> slaves;

}
