package com.yijiajiao.server.bean.solution;

import java.util.Date;
import java.util.List;

/**
 * 答疑实体
 */
public class Solution {

    private String id;

    private String solveName;

    private String studentId;
    private String teacherId;

    private String subject;
    private String subjectCode;

    private String grade;
    private String gradeCode;

    private String solGrades;
    private String solGradesCode;


    private Date createTime;

    // 计进与实际等待时间，单位秒
    private Long planWaitTime;

    // 实际接单时间
    private Date realReceiveTime;
    /**
     * 订单状态=>
     * 1. 发起答疑；
     * 2. 老师已接单；
     * 3. 答案已回传；
     * 4. 学生已付款；
     * 5. 学生已评价；
     */
    private Integer solutionStatus ;
    /**
     * 答疑（完成）状态=>
     * 1. 正常结束；
     * 2. 回传超时但完成；
     * 3. 超时未接单；
     * 4. 回传超时学生主动取消；
     * 5. 回传超时系统自动取消；
     */
    private Integer questionStatus;

    /**
     * 教师抢单时间
     */
    private Long receiveTime;

    // 问题描述
    private String description;
    /**
     * 图片描述
     */
    private List<String> images;
    /**
     * 语音描述
     */
    private List<String> audios;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSolveName() {
        return solveName;
    }

    public void setSolveName(String solveName) {
        this.solveName = solveName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getSolGrades() {
        return solGrades;
    }

    public void setSolGrades(String solGrades) {
        this.solGrades = solGrades;
    }

    public String getSolGradesCode() {
        return solGradesCode;
    }

    public void setSolGradesCode(String solGradesCode) {
        this.solGradesCode = solGradesCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getPlanWaitTime() {
        return planWaitTime;
    }

    public void setPlanWaitTime(Long planWaitTime) {
        this.planWaitTime = planWaitTime;
    }

    public Date getRealReceiveTime() {
        return realReceiveTime;
    }

    public void setRealReceiveTime(Date realReceiveTime) {
        this.realReceiveTime = realReceiveTime;
    }

    public Integer getSolutionStatus() {
        return solutionStatus;
    }

    public void setSolutionStatus(Integer solutionStatus) {
        this.solutionStatus = solutionStatus;
    }

    public Integer getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(Integer questionStatus) {
        this.questionStatus = questionStatus;
    }

    public Long getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Long receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getAudios() {
        return audios;
    }

    public void setAudios(List<String> audios) {
        this.audios = audios;
    }
}




