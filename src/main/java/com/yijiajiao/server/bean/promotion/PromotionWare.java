package com.yijiajiao.server.bean.promotion;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-12-22-20:22
 */
public class PromotionWare {
    private String id;
    private String teacherId;
    private String curriculumName;
    private String curriculumType;
    private String curriculumInfo;
    private String subjectCode;
    private String subjectName;
    private String gradeCode;
    private String gradeName;
    private String booktypeCode;
    private String booktypeName;
    private String price;
    private String isDiscount;
    private String priceDiscount;
    private String price121Online;
    private String price121Offline;
    private String cover;
    private String status;
    private String popularity;
    private String teachMode;
    private String createTime;
    private String updateTime;
    private String describeInfo;
    private String maxNumber;
    private String trialCrowd;
    private String sales;
    private String startTime;
    private String endTime;
    private String isYjj;
    private int activityStatus;
    private String activityStartTime="";
    private String activityEndTime="";
    private Double discount;

    public int getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(int activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(String activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public String getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(String activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getCurriculumName() {
        return curriculumName;
    }

    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }

    public String getCurriculumType() {
        return curriculumType;
    }

    public void setCurriculumType(String curriculumType) {
        this.curriculumType = curriculumType;
    }

    public String getCurriculumInfo() {
        return curriculumInfo;
    }

    public void setCurriculumInfo(String curriculumInfo) {
        this.curriculumInfo = curriculumInfo;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getBooktypeCode() {
        return booktypeCode;
    }

    public void setBooktypeCode(String booktypeCode) {
        this.booktypeCode = booktypeCode;
    }

    public String getBooktypeName() {
        return booktypeName;
    }

    public void setBooktypeName(String booktypeName) {
        this.booktypeName = booktypeName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(String isDiscount) {
        this.isDiscount = isDiscount;
    }

    public String getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(String priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public String getPrice121Online() {
        return price121Online;
    }

    public void setPrice121Online(String price121Online) {
        this.price121Online = price121Online;
    }

    public String getPrice121Offline() {
        return price121Offline;
    }

    public void setPrice121Offline(String price121Offline) {
        this.price121Offline = price121Offline;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getTeachMode() {
        return teachMode;
    }

    public void setTeachMode(String teachMode) {
        this.teachMode = teachMode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescribeInfo() {
        return describeInfo;
    }

    public void setDescribeInfo(String describeInfo) {
        this.describeInfo = describeInfo;
    }

    public String getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(String maxNumber) {
        this.maxNumber = maxNumber;
    }

    public String getTrialCrowd() {
        return trialCrowd;
    }

    public void setTrialCrowd(String trialCrowd) {
        this.trialCrowd = trialCrowd;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIsYjj() {
        return isYjj;
    }

    public void setIsYjj(String isYjj) {
        this.isYjj = isYjj;
    }
}
