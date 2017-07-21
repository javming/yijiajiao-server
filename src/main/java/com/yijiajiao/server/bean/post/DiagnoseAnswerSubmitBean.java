package com.yijiajiao.server.bean.post;

import java.util.ArrayList;
import java.util.List;

public class DiagnoseAnswerSubmitBean {
	private String openId;
	private String paperId;
	private String waresId;
	private String waresSlaveId;
	private String subjectCode;
	private Integer submitType;//1 课中练习 ，2 课后作业
	private Integer groupCode;//试卷分类 (0 未分类 1自由学类)  该项不传默认设置为0

	public Integer getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(Integer groupCode) {
		this.groupCode = groupCode;
	}

	public Integer getSubmitType() {
		return submitType;
	}

	public void setSubmitType(Integer submitType) {
		this.submitType = submitType;
	}

	private List<DiagnoseAnswerBean> list= new ArrayList<DiagnoseAnswerBean>();

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getWaresId() {
		return waresId;
	}

	public void setWaresId(String waresId) {
		this.waresId = waresId;
	}

	public String getWaresSlaveId() {
		return waresSlaveId;
	}
	public void setWaresSlaveId(String waresSlaveId) {
		this.waresSlaveId = waresSlaveId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getPaperId() {
		return paperId;
	}
	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}
	public List<DiagnoseAnswerBean> getList() {
		return list;
	}
	public void setList(List<DiagnoseAnswerBean> list) {
		this.list = list;
	}
	
	
}
