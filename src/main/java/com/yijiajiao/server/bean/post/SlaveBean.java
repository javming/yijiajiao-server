package com.yijiajiao.server.bean.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlaveBean {

	private Integer id;
	private Integer seq;
	private String startTime;
	private String endTime;
	private String content;
	private String homework;
	private String yjjCode;
	private String slaveId;

}
