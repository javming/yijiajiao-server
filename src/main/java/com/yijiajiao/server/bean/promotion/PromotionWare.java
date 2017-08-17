package com.yijiajiao.server.bean.promotion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author  zhaoming
 * @since  2016-12-22-20:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}
