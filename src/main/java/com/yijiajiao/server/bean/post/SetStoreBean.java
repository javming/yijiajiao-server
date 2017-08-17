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
public class SetStoreBean {

	private String storeName;
	private String introduction;
	private String brief;
	private String label;
	private String customLabel;
	private String storeBackPic;
	private String userOpenId;
	private String provinceCode;
	private String cityCode;
	private String areaCode;
	private String storePic;
	private String teachPic;
	private String videoDescription;//视频描述
	private String weibo;
	private String weChat;
	private String qq;
	private String famousTeacherDescription;	//名师风采描述
	private String famousTeacherPic;	//名师风采图片
	private String famousTeacherVideo;	//名师风采视频
	private List<TeachExperience> teachExperience;
	private List<TeachResult> teachResult;

}
