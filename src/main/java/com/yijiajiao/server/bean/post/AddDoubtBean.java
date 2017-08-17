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
public class AddDoubtBean {

	private List<GradeAndBookTypeBean> gradesAndSubject;
	private String id;// 答疑
	private String goodRange;// 擅长范围
	private String onlineTime;// 在线时间
	private String limitNumber;// 人数限制
	private String provinceCode;// 地区code
	private String provinceName;//地区名称
	private String openId;//　　教师openid

}
