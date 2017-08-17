package com.yijiajiao.server.bean.wares;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollectQueryBean {

	private String userOpenId;
	private String courseId;
	private int type;

}
