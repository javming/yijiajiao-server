package com.yijiajiao.server.bean.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeachExperience {
	
	private String openId;
	private String experienceStartTime;
	private String experienceEndTime;
	private String experienceDescription;

}
