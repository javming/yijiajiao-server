package com.yijiajiao.server.bean.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplyPermissionBean {

	private String userOpenId;
	private int type;
	private String subjectCode;
	private String stageCode;
	private String stageName;

}
