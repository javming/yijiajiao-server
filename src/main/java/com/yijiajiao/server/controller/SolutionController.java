package com.yijiajiao.server.controller;

import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.post.*;
import com.yijiajiao.server.bean.solution.Solution;
import com.yijiajiao.server.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

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
    public ResultBean updateAsk(@HeaderParam("tag")String tag, UpdateAskBean updateAskBean){

        return solutionService.updateAsk(tag,updateAskBean);

    }

    /**
     *（教师端）回传解析
     */
    @POST
    @Path("/answer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean updateAnswer(@HeaderParam("tag")String tag,UpdateAnswerBean updateAnswerBean){

        return solutionService.updateAnswer(tag,updateAnswerBean);

    }

    /**
     * Web提交教师答疑介绍信息
     */
    @POST
    @Path("/addDoubt")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean addDoubt(@HeaderParam("tag")String tag,AddDoubtBean addDoubtBean){

        return solutionService.addDoubt(tag,addDoubtBean);

    }

    /**
     * Web编辑教师答疑介绍信息
     */
    @POST
    @Path("/updateDoubt")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean updateDoubt(@HeaderParam("tag")String tag,AddDoubtBean addDoubtBean){

        return solutionService.updateDoubt(tag,addDoubtBean);

    }

    /**
     * （学生端）提交答疑投诉
     */
    @POST
    @Path("/addComplain")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean addComplain(@HeaderParam("tag")String tag,AddComplainBean addComplainBean){

        return solutionService.addComplain(tag,addComplainBean);

    }

    /**
     * （教师端）回复评价
     */
    @POST
    @Path("/reBackComplain")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean reBackComplain(@HeaderParam("tag")String tag,ReBackComplainBean reBackComplainBean){

        return solutionService.reBackComplain(tag,reBackComplainBean);

    }

    /**
     * Web端教师添加时长
     */
    @POST
    @Path("/addTimePakage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean addTimePakage(@HeaderParam("tag")String tag, AddTimePakageBean addTimePakageBean){

        return solutionService.addTimePakage(tag, addTimePakageBean);

    }

    /**
     * 学生评价教师答疑
     */
    @POST
    @Path("/solutionAppraise")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean solutionAppraise(@HeaderParam("tag")String tag,AppraiseSolutionBean appraiseSolutionBean){

        return solutionService.solutionAppraise(tag,appraiseSolutionBean);

    }

    /**
     * 教师添加答疑反馈
     */
    @POST
    @Path("/solutionFeedBack")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean solutionFeedBack(@HeaderParam("tag")String tag,SolutionFeedBackBean solutionFeedBackBean){

        return solutionService.solutionFeedBack(tag,solutionFeedBackBean);

    }


    /* --------------------------------vvvvv新版答疑vvvvv--------------------------------------------------- */


    /**
     * 学生提交答疑
     */
    @POST
    @Path( "/commit" )
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean commitSolution(Solution solution){

        return solutionService.commitSolution(solution);

    }

    /**
     * 老师查看自己的答疑列表
     *
     * @param teacherId 老师openId
     * @param status    答疑状态（是否完成）
     */
    @GET
    @Path("/teacher/solutionList")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean teacherSolutionList( @QueryParam("teacherId") String teacherId, @QueryParam("status") Integer status){

        return solutionService.teacherSolutionList( teacherId, status);

    }

    /**
     * 查看答疑详情
     *
     * @param solutionId solutionId
     */
    @GET
    @Path("/solutionInfoById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean solutionInfoById(@QueryParam("solutionId") String solutionId) {

        return solutionService.solutionInfoById(solutionId);

    }

    /**
     * 老师接单
     *
     * @param planWaitTime 预计解答时间 int/分钟
     */
    @PUT
    @Path("/receive")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean receiveSolution( @QueryParam("solutionId") String solutionId,
                                       @QueryParam("planWaitTime") Integer planWaitTime) {

        return solutionService.receiveSolution(solutionId, planWaitTime);

    }

    /**
     * 老师拒接
     */
    @PUT
    @Path("/reject")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean rejectSolution( Map<String, Object> reject) {

        return solutionService.rejectSolution(reject);

    }

    /**
     * 老师回传答案
     *
     * @param param 参数数据结构
     * {
            "solutionId":"18",
            "description":"这个题我不会",
            "videoUrl":"http://gaopin-preview.bj.bcebos.com/133104060366.jpg@!420

            ",
            "videoLength":20,
            "knowledge":[
                {
                "knowledgeCode":"knowledgeCode1",
                "knowledgeName":"knowledgeName1"
                }
            ]
        }
     */
    @POST
    @Path("/answer/upload")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean uploadAnswer(Map<String, Object> param) {

        return solutionService.uploadAnswer(param);

    }

    /**
     * 学生查询答案
     *
     * @param solutionId solutionId
     */
    @GET
    @Path("/answer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean answerInfo(@QueryParam("solutionId") String solutionId) {

        return solutionService.answerInfo(solutionId);

    }

    /**
     * 学生支付
     *
     * @param solutionId solutionId
     */
    @PUT
    @Path("/paySolution")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean pay(@QueryParam("solutionId") String solutionId) {

        return solutionService.pay(solutionId);

    }

    /**
     * 答疑时长充值
     * @param recharge
     * {
            "openId":"st",
            "orderId":"0000002",
            "duration":250
        }
     */
    @POST
    @Path("/durationRecharge")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean recharge(Map<String, Object> recharge){

        return solutionService.recharge(recharge);

    }

    /**
     * 时长余额
     *
     */
    @GET
    @Path("/durationBalance")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getBalance(@QueryParam("openId") String openId){

        return solutionService.getBalance(openId);

    }


}
