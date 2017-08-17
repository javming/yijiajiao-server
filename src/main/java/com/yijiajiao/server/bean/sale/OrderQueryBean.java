package com.yijiajiao.server.bean.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderQueryBean {
	
	private int pageNo;
	private int pageSize;
	private String status;
	private String openId;
	private int curriculumType;

}
