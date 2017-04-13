package com.yijiajiao.server.bean.promotion;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-04-13-11:00
 */
public class CommodityActivityBean {
    private Integer activityId;
    private String courseId;
    private String startDate ="";
    private String endDate="";
    private double value;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
