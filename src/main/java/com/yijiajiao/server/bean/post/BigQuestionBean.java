package com.yijiajiao.server.bean.post;

import java.util.ArrayList;
import java.util.List;

public class BigQuestionBean {
	private String itemContent;
	private int sort;
	private List<SmallQuestionBean> smallQuestions= new ArrayList<SmallQuestionBean>();
	private String itemType;

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemContent() {
		return itemContent;
	}
	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public List<SmallQuestionBean> getSmallQuestions() {
		return smallQuestions;
	}
	public void setSmallQuestions(List<SmallQuestionBean> smallQuestions) {
		this.smallQuestions = smallQuestions;
	}
	
}
