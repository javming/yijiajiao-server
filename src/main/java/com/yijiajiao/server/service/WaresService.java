package com.yijiajiao.server.service;


import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.wares.BackUrlInfoParamBean;
import com.yijiajiao.server.bean.wares.BackUrlListParamBean;
import com.yijiajiao.server.bean.wares.M3JoinMtgParam;

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

    /**
     * 获取教师资格认证试题
     */
    ResultBean teacherExamTest(String openId, String code);

    /**
     * 教师课程数量统计
     */
    ResultBean countWaresByteacherId(String teacherId);

    ResultBean waresListByType(String teacherId, int curriculumType, Integer status, int pageNo, int pageSize, String isYjj);

    ResultBean getStage();

    ResultBean getGradeByStage(String stageCode);

    ResultBean getSubjectByGrade(String gradeCode);

    ResultBean mobilWaresListByTeacherIdAndTime(String openId, int pageNo, int pageSize, String cover, String sales, String maxNumber);

    ResultBean mobilWaresListByTeacherId(String openId, int pageNo, int pageSize, int curriculumType);

    ResultBean mobilWaresInfoByWaresId(String openId, int waresId, String subjectCode, String gradeCode, String bookTypeCode);

    ResultBean releaseStatus(String openId, int waresId, int status);

    ResultBean delVideo(int videoId);

    ResultBean videoCountByStatus(String openId, int status);

    ResultBean examList(String subjectCode, String gradeCode, String bookTypeCode);

    ResultBean answerInfoById(int id);

    ResultBean examStatistic(int id);

    ResultBean popularity(int waresId, String popType);

    ResultBean liveVideoPlayback(int waresId, int slaveId);

    ResultBean getMyPapers(String openId, int pageNo, int pageSize, Integer useType, String gradeCode, String subjectCode);

    ResultBean getPaperInfo(String paperId);

    ResultBean todayCourse();

    ResultBean paperCommitInfoByStudent(String openId, Integer waresId, Integer waresSlaveId, String paperId);

    ResultBean paperCommitInfoByTeacher(String waresId, String waresSlaveId, String paperId);

    ResultBean checkPaperCommitByStudent(String waresId, String waresSlaveId, String paperId, String openId);

    ResultBean deleteWaresById(int waresId);

    ResultBean liveWebUrl(M3JoinMtgParam m3JoinMtgParam);

    ResultBean backUrlList(BackUrlListParamBean backUrlListParamBean);

    ResultBean backUrlInfo(BackUrlInfoParamBean backUrlInfoParamBean);

    ResultBean appMtgInfo(M3JoinMtgParam m3JoinMtgParam);

    ResultBean homework(int commodityId, String type, String homeWorkCode);
}
