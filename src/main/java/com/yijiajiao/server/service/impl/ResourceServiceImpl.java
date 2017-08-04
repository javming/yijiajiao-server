package com.yijiajiao.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.yijiajiao.server.bean.RedisParam;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.SystemStatus;
import com.yijiajiao.server.bean.post.*;
import com.yijiajiao.server.bean.user.EasyUserListBean;
import com.yijiajiao.server.bean.user.IdsBean;
import com.yijiajiao.server.bean.wares.WaresBean;
import com.yijiajiao.server.bean.wares.WaresListBean;
import com.yijiajiao.server.service.ResourceService;
import com.yijiajiao.server.service.UserService;
import com.yijiajiao.server.util.Config;
import com.yijiajiao.server.util.RedisUtil;
import com.yijiajiao.server.util.ServerUtil;
import com.yijiajiao.server.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.yijiajiao.server.util.ServerUtil.*;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-03-28-9:40
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService{

    private static final Logger log = LoggerFactory.getLogger(ResourceServiceImpl.class);
    
    @Autowired
    private UserService userService;

    @Override
    public ResultBean knowledges(String subjectCode, String gradeCode, String bookTypeCode) {

        String path = Config.getString("wares.knowledges") + "subjectCode=" + subjectCode + "&gradeCode=" + gradeCode
                + "&bookTypeCode=" + bookTypeCode;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean questionByKnowledge(String subject, String grade, String knowledge, int pageNo, int pageSize) {
        String path = Config.getString("wares.questionByKnowledge") + "subject=" + subject + "&grade=" + grade
                + "&knowledge=" + knowledge+ "&pageNo=" + pageNo+ "&pageSize=" + pageSize;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean examList(String openId,String gradeName,String subjectName,String bookTypeName,int examType,
                               int pageNo,int pageSize) {
        String path = Config.getString("ware.ExamList")+"teacherId="+openId+"&examType="+examType+"&pageNo="+pageNo
                +"&pageSize="+pageSize+(StringUtil.isEmpty(gradeName)?"":("&gradeName="+gradeName))
                +(StringUtil.isEmpty(subjectName)?"":("&subjectName="+subjectName))
                +(StringUtil.isEmpty(bookTypeName)?"":("&bookTypeName="+bookTypeName));
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean searchTeacher(int pageNo, int pageSize, String order, String orderType, String keyword) {
        String path = Config.getString("user.searchteacherbyname")+"pageNo="+pageNo+"&pageSize="+pageSize
                + "&orderType="+ (StringUtil.isEmpty(order)?"popularity":order)
                +(StringUtil.isEmpty(keyword)?"":("&name=" +keyword))
                +(StringUtil.isEmpty(orderType)?"":("&orders="+orderType));
        String response = ServerUtil.httpRest(USER_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean searchWares(int pageNo, int pageSize, String subjectCode, String gradeCode, String bookTypeCode,
                                  Integer curriculumType, String keyword, String order, String orderType, String reservePrice,
                                  String peakPrice) {
        ResultBean result = new ResultBean();
        String path = Config.getString("wares.wareslist") + "pageNo=" + pageNo + "&pageSize=" + pageSize
                + (curriculumType==null?"":("&curriculumType="+curriculumType) )
                + (StringUtil.isEmpty(subjectCode)?"":("&subjectCode=" + subjectCode))
                + (StringUtil.isEmpty(gradeCode)?"":("&gradeCode=" + gradeCode))
                + (StringUtil.isEmpty(bookTypeCode)?"":("&bookTypeCode=" + bookTypeCode))
                + (StringUtil.isEmpty(keyword)?"":("&curriculumName="+keyword))
                + (StringUtil.isEmpty(order)?"":("&order=" + order))
                + (StringUtil.isEmpty(orderType)?"":("&orderType=" + orderType))
                + (StringUtil.isEmpty(reservePrice)?"":("&reservePrice="+reservePrice))
                + (StringUtil.isEmpty(peakPrice)?"":("&peakPrice="+peakPrice));
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        ResultBean resultBean = JSON.parseObject(response, ResultBean.class);
        if (resultBean.getCode() == 200 ) {
            String waresInfo = JSON.toJSONString(resultBean.getResult());
            log.info("正确信息： " + waresInfo);
            WaresListBean waresListBean = JSON.parseObject(waresInfo, WaresListBean.class);
            if (waresListBean.getList().size() > 0) {
                StringBuffer stb = new StringBuffer("");
                for (WaresBean waresBean : waresListBean.getList()) {
                    stb.append(waresBean.getTeacherId() + ",");
                }
                IdsBean ids = new IdsBean(stb.toString().substring(0, stb.toString().length()-1));

                System.out.println(ids);
                System.out.println(userService);
                System.out.println("hashCode: "+hashCode());
                System.out.println("userService.hashCode() : "+userService.hashCode());


                // 查询到教师的名称
//                UserService userService = new UserServiceImpl();
                ResultBean ur = userService.finduserinfobyid(ids);
                EasyUserListBean list_user = (EasyUserListBean) ur.getResult();
                for (int i = 0; i < waresListBean.getList().size(); i++) {
                    waresListBean.getList().get(i).setTeacherName(list_user.getList().get(i).getName());
                }
                result.setSucResult(waresListBean);
            }else{
                result.setSucResult(resultBean.getResult());
            }

        } else {
            log.info("错误信息： " + resultBean.getMessage());
            result.setFailMsg(resultBean.getCode(), resultBean.getMessage());
        }
        return result;
    }

    @Override
    public ResultBean nowledgeByGradeSubjectBooktype(String gradeCode, String subjectCode, String bookTypeCode) {
        String path = Config.getString("wares.nowledgeTree") + "gradeCode="+(gradeCode==null?"":gradeCode)
                +(StringUtil.isEmpty(subjectCode)?"":("&subjectCode="+subjectCode))
                +(StringUtil.isEmpty(bookTypeCode)?"":("&bookTypeCode="+bookTypeCode));
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getSmallList(String gradeCode, String subjectCode, String bookType, String categoriesCode) {
        String path = Config.getString("wares.smallList")+"gradeCode="+(StringUtil.isEmpty(gradeCode)?"":gradeCode)
                +(StringUtil.isEmpty(subjectCode)?"":("&subjectCode="+subjectCode))
                +(StringUtil.isEmpty(bookType)?"":("&bookType="+bookType))
                +(StringUtil.isEmpty(categoriesCode)?"":("&categoriesCode="+categoriesCode));
        String response = ServerUtil.httpRest(WARES_SERVER,path,null,null,"GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean moduleListBySmall(String small) {
        String path = Config.getString("wares.moduleListBySmall")+small;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean moduleInfoByCode(String code) {
        String path = Config.getString("wares.moduleInfoByCode")+code;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean baseCourseAndStage() {
        String path = Config.getString("wares.baseCourseAndStage");
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean questionTypeBySubject(String subjectCode) {
        String path = Config.getString("wares.questionTypeBySubject")+subjectCode;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean questions(String gradeCode,String subjectCode,String knowledgeCode,String type,int pageNo,int pageSize) {
        String path = Config.getString("wares.questions") +"pageNo="+pageNo+"&pageSize="+pageSize
                +(StringUtil.isEmpty(gradeCode)?"":("&gradeCode="+gradeCode)) +(StringUtil.isEmpty(type)?"":("&type="+type))
                +(StringUtil.isEmpty(subjectCode)?"":("&subjectCode="+subjectCode))
                +(StringUtil.isEmpty(knowledgeCode)?"":("&knowledgeCode="+knowledgeCode));
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log, response);
    }
    @Override
    public ResultBean knowledgesNew(String gradeCode, String subjectCode, String bookTypeCode) {
        ResultBean resultBean = new ResultBean();
        String path = Config.getString("wares.knowledgesNew")+"gradeCode="+(StringUtil.isEmpty(gradeCode)?"":gradeCode)
                +(StringUtil.isEmpty(subjectCode)?"":("&subjectCode="+subjectCode))
                +(StringUtil.isEmpty(bookTypeCode)?"":("&bookTypeCode="+bookTypeCode));
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean questionsinfo(String ids, String subjectCode) {
        String path = Config.getString("wares.questionsinfo")+"ids="+ids+"&subjectCode="+subjectCode;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getMyFameCourseAndSolutionCount(String openId) {
        ResultBean resultBean = new ResultBean();
        String path= Config.getString("solution.getPackageCount")+"openId="+openId;
        Map<String,Object> counts = new HashMap<>();
        String response = ServerUtil.httpRest(SOLUTION_SERVER, path, null, null, "GET");
        ResultBean r = JSON.parseObject(response, ResultBean.class);
        if (r.getCode() == 200) {
            log.info("请求答疑包数量正确信息： " + r.getResult());
            counts.put("packageCount", r.getResult());
        } else {
            log.info("请求答疑包数量错误信息： " + r.getMessage());
            counts.put("packageCount", 0);
        }
        resultBean.setSucResult(counts);
        return resultBean;
    }

    @Override
    public ResultBean papersOnYjj(String paperId, String moduleId,String slaveId,String type) {
        String path = Config.getString("wares.papersOnYjj")+"moduleId="+moduleId
                +(StringUtil.isEmpty(paperId)?"":("&paperId="+paperId))
                +(StringUtil.isEmpty(slaveId)?"":("&slaveId="+slaveId))
                +(StringUtil.isEmpty(type)?"":("&type="+type));
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean gradeBySubject(String subjectCode) {
        String path = Config.getString("wares.gradeBySubject")+subjectCode;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean createExamHead(String tag, CreateExamBean createExamBean) {
        String path = Config.getString("wares.createExam");
        String response = ServerUtil.httpRest(WARES_SERVER,path,null,createExamBean,"POST");
        return dealResult(log,response);
    }

    @Override
    public ResultBean createExamDetail(String tag, CreateExamDetailBean createExamDetailBean) {
        String path = Config.getString("wares.CreateExamDetail");
        String response = ServerUtil.httpRest(WARES_SERVER,path,null,createExamDetailBean,"POST");
        return dealResult(log,response);
    }

    @Override
    public ResultBean smartCreateExam(String tag, SmartCreateExamBean smartCreateExamBean) {
        String path = Config.getString("wares.SmartCreateExam");
        String response = ServerUtil.httpRest(WARES_SERVER,path,null,smartCreateExamBean,"POST");
        return dealResult(log,response);
    }

    @Override
    public ResultBean addQuestions(String tag, AddQuestionsBean addQuestionsBean) {
        String path = Config.getString("wares.AddQuestions");
        String response = ServerUtil.httpRest(WARES_SERVER,path,null,addQuestionsBean,"POST");
        return dealResult(log,response);
    }

    @Override
    public ResultBean markingPaper(String tag, DiagnoseAnswerSubmitBean diagnoseAnswerSubmitBean) {
        String markingPaper = Config.getString("wares.markingPaper");
        String res = ServerUtil.httpRest(WARES_SERVER, markingPaper, null, diagnoseAnswerSubmitBean, "POST");
        log.info("markingPaper  return is " + res);
        ResultBean result = JSON.parseObject(res, ResultBean.class);
        if(result.getCode()!=200){
            return result;
        }
        if (diagnoseAnswerSubmitBean.getSubmitType()==null){
            System.out.println("修改ishomework为 2");
            String updateIsHomework=Config.getString("sale.updateIsHomework")+"openId="+diagnoseAnswerSubmitBean.getOpenId()+
                    "&commodityId="+diagnoseAnswerSubmitBean.getWaresId()+"&slaveId="+
                    (StringUtil.isEmpty(diagnoseAnswerSubmitBean.getWaresSlaveId())?-1:diagnoseAnswerSubmitBean.getWaresSlaveId());
            ServerUtil.httpRest(SALE_SERVER,updateIsHomework,null,null,"PUT");
        }

        return result;
    }

    @Override
    public ResultBean getFromRedis(RedisParam redisParam) {
        ResultBean result = new ResultBean();
        log.info("key =="+redisParam.getKey()+", dbNum=="+redisParam.getDbNum());
        try {
            String value = RedisUtil.getValue(redisParam.getKey(), redisParam.getDbNum());
            log.info("the retuning of redis is :"+value);
            //JSONObject jsonObject = JSONObject.fromObject(value);
            Object jsonObject = JSON.parse(value);
            result.setSucResult(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            result.setFailMsg(SystemStatus.SERVER_ERROR);
        }
        return result;
    }

    @Override
    public ResultBean getToken(String key, int dbNum) {
        String value = RedisUtil.getValue(key, dbNum);
        long ttl = RedisUtil.ttl(key);
        log.info("{token="+value+",ttl="+ttl+"}");
        Map<String,Object> result = new HashMap<>();
        result.put("token",value);
        result.put("ttl",ttl);
        return ResultBean.getSucResult(result);
    }

}
