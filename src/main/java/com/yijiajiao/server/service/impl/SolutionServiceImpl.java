package com.yijiajiao.server.service.impl;

import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.solution.CountTimeBean;
import com.yijiajiao.server.bean.solution.ExitGrabBean;
import com.yijiajiao.server.bean.solution.ExitSolutionBean;
import com.yijiajiao.server.service.BaseService;
import com.yijiajiao.server.service.SolutionService;
import com.yijiajiao.server.util.Config;
import com.yijiajiao.server.util.ServerUtil;
import com.yijiajiao.server.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-04-15:32
 */

@Service("solutionService")
public class SolutionServiceImpl extends BaseService implements SolutionService{

    private static final Logger log = LoggerFactory.getLogger(SolutionServiceImpl.class);

    @Override
    public ResultBean getAnswering(String openId, int pageNo, int pageSize, String subjectCode, String gradeCodes) {
        String  path = Config.getString("solution.getAnswering")+"pageNo="+pageNo+"&pageSize="+pageSize
                +(StringUtil.isEmpty(subjectCode)?"":("&solSubjectCode="+subjectCode))
                +(StringUtil.isEmpty(gradeCodes)?"":("&solGradesCode="+gradeCodes));
        String response = ServerUtil.httpRest(SOLUTION_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getAnsweringById(String openId, int id) {
        String  path = Config.getString("solution.getAnsweringById")+id;
        String response = ServerUtil.httpRest(SOLUTION_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getAnswered(String openId, int pageNo, int pageSize) {
        String  path = Config.getString("solution.getAnswered")+"pageNo="+pageNo+"&pageSize="+pageSize+"&openId="+openId+"&isAnswer=0";
        String response = ServerUtil.httpRest(SOLUTION_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getAnsweredById(String openId, int id) {
        String  path = Config.getString("solution.getAnsweredById")+id;
        String response = ServerUtil.httpRest(SOLUTION_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getDoubtDetail(String teachId) {
        String  path = Config.getString("solution.getDoubtDetail")+teachId;
        String response = ServerUtil.httpRest(SOLUTION_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean exitGrab(String openId, int solutionId) {
        String  path = Config.getString("solution.exitGrab");
        ExitGrabBean exitGrabBean  = new ExitGrabBean(openId,solutionId);
        String response = ServerUtil.httpRest(SOLUTION_SERVER, path, null, exitGrabBean, "POST");
        return dealResult(log,response);
    }

    @Override
    public ResultBean exitSolution(String openId, int solutionId) {
        String  path = Config.getString("solution.exitSolution");
        ExitSolutionBean exitSolutionBean  = new ExitSolutionBean(solutionId,3);
        String response = ServerUtil.httpRest(SOLUTION_SERVER, path, null, exitSolutionBean, "POST");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getSolutionTeacherList(int pageNo, int pageSize, String subjectCode, String gradeCode, String bookTypeCode,
                                             String orderType, String stageCode, String name, String openId, String order) {
        String  path = Config.getString("solution.getsolutionteacherlist")+"pageNo="+pageNo+"&pageSize="+pageSize
                +(StringUtil.isEmpty(subjectCode)?"":("&subjectCode="+subjectCode))+(StringUtil.isEmpty(name)?"":("&name="+name))
                +(StringUtil.isEmpty(gradeCode)?"":("&gradeCode="+gradeCode))+(StringUtil.isEmpty(openId)?"":("&openId="+openId))
                +(StringUtil.isEmpty(orderType)?"":("&orderType="+orderType))+(StringUtil.isEmpty(stageCode)?"":("&stageCode="+stageCode))
                +(StringUtil.isEmpty(bookTypeCode)?"":("&bookTypeCode="+bookTypeCode))+(StringUtil.isEmpty(order)?"":("&order="+order));

        String response = ServerUtil.httpRest(SOLUTION_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean createSolutionCount(int id, double countTime, String communicateTime) {
        String  path = Config.getString("solution.exitSolution");
        CountTimeBean countTimeBean  = new CountTimeBean(id,countTime,communicateTime);
        String response = ServerUtil.httpRest(SOLUTION_SERVER, path, null, countTimeBean, "POST");
        return dealResult(log,response);
    }

    @Override
    public ResultBean ratio() {
        String  path = Config.getString("solution.ratio");
        String response = ServerUtil.httpRest(SOLUTION_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean solutionList(String openId, int pageNo, int pageSize) {
        String  path = Config.getString("solution.solutionList")+"pageNo="+pageNo+"&pageSize="+pageSize+"&openId="+openId;
        String response = ServerUtil.httpRest(SOLUTION_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean solutionInfo(int id) {
        String  path = Config.getString("solution.solutionInfo")+id;
        String response = ServerUtil.httpRest(SOLUTION_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getPackageReord(String teacherId, int pageNo, int pageSize, int sellStatus, String studentId) {
        String path = Config.getString("solution.getPackageReord")+"pageNo="+pageNo+"&pageSize="+pageSize
                +"&sellStatus="+sellStatus+(StringUtil.isEmpty(studentId)?"":("&stuOpenId="+studentId))
                +(StringUtil.isEmpty(teacherId)?"":("&openId="+teacherId));
        String response = ServerUtil.httpRest(SOLUTION_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getPackageById(String packageId) {
        String path = Config.getString("solution.getPackageById")+packageId;
        String response = ServerUtil.httpRest(SOLUTION_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getDuration(String gradeCode, String subjectCode) {
        String path = Config.getString("solution.getDuration")+"gradeCode="+gradeCode+"&subjectCode="+subjectCode;
        String response = ServerUtil.httpRest(SOLUTION_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean packagePutawayOrDown(String packageId, int shelfStatus) {
        return null;
    }

    @Override
    public ResultBean getMyDoubtTeachers(String openId, int pageNo, int pageSize) {
        return null;
    }

    @Override
    public ResultBean getSolutions(String teacherId, String studentId, int pageNo, int pageSize, String isAnswer, String subjectCode) {
        return null;
    }

    @Override
    public ResultBean getRecDoubtTeachers(String openId) {
        return null;
    }

    @Override
    public ResultBean getMyPackages(String openId, int pageNo, int pageSize) {
        return null;
    }

    @Override
    public ResultBean getDetail(int id) {
        return null;
    }

    @Override
    public ResultBean getFeedBackReasons() {
        return null;
    }

    @Override
    public ResultBean getPackageByGrade(String teacherId, String gradeCode) {
        return null;
    }

    @Override
    public ResultBean getPackagesByOpenId(String openId) {
        return null;
    }

    @Override
    public ResultBean getEasemobId(String easemobId) {
        return null;
    }

    @Override
    public ResultBean getSolId(String openId, int solId) {
        return null;
    }

    @Override
    public ResultBean getQuestionByRand(String gradeCode, String subjectCode, String knowledgeCode) {
        return null;
    }
}
