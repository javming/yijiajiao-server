package com.yijiajiao.server.service;


import com.yijiajiao.server.bean.ResultBean;

public interface WaresService {
    /**
     * 根据日期获取某个教师的课程表
     */
    ResultBean timeTable(String startDate, String endDate, String openId);
    /**
     * 根据日期获取某天教师的课程信息列表
     */
    ResultBean getWaresByDate(String classDate, String waresType, String teacherId);

    /**
     * 课程列表
     */
    ResultBean wareslist(int pageNo, int pageSize, String subjectCode, String gradeCode, String bookTypeCode, Integer curriculumType, String order, String orderType, String stageCode, String reservePrice, String peakPrice, String smallCourseware, String teacherId, String status, String isYjj, String yjjCode);

    /**
     * 课程详情页
     */
    ResultBean waresInfoById(int id);

    /**
     * 教师获取个人视频列表
     */
    ResultBean getViedoById(String openId, String gradeCode, String bookTypeCode, int useType);

    /**
     * 教师按照审核状态获取个人视频列表
     */
    ResultBean getViedoByExamination(String openId, String gradeCode, String bookTypeCode, String examination, int pageNo, int pageSize);

    /**
     * 验证教师课程时间冲突
     */
    ResultBean checkTime(String openId, String startTime, String endTime);
}
