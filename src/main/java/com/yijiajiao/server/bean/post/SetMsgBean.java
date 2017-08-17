package com.yijiajiao.server.bean.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SetMsgBean {

	private String openId;
	private int isTeacher;
	private int isSend;
	private int isSendSystem;
	private int isSendStation;
	private int isSendCourse;

}
