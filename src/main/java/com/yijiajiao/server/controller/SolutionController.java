package com.yijiajiao.server.controller;

import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.post.*;
import com.yijiajiao.server.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-04-15:31
 */
@Path("/solution")
@Controller
public class SolutionController {

    @Autowired
    private SolutionService solutionService;

    /**
     * 前端首页展示焦点图
     */
    @GET
    @Path("/getAnswering")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getAnswering(@HeaderParam("openId") String openId, @QueryParam("pageNo") int pageNo, @QueryParam("pageSize") int pageSize,
                                   @QueryParam("subjectCode") String subjectCode, @QueryParam("gradeCodes") String gradeCodes) {

        return solutionService.getAnswering(openId, pageNo, pageSize, subjectCode, gradeCodes);

    }

    /**
     * 获取单条待解答记录
     */
    @GET
    @Path("/getAnsweringById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getAnsweringById(@HeaderParam("openId") String openId, @QueryParam("id") int id) {

        return solutionService.getAnsweringById(openId, id);

    }

    /**
     * 学生端获取已解答答疑列表
     */
    @GET
    @Path("/getAnswered")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getAnswered(@HeaderParam("openId") String openId, @QueryParam("pageNo") int pageNo, @QueryParam("pageSize") int pageSize) {

        return solutionService.getAnswered(openId, pageNo, pageSize);

    }

    /**
     * 学生端获取自己已解答答疑详情
     */
    @GET
    @Path("/getAnsweredById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getAnsweredById(@HeaderParam("openId") String openId, @QueryParam("id") int id) {

        return solutionService.getAnsweredById(openId, id);

    }

    /**
     * 获取某教师的答疑介绍详情
     */
    @GET
    @Path("/getDoubtDetail")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getDoubtDetail(@QueryParam("teachId") String teachId) {

        return solutionService.getDoubtDetail(teachId);

    }

    /**
     * 教师端抢单
     */
    @GET
    @Path("/exitGrab")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean exitGrab(@HeaderParam("token") String token, @HeaderParam("openId") String openId,
                               @QueryParam("solutionId") int solutionId) {

        return solutionService.exitGrab(openId, solutionId);

    }

    /**
     * 学生段取消答疑订单
     */
    @GET
    @Path("/exitSolution")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean exitSolution(@HeaderParam("token") String token, @HeaderParam("openId") String openId,
                                   @QueryParam("solutionId") int solutionId) {

        return solutionService.exitSolution(openId, solutionId);

    }

    /**
     * web分页查询答疑教师列表
     */
    @GET
    @Path("/getsolutionteacherList")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getSolutionTeacherList(@QueryParam("pageNo") int pageNo, @QueryParam("pageSize") int pageSize,
                                             @QueryParam("subjectCode") String subjectCode,@QueryParam("gradeCode") String gradeCode,
                                             @QueryParam("bookTypeCode") String bookTypeCode, @QueryParam("orderType") String orderType,
                                             @QueryParam("stageCode")String stageCode,@QueryParam("name") String name,
                                             @QueryParam("openId") String openId,@QueryParam("order") String order) {

        return solutionService.getSolutionTeacherList(pageNo, pageSize, subjectCode, gradeCode, bookTypeCode,orderType,stageCode,name,openId,order);

    }

    /**
     * 学生段提交答疑时长
     */
    @GET
    @Path("/createSolutionCount")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean createSolutionCount(@QueryParam("solutionId") int id, @QueryParam("countTime") double countTime,
                                          @QueryParam("communicateTime") String communicateTime) {

        return solutionService.createSolutionCount(id, countTime,communicateTime);

    }


    /**
     * 答疑系数列表
     */
    @GET
    @Path("/ratio")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean ratio() {

        return solutionService.ratio();

    }



    /**
     * 教师端接单历史列表
     */
    @GET
    @Path("/solutionList")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean solutionList(@HeaderParam("openId") String openId, @QueryParam("pageNo") int pageNo,
                                   @QueryParam("pageSize") int pageSize ) {

        return solutionService.solutionList(openId,pageNo,pageSize);

    }



    /**
     * 教师端接单历史列表
     */
    @GET
    @Path("/solutionInfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean solutionInfo(@QueryParam("id") int id) {

        return solutionService.solutionInfo(id);

    }
    /**
     *	分页获取教师答疑包
     */
    @GET
    @Path("/getPackageReord")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getPackageReord(@QueryParam("teacherId") String teacherId,
                                      @QueryParam("pageNo") int pageNo,@QueryParam("pageSize")int pageSize,@QueryParam("sellStatus")int sellStatus,
                                      @QueryParam("studentId") String studentId){

        return solutionService.getPackageReord(teacherId,pageNo,pageSize,sellStatus,studentId);

    }
    /**
     *	根据id获取答疑包信息
     */
    @GET
    @Path("/getPackageById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getPackageById(@QueryParam("packageId") String packageId ){

        return solutionService.getPackageById(packageId);

    }

    /**
     *	获取答疑时长基础数据
     */
    @GET
    @Path("/duration")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getDuration(@QueryParam("gradeCode")String gradeCode,@QueryParam("subjectCode")String subjectCode){

        return solutionService.getDuration(gradeCode,subjectCode);

    }

    /**
     *	答疑包上下架
     */
    @GET
    @Path("/packagePutawayOrDown")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean packagePutawayOrDown(@QueryParam("packageId") String packageId,@QueryParam("shelfStatus") int shelfStatus){

        return solutionService.packagePutawayOrDown(packageId,shelfStatus);

    }
    /**
     *	分页获取购买的答疑老师信息
     */
    @GET
    @Path("/getMyDoubtTeachers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getMyDoubtTeachers(@HeaderParam("openId") String openId,
                                         @QueryParam("pageNo") int pageNo,@QueryParam("pageSize")int pageSize){

        return solutionService.getMyDoubtTeachers(openId,pageNo,pageSize);

    }

    /**
     *	分页查询答疑记录
     */
    @GET
    @Path("/getSolutions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getSolutions(@QueryParam("pageNo")int pageNo,@QueryParam("pageSize") int pageSize,
                                   @QueryParam("studentId")String studentId,@QueryParam("teacherId")String teacherId,
                                   @QueryParam("isAnswer") String isAnswer,@QueryParam("subjectCode") String subjectCode){

        return solutionService.getSolutions(teacherId,studentId,pageNo,pageSize,isAnswer,subjectCode);

    }
    /**
     *	答疑排行榜
     */
    @GET
    @Path("/getRecDoubtTeachers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getRecDoubtTeachers(@QueryParam("openId") String openId){

        return solutionService.getRecDoubtTeachers(openId);

    }
    /**
     * 学生获取已购买的答疑包列表
     */
    @GET
    @Path("/getMyPackages")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getMyPackages(@HeaderParam("openId") String openId,@QueryParam("pageNo")int pageNo,@QueryParam("pageSize") int pageSize){

        return solutionService.getMyPackages(openId,pageNo,pageSize);

    }

    /**
     *	获取答疑记录详情
     */
    @GET
    @Path("/getDetail")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getDetail(@QueryParam("id") int id){

        return solutionService.getDetail(id);

    }

    /**
     *	获取反馈答疑的理由
     */
    @GET
    @Path("/getFeedBackReasons")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getFeedBackReasons(){

        return solutionService.getFeedBackReasons();

    }

    /**
     *	根据教师openId及学年获取教师答疑包
     */
    @GET
    @Path("/getPackageByGrade")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getPackageByGrade(@QueryParam("teacherId")String teacherId,@QueryParam("gradeCode") String gradeCode){

        return solutionService.getPackageByGrade(teacherId,gradeCode);

    }

    /**
     *	根据教师openId 学年分组获取教师答疑包
     */
    @GET
    @Path("/getPackagesByOpenId")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getPackagesByOpenId(@QueryParam("openId") String openId){

        return solutionService.getPackagesByOpenId(openId);

    }

    /**
     *	根据环信id获取答疑教师头像和答疑图片
     */
    @GET
    @Path("/getEasemobId")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getEasemobId(@QueryParam("easemobId") String easemobId){

        return solutionService.getEasemobId(easemobId);

    }

    /**
     *@	教师打电话时提交答疑id
     */
    @GET
    @Path("/getSolId")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getSolId(@QueryParam("openId") String openId, @QueryParam("solId")int solId){

        return solutionService.getSolId(openId,solId);

    }

    /**
     *	获取答疑近似题
     */
    @GET
    @Path("/getQuestionByRand")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getQuestionByRand(@QueryParam("gradeCode") String gradeCode,@QueryParam("subjectCode") String subjectCode,
                                        @QueryParam("knowledgeCode") String knowledgeCode){

        return solutionService.getQuestionByRand(gradeCode,subjectCode,knowledgeCode);

    }


    /**
     * （学生端）提交答疑
     */
    @POST
    @Path("/ask")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean updateAsk(UpdateAskBean updateAskBean){

        return solutionService.updateAsk(updateAskBean);

    }

    /**
     *（教师端）回传解析
     */
    @POST
    @Path("/answer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean updateAnswer(UpdateAnswerBean updateAnswerBean){

        return solutionService.updateAnswer(updateAnswerBean);

    }

    /**
     * Web提交教师答疑介绍信息
     */
    @POST
    @Path("/addDoubt")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean addDoubt(AddDoubtBean addDoubtBean){

        return solutionService.addDoubt(addDoubtBean);

    }

    /**
     * Web编辑教师答疑介绍信息
     */
    @POST
    @Path("/updateDoubt")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean updateDoubt(AddDoubtBean addDoubtBean){

        return solutionService.updateDoubt(addDoubtBean);

    }

    /**
     * （学生端）提交答疑投诉
     */
    @POST
    @Path("/addComplain")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean addComplain(AddComplainBean addComplainBean){

        return solutionService.addComplain(addComplainBean);

    }

    /**
     * （教师端）回复评价
     */
    @POST
    @Path("/reBackComplain")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean reBackComplain(ReBackComplainBean reBackComplainBean){

        return solutionService.reBackComplain(reBackComplainBean);

    }

    /**
     * Web端教师添加时长
     */
    @POST
    @Path("/addTimePakage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean addTimePakage(AddTimePakageBean addTimePakageBean){

        return solutionService.addTimePakage(addTimePakageBean);

    }

    /**
     * 学生评价教师答疑
     */
    @POST
    @Path("/solutionAppraise")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean solutionAppraise(AppraiseSolutionBean appraiseSolutionBean){

        return solutionService.solutionAppraise(appraiseSolutionBean);

    }

    /**
     * 教师添加答疑反馈
     */
    @POST
    @Path("/solutionFeedBack")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean solutionFeedBack(SolutionFeedBackBean solutionFeedBackBean){

        return solutionService.solutionFeedBack(solutionFeedBackBean);

    }


}
