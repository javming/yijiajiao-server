package com.yijiajiao.server.controller;

import com.yijiajiao.server.bean.RedisParam;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.post.*;
import com.yijiajiao.server.service.OSSService;
import com.yijiajiao.server.service.ResourceService;
import com.yijiajiao.server.service.impl.ResourceServiceImpl;
import com.yijiajiao.server.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.URLEncoder;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-05-10:01
 */

@Path("/resource")
@Component
public class ResourceController {

    private static final Logger log = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private OSSService ossService;

    /**
     * 按照学科，学年，教材版本获取知识树
     */
    @GET
    @Path("/knowledges")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean knowledges(@QueryParam("subjectCode") String subjectCode, @QueryParam("gradeCode") String gradeCode,
                                 @QueryParam("bookTypeCode") String bookTypeCode) {

        return resourceService.knowledges(subjectCode, gradeCode, bookTypeCode);

    }

    /**
     *  根据知识点获取试题
     */
    @GET
    @Path("/questionByKnowledge")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean knowledges(@QueryParam("subject") String subject, @QueryParam("grade") String grade,
                                 @QueryParam("knowledge") String knowledge, @QueryParam("pageNo") int pageNo,
                                 @QueryParam("pageSize") int pageSize) {

        return resourceService.questionByKnowledge(subject, grade, knowledge,pageNo,pageSize);

    }
    /**
     * 查询试卷列表
     */
    @GET
    @Path("/examListLogin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean examList(@HeaderParam("openId") String openId, @QueryParam("gradeCode") String gradeName,
                               @QueryParam("subjectCode") String subjectName, @QueryParam("bookTypeCode ") String bookTypeName,
                               @QueryParam("pageNo") int pageNo, @QueryParam("pageSize") int pageSize ){

        return resourceService.examList(openId,gradeName,subjectName,bookTypeName,11,pageNo,pageSize);

    }

    /**
     *	搜索教师或者课程
     */
    @GET
    @Path("/tosearch")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean toSearchForTeacherOrWares(@QueryParam("pageNo") int pageNo, @QueryParam("pageSize") int pageSize,
                                                @QueryParam("subjectCode") String subjectCode, @QueryParam("gradeCode") String gradeCode,
                                                @QueryParam("bookTypeCode") String bookTypeCode,@QueryParam("curriculumType") Integer curriculumType,
                                                @QueryParam("searchType") int searchType,@QueryParam("keyword") String keyword,@QueryParam("order") String order,
                                                @QueryParam("orderType") String orderType,@QueryParam("reservePrice")String reservePrice,@QueryParam("peakPrice") String peakPrice){

        if(StringUtil.isNotEmpty(keyword)){
            keyword = URLEncoder.encode(keyword);
            try {
                ossService.hotSearch(keyword,searchType);
            } catch (Exception e) {
                log.error("**[添加热搜词时出错:<"+e.getMessage()+">]**");
            }
        }

        if(searchType==1){
            return resourceService.searchTeacher(pageNo,pageSize,order,orderType,keyword);
        }else{
            return resourceService.searchWares(pageNo,pageSize,subjectCode,gradeCode,bookTypeCode,curriculumType,keyword,
                    order,orderType,reservePrice,peakPrice);
        }

    }


    @GET
    @Path("/nowledgeTree")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean nowledgeByGradeSubjectBooktype(@QueryParam("gradeCode") String gradeCode,@QueryParam("subjectCode")
                                                String subjectCode,@QueryParam("bookTypeCode")String bookTypeCode){

        return resourceService.nowledgeByGradeSubjectBooktype(gradeCode,subjectCode,bookTypeCode);

    }

    /**
     *@description		小课列表
     *@date 2016-8-9
     */
    @GET
    @Path("/smallList")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean smallList(@QueryParam("gradeCode") String gradeCode,@QueryParam("subjectCode") String subjectCode,
                                @QueryParam("bookType") String bookType,@QueryParam("categoriesCode") String categoriesCode){

        return resourceService.getSmallList(gradeCode,subjectCode,bookType,categoriesCode);

    }

    /**
     *@description		模块列表
     *@date 2016-8-9
     */
    @GET
    @Path("/moduleListBySmall")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean moduleListBySmall(@QueryParam("small") String small){

        return resourceService.moduleListBySmall(small);

    }

    /**
     *@description		模块详情
     *@date 2016-8-9
     */
    @GET
    @Path("/moduleInfoByCode")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean moduleInfoByCode(@QueryParam("code") String code){

        return resourceService.moduleInfoByCode(code);

    }

    @GET
    @Path("/baseCourseAndStage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean baseCourseAndStage(){

        return resourceService.baseCourseAndStage();

    }

    /**
     *	通过学科获取试题类型
     */
    @GET
    @Path("/questionTypeBySubject")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean questionTypeBySubject(@QueryParam("subjectCode") String subjectCode){

        return resourceService.questionTypeBySubject(subjectCode);

    }

    /**
     *	获取试题
     */
    @GET
    @Path("/questions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean questions(@QueryParam("gradeCode") String gradeCode,@QueryParam("subjectCode") String subjectCode,
                                @QueryParam("knowledgeCode") String knowledgeCode,@QueryParam("type") String type,@QueryParam("pageNo") int pageNo,
                                @QueryParam("pageSize") int pageSize){

        return resourceService.questions(gradeCode,subjectCode,knowledgeCode,type,pageNo,pageSize);

    }
    /**
     * 获取知识点
     */
    @GET
    @Path("/knowledgesNew")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean knowledgesNew(@QueryParam("gradeCode") String gradeCode,@QueryParam("subjectCode")String subjectCode,
                                    @QueryParam("bookTypeCode") String bookTypeCode){

        return resourceService.knowledgesNew(gradeCode,subjectCode,bookTypeCode);

    }

    /**
     * 批量获取试题详情
     */
    @GET
    @Path("/questionsinfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean questionsinfo(@QueryParam("ids") String ids,@QueryParam("subjectCode") String subjectCode){

        return resourceService.questionsinfo(ids,subjectCode);

    }

    /**
     *	获取教师名家开奖课数量和答疑包数量
     */
    @GET
    @Path("/getMyFameCourseAndSolutionCount")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getMyFameCourseAndSolutionCount(@QueryParam("teacherId") String teacherId){

        return resourceService.getMyFameCourseAndSolutionCount(teacherId);

    }
    /**
     *教师工作室获取易教体系试卷列表
     */
    @GET
    @Path("/papersOnYjj")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean papersOnYjj(@QueryParam("paperId")String paperId,@QueryParam("moduleId")String moduleId,@QueryParam("slaveId") String slaveId,
                                  @QueryParam("type")String type){

        return resourceService.papersOnYjj(paperId,moduleId,slaveId,type);

    }

    /**
     * 根据学科获取学年
     */
    @GET
    @Path("/gradeBySubject")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean gradeBySubject(@QueryParam("subjectCode")String subjectCode){

        return resourceService.gradeBySubject(subjectCode);

    }


    /**
     * 手动组卷添加卷头
     */
    @POST
    @Path("/createExam")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean createExamHead(@HeaderParam("tag")String tag, CreateExamBean createExamBean){
        return resourceService.createExamHead(tag,createExamBean);
    }

    /**
     * 手动组卷详情
     */
    @POST
    @Path("/createExamDetail")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean createExamDetail(@HeaderParam("tag")String tag, CreateExamDetailBean createExamDetailBean){
        return resourceService.createExamDetail(tag,createExamDetailBean);
    }

    /**
     * 智能组卷
     */
    @POST
    @Path("/smartCreateExam")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean smartCreateExam(@HeaderParam("tag")String tag, SmartCreateExamBean smartCreateExamBean){
        return resourceService.smartCreateExam(tag,smartCreateExamBean);
    }

    /**
     * 手动组卷添加试题
     */
    @POST
    @Path("/addQuestions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean addQuestions(@HeaderParam("tag")String tag, AddQuestionsBean addQuestionsBean){
        return resourceService.addQuestions(tag,addQuestionsBean);
    }


    /**
     * 学生交卷
     */
    @POST
    @Path("/markingPaper")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean markingPaper(@HeaderParam("tag")String tag, DiagnoseAnswerSubmitBean diagnoseAnswerSubmitBean){
        return resourceService.markingPaper(tag,diagnoseAnswerSubmitBean);
    }


    /**
     * @return
     */
    @POST
    @Path("/getFromRedis")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getFromRedis(RedisParam redisParam){
        resourceService = new ResourceServiceImpl();
        return resourceService.getFromRedis(redisParam);

    }

    /**
     *
     * @param key 获取web-token：key=openId+0  app-token：key=openId+1
     * @param dbNum 数据库编号
     * @return
    {
    "requestId": "6f011247e7c845f6974ee06896916f42",
    "httpCode": "",
    "code": 200,
    "message": "success",
    "result":
    {
    "token": "TK002CD4C26504F470",
    "ttl": 7178（秒）
    }
    }
     */
    @GET
    @Path("/getToken")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean getToken(@QueryParam("key") String key,@QueryParam("dbNum") int dbNum){
        resourceService = new ResourceServiceImpl();
        return resourceService.getToken(key,dbNum);
    }
}
