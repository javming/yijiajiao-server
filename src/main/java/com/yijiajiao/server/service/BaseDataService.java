package com.yijiajiao.server.service;


import com.yijiajiao.server.bean.RedisParam;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.post.*;

public interface BaseDataService {

    /**
     * 按照学科，学年，教材版本获取知识树
     */
    ResultBean knowledges(String subjectCode, String gradeCode, String bookTypeCode);

    /**
     *  根据知识点获取试题
     */
    ResultBean questionByKnowledge(String subject, String grade, String knowledge, int pageNo, int pageSize);

    /**
     * 查询试卷列表
     */
    ResultBean examList(String openId, String gradeName, String subjectName, String bookTypeName, int examType, int pageNo, int pageSize);

    /**
     * 搜索教师
     */
    ResultBean searchTeacher(int pageNo, int pageSize, String order, String orderType, String keyword);

    /**
     * 搜索课程
     */
    ResultBean searchWares(int pageNo, int pageSize, String subjectCode, String gradeCode, String bookTypeCode, Integer curriculumType, String keyword, String order, String orderType, String reservePrice, String peakPrice);

    ResultBean nowledgeByGradeSubjectBooktype(String gradeCode, String subjectCode, String bookTypeCode);


    ResultBean createExamHead(CreateExamBean createExamBean);

    ResultBean createExamDetail(CreateExamDetailBean createExamDetailBean);

    ResultBean smartCreateExam(SmartCreateExamBean createExamDetailBean);

    ResultBean addQuestions(AddQuestionsBean createExamDetailBean);

    ResultBean markingPaper(DiagnoseAnswerSubmitBean diagnoseAnswerSubmitBean);

    ResultBean getFromRedis(RedisParam redisParam);

    ResultBean getToken(String key, int dbNum);

    ResultBean getSmallList(String gradeCode, String subjectCode, String bookType, String categoriesCode);

    ResultBean moduleListBySmall(String small);

    ResultBean moduleInfoByCode(String code);

    ResultBean baseCourseAndStage();

    ResultBean questionTypeBySubject(String subjectCode);

    ResultBean questions(String gradeCode, String subjectCode, String knowledgeCode, String type, int pageNo, int pageSize);

    ResultBean knowledgesNew(String gradeCode, String subjectCode, String bookTypeCode);

    ResultBean questionsinfo(String ids, String subjectCode);

    ResultBean getMyFameCourseAndSolutionCount(String teacherId);

    ResultBean papersOnYjj(String paperId, String moduleId, String slaveId, String type);

    ResultBean gradeBySubject(String subjectCode);
}
