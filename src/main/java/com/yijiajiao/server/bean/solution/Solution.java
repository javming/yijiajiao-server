package com.yijiajiao.server.bean.solution;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 答疑实体
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}




