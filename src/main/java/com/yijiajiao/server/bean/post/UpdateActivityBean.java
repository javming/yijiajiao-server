package com.yijiajiao.server.bean.post;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-09-17:09
 */
public class UpdateActivityBean {
    private int id;// 活动id
    private String startDate; // 开始时间
    private String endDate;// 结束时间
    private String description;
    private String name; //活动名称
    private String value;// 折扣值
    private String courseIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(String courseIds) {
        this.courseIds = courseIds;
    }

    @Override
    public String toString() {
        return "UpdateActivityBean{" +
                "id=" + id +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", courseIds='" + courseIds + '\'' +
                '}';
    }
}
