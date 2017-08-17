package com.yijiajiao.server.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaofenUserBean {

	private int id;
	private String name;
	private String area;
	private int age;
	private String contactWay;

}
