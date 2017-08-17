package com.yijiajiao.server.bean.wares;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaresBean {

    private String id;
    private String teacherId;
    private String teacherName;
    private String curriculumName;
    private int curriculumType;
    private String curriculumInfo;
    private String subjectCode;
    private String gradeCode;
    private String bookTypeCode;
    private double price;
    private int isDiscount;
    private double priceDiscount;
    private String cover;
    private String upTime;
    private int popularity;
    private String downTime;
    private String status;
    private String sales;
    private String startTime;
    private String endTime;
    private String isYjj;
    private String curriculumInfoAudio;
    private String curriculumInfoVideo;
    private String curriculumInfoPic;
    private int activeStatus;

}
