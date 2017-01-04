package com.yijiajiao.server.service;

import com.yijiajiao.server.bean.ResultBean;


public interface SolutionService {

    /**
     * 前端首页展示焦点图
     */
    ResultBean getAnswering(String openId, int pageNo, int pageSize, String subjectCode, String gradeCodes);

    /**
     * 获取单条待解答记录
     */
    ResultBean getAnsweringById(String openId, int id);

    /**
     * 学生端获取已解答答疑列表
     */
    ResultBean getAnswered(String openId, int pageNo, int pageSize);

    /**
     * 学生端获取自己已解答答疑详情
     */
    ResultBean getAnsweredById(String openId, int id);

    /**
     * 获取某教师的答疑介绍详情
     */
    ResultBean getDoubtDetail(String teachId);

    /**
     * 教师端抢单
     */
    ResultBean exitGrab(String openId, int solutionId);

    /**
     * 学生段取消答疑订单
     */
    ResultBean exitSolution(String openId, int solutionId);

    /**
     * web分页查询答疑教师列表
     */
    ResultBean getSolutionTeacherList(int pageNo, int pageSize, String subjectCode, String gradeCode, String bookTypeCode, String orderType, String stageCode, String name, String openId, String order);

    /**
     * 学生段提交答疑时长
     */
    ResultBean createSolutionCount(int id, double countTime, String communicateTime);

    /**
     * 答疑系数列表
     */
    ResultBean ratio();

    /**
     * 教师端接单历史列表
     */
    ResultBean solutionList(String openId, int pageNo, int pageSize);

    /**
     * 教师端接单历史列表
     */
    ResultBean solutionInfo(int id);

    /**
     *	分页获取教师答疑包
     */
    ResultBean getPackageReord(String teacherId, int pageNo, int pageSize, int sellStatus, String studentId);

    /**
     *	根据id获取答疑包信息
     */
    ResultBean getPackageById(String packageId);

    /**
     *	获取答疑时长基础数据
     */
    ResultBean getDuration(String gradeCode, String subjectCode);

    /**
     *	答疑包上下架
     */
    ResultBean packagePutawayOrDown(String packageId, int shelfStatus);

    /**
     *	分页获取购买的答疑老师信息
     */
    ResultBean getMyDoubtTeachers(String openId, int pageNo, int pageSize);

    /**
     *	分页查询答疑记录
     */
    ResultBean getSolutions(String teacherId, String studentId, int pageNo, int pageSize, String isAnswer, String subjectCode);

    /**
     *	答疑排行榜
     */
    ResultBean getRecDoubtTeachers(String openId);

    /**
     * 学生获取已购买的答疑包列表
     */
    ResultBean getMyPackages(String openId, int pageNo, int pageSize);

    /**
     *	获取答疑记录详情
     */
    ResultBean getDetail(int id);

    /**
     *	获取反馈答疑的理由
     */
    ResultBean getFeedBackReasons();

    /**
     *	根据教师openId及学年获取教师答疑包
     */
    ResultBean getPackageByGrade(String teacherId, String gradeCode);

    /**
     *	根据教师openId 学年分组获取教师答疑包
     */
    ResultBean getPackagesByOpenId(String openId);

    /**
     *	根据环信id获取答疑教师头像和答疑图片
     */
    ResultBean getEasemobId(String easemobId);

    /**
     *@	教师打电话时提交答疑id
     */
    ResultBean getSolId(String openId, int solId);

    /**
     *	获取答疑近似题
     */
    ResultBean getQuestionByRand(String gradeCode, String subjectCode, String knowledgeCode);
}
