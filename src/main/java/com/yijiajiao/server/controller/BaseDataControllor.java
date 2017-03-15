package com.yijiajiao.server.controller;

import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.post.*;
import com.yijiajiao.server.service.BaseDataService;
import com.yijiajiao.server.service.OSSService;
import com.yijiajiao.server.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.URLEncoder;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-05-10:01
 */

@Path("/resource")
@Controller
public class BaseDataControllor {

    private static final Logger log = LoggerFactory.getLogger(BaseDataControllor.class);

    @Autowired
    private BaseDataService baseDataService;
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

        return baseDataService.knowledges(subjectCode, gradeCode, bookTypeCode);

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

        return baseDataService.questionByKnowledge(subject, grade, knowledge,pageNo,pageSize);

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

        return baseDataService.examList(openId,gradeName,subjectName,bookTypeName,11,pageNo,pageSize);

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
            return baseDataService.searchTeacher(pageNo,pageSize,order,orderType,keyword);
        }else{
            return baseDataService.searchWares(pageNo,pageSize,subjectCode,gradeCode,bookTypeCode,curriculumType,keyword,
                    order,orderType,reservePrice,peakPrice);
        }

    }


    @GET
    @Path("/nowledgeTree")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean nowledgeByGradeSubjectBooktype(@QueryParam("gradeCode") String gradeCode,@QueryParam("subjectCode")
                                                String subjectCode,@QueryParam("bookTypeCode")String bookTypeCode){

        return baseDataService.nowledgeByGradeSubjectBooktype(gradeCode,subjectCode,bookTypeCode);

    }

    /**
     * 手动组卷添加卷头
     */
    @POST
    @Path("/createExam")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean createExamHead(CreateExamBean createExamBean){
        return baseDataService.createExamHead(createExamBean);
    }

    /**
     * 手动组卷详情
     */
    @POST
    @Path("/createExamDetail")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean createExamDetail(CreateExamDetailBean createExamDetailBean){
        return baseDataService.createExamDetail(createExamDetailBean);
    }

    /**
     * 智能组卷
     */
    @POST
    @Path("/smartCreateExam")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean smartCreateExam(SmartCreateExamBean smartCreateExamBean){
        return baseDataService.smartCreateExam(smartCreateExamBean);
    }

    /**
     * 手动组卷添加试题
     */
    @POST
    @Path("/addQuestions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean addQuestions(AddQuestionsBean addQuestionsBean){
        return baseDataService.addQuestions(addQuestionsBean);
    }
    /**
     * 学生交卷
     */
    @POST
    @Path("/markingPaper")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean markingPaper(DiagnoseAnswerSubmitBean diagnoseAnswerSubmitBean){
        return baseDataService.markingPaper(diagnoseAnswerSubmitBean);
    }



}
