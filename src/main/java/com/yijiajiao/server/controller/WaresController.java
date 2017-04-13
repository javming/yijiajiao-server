package com.yijiajiao.server.controller;

import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.post.*;
import com.yijiajiao.server.bean.wares.BackUrlInfoParamBean;
import com.yijiajiao.server.bean.wares.BackUrlListParamBean;
import com.yijiajiao.server.bean.wares.M3JoinMtgParam;
import com.yijiajiao.server.service.WaresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-12-14:50
 */

@Path("/wares")
@Controller
public class WaresController {

    @Autowired
    private WaresService waresService;

    /**
     * 根据日期获取某个教师的课程表
     */
    @GET
    @Path("/timetable")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean timeTable(@HeaderParam("openId") String openId,
                                @QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {

        return waresService.timeTable(startDate, endDate, openId);

    }

    /**
     * 根据日期获取某天教师的课程信息列表
     */
    @GET
    @Path("/getwaresbydate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getWaresByDate(@HeaderParam("openId") String teacherId,
                                     @QueryParam("classDate") String classDate, @QueryParam("waresType") String waresType) {

        return waresService.getWaresByDate(classDate, waresType, teacherId);

    }

    /**
     * 课程首页列表
     */
    @GET
    @Path("/wareslist")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean wareslist(@QueryParam("curriculumType") Integer curriculumType, @QueryParam("subjectCode") String subjectCode, @QueryParam("gradeCode") String gradeCode,
                                @QueryParam("bookTypeCode") String bookTypeCode, @QueryParam("pageNo") int pageNo, @QueryParam("pageSize") int pageSize, @QueryParam("order") String order,
                                @QueryParam("orderType") String orderType,@QueryParam("stageCode") String stageCode,@QueryParam("reservePrice") String reservePrice,
                                @QueryParam("peakPrice") String peakPrice,@QueryParam("smallCourseware") String smallCourseware,@QueryParam("teacherId") String teacherId,
                                @QueryParam("status") String status,@QueryParam("isYjj")String isYjj,@QueryParam("yjjCode")String yjjCode){

        return waresService.wareslist(pageNo, pageSize, subjectCode, gradeCode,
                    bookTypeCode, curriculumType, order, orderType,stageCode,reservePrice,
                    peakPrice,smallCourseware,teacherId,status,isYjj,yjjCode);

    }

    /**
     * 课程详情页
     */
    @GET
    @Path("/waresInfoById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean waresInfoById(@QueryParam("id") int id) {

        return waresService.waresInfoById(id);

    }

    /**
     * 教师获取个人视频列表
     */
    @GET
    @Path("/getViedoById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getViedoById(@HeaderParam("token") String token, @HeaderParam("openId") String openId, @QueryParam("gradeCode") String gradeCode, @QueryParam("bookTypeCode") String bookTypeCode,@QueryParam("useType")int useType) {

        return waresService.getViedoById(openId, gradeCode, bookTypeCode,useType);

    }

    /**
     * 教师按照审核状态获取个人视频列表
     */
    @GET
    @Path("/getViedoByExamination")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getViedoByExamination(@HeaderParam("token") String token, @HeaderParam("openId") String openId, @QueryParam("gradeCode") String gradeCode,
                                            @QueryParam("bookTypeCode") String bookTypeCode, @QueryParam("examination") String examination, @QueryParam("pageNo") int pageNo, @QueryParam("pageSize") int pageSize) {

        return waresService.getViedoByExamination(openId, gradeCode, bookTypeCode, examination, pageNo, pageSize);

    }

    /**
     * 验证教师课程时间冲突
     */
    @GET
    @Path("/checktime")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean checkTime(@HeaderParam("token") String token, @HeaderParam("openId") String openId, @QueryParam("startTime") String startTime,
                                @QueryParam("endTime") String endTime) {

        return waresService.checkTime(openId,startTime, endTime);

    }

    /**
     * 获取教师资格认证试题
     */
    @GET
    @Path("/examtest")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean TeacherExamTest(@HeaderParam("token") String token, @HeaderParam("openId") String openId, @QueryParam("code") String code) {

        return waresService.teacherExamTest(openId, code);

    }

    /**
     * 教师课程数量统计
     */
    @GET
    @Path("/countWaresByteacherId")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean countWaresByteacherId(@QueryParam("teacherId") String teacherId) {

        return waresService.countWaresByteacherId(teacherId);

    }


    @GET
    @Path("/waresListByType")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean waresListByType(@QueryParam("teacherId") String teacherId, @QueryParam("curriculumType") int curriculumType, @QueryParam("status") Integer status,
                                      @QueryParam("pageNo") int pageNo,@QueryParam("pageSize") int pageSize,@QueryParam("isYjj") String isYjj) {

        return waresService.waresListByType(teacherId, curriculumType,status,pageNo,pageSize,isYjj);

    }

    /**
     * getstage
     */
    @GET
    @Path("/getstage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getStage() {

        return waresService.getStage();

    }

    /**
     * getGradeByStage
     */
    @GET
    @Path("/getGradeByStage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getGradeByStage(@QueryParam("stageCode") String stageCode) {

        return waresService.getGradeByStage(stageCode);

    }

    /**
     * getSubjectByGrade
     * @param gradeCode
     * @return
     */
    @GET
    @Path("/getSubjectByGrade")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getSubjectByGrade(@QueryParam("gradeCode") String gradeCode) {

        return waresService.getSubjectByGrade(gradeCode);

    }

    /**
     * 移动端 - 获取某老师正在进行中的商品列表
     */
    @GET
    @Path("/mobilWaresListByTeacherIdAndTime")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean mobilWaresListByTeacherIdAndTime(@HeaderParam("openId") String openId, @HeaderParam("token") String token,
                                                       @QueryParam("pageNo") int pageNo, @QueryParam("pageSize") int pageSize,@QueryParam("cover") String cover,
                                                       @QueryParam("sales")String sales,@QueryParam("maxNumber")String maxNumber) {

        return waresService.mobilWaresListByTeacherIdAndTime(openId, pageNo, pageSize,cover,sales,maxNumber);

    }

    /**
     * 移动端 - 获取某老师所有的商品列表
     */
    @GET
    @Path("/mobilWaresListByTeacherId")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean mobilWaresListByTeacherId(@HeaderParam("openId") String openId, @HeaderParam("token") String token,
                                                @QueryParam("pageNo") int pageNo, @QueryParam("pageSize") int pageSize,
                                                @QueryParam("curriculumType") int curriculumType) {

        return waresService.mobilWaresListByTeacherId(openId, pageNo, pageSize, curriculumType);

    }

    /**
     * 移动端 - 获取某老师-某商品的详情列表
     */
    @GET
    @Path("/mobilWaresInfoByWaresId")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean mobilWaresInfoByWaresId(@HeaderParam("openId") String openId, @HeaderParam("token") String token,
                                              @QueryParam("waresId") int waresId, @QueryParam("subjectCode") String subjectCode,
                                              @QueryParam("gradeCode")String gradeCode,@QueryParam("bookTypeCode")String bookTypeCode) {

        return waresService.mobilWaresInfoByWaresId(openId, waresId,subjectCode,gradeCode,bookTypeCode);

    }

    /**
     * 商品上下架
     */
    @GET
    @Path("/releaseStatus")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean releaseStatus(@HeaderParam("openId") String openId, @HeaderParam("token") String token,
                                    @QueryParam("waresId") int waresId,@QueryParam("status") int status) {

        return waresService.releaseStatus(openId, waresId,status);

    }

    /**
     * 商品上下架
     */
    @GET
    @Path("/delvideo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean delVideo(@HeaderParam("openId") String openId, @HeaderParam("token") String token,
                               @QueryParam("videoId") int videoId) {

        return waresService.delVideo(videoId);

    }

    /**
     * 通过审核录播视频数量
     */
    @GET
    @Path("/videoCountByStatus")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean videoCountByStatus(@HeaderParam("openId") String openId, @HeaderParam("token") String token, @QueryParam("status") int status) {

        return waresService.videoCountByStatus(openId,status);

    }

    /**
     * 诊断试卷列表
     */
    @GET
    @Path("/examList")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean examList(@QueryParam("subjectCode") String subjectCode,@QueryParam("gradeCode") String gradeCode,@QueryParam("bookTypeCode") String bookTypeCode) {

        return waresService.examList(subjectCode,gradeCode,bookTypeCode);

    }

    /**
     * 试卷答题详情
     */
    @GET
    @Path("/answerInfoById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean answerInfoById(@HeaderParam("openId") String openId, @HeaderParam("token") String token,@QueryParam("id") int id) {

        return waresService.answerInfoById(id);

    }

    /**
     * 试卷分析
     * @param openId
     * @return
     */
    @GET
    @Path("/examStatistic")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean examStatistic(@HeaderParam("openId") String openId, @HeaderParam("token") String token,@QueryParam("id") int id) {

        return waresService.examStatistic(id);

    }

    /**
     * 人气
     */
    @GET
    @Path("/popularity")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean popularity(@QueryParam("waresId") String waresId,@QueryParam("popType") String popType) {

        return waresService.popularity(waresId,popType);

    }

    /**
     *@description		直播回放
     *@date 2016-6-29
     */
    @GET
    @Path("/liveVideoPlayback")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean liveVideoPlayback(@HeaderParam("openId") String openId, @HeaderParam("token") String token,
                                        @QueryParam("waresId") int waresId,@QueryParam("slaveId")int slaveId){

        return waresService.liveVideoPlayback(waresId,slaveId);

    }

    /**
     *  获取我的试卷列表
     */
    @GET
    @Path("/getMyPapers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getMyPapers(@HeaderParam("openId") String openId, @HeaderParam("token") String token,
                                  @QueryParam("pageNo") int pageNo,@QueryParam("pageSize") int pageSize,
                                  @QueryParam("useType") Integer useType, @QueryParam("gradeCode") String gradeCode,
                                  @QueryParam("subjectCode") String subjectCode){

        return waresService.getMyPapers(openId,pageNo,pageSize,useType,gradeCode,subjectCode);

    }

    /**
     *	获取试卷详情
     */
    @GET
    @Path("/getPaperInfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getPaperInfo(@QueryParam("paperId") String paperId){

        return waresService.getPaperInfo(paperId);

    }

    /**
     *	获取今日开讲课程列表
     */
    @GET
    @Path("/todayCourse")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean todayCourse(){

        return waresService.todayCourse();

    }

    /**
     *	学生获取已做过的试卷的结果
     */
    @GET
    @Path("/paperCommitInfoByStudent")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean paperCommitInfoByStudent(@HeaderParam("openId") String openId, @HeaderParam("token") String token,
                                               @QueryParam("waresId") Integer waresId,@QueryParam("waresSlaveId") Integer waresSlaveId,
                                               @QueryParam("paperId")String paperId){

        return waresService.paperCommitInfoByStudent(openId,waresId,waresSlaveId,paperId);

    }

    /**
     *	教师获取学生解答试卷结果
     */
    @GET
    @Path("/paperCommitInfoByTeacher")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean paperCommitInfoByTeacher(@HeaderParam("openId") String openId, @HeaderParam("token") String token,
                                               @QueryParam("waresId") String waresId,@QueryParam("waresSlaveId") String waresSlaveId,@QueryParam("paperId")String paperId){

        return waresService.paperCommitInfoByTeacher(waresId,waresSlaveId,paperId);

    }

    /**
     *  检验学生是否交过卷
     */
    @GET
    @Path("/paperInfoByStudent")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getPaperInfoByStudent(@HeaderParam("openId") String openId, @HeaderParam("token") String token,
                                            @QueryParam("waresId") String waresId,@QueryParam("waresSlaveId")String waresSlaveId,
                                            @QueryParam("paperId")String paperId){

        ResultBean result =  waresService.checkPaperCommitByStudent(waresId,waresSlaveId,paperId,openId);
        Integer check =1;
        if(check.equals(result.getResult())){//学生是否交过卷子（0表示没交过，1表示交过）
            System.out.println("该学生已经交过此卷！！");
            return result;
        }
        result = waresService.getPaperInfo(paperId);
        return result;

    }

    /**
     * 删除课程
     */
    @DELETE
    @Path("/deleteWaresById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean deleteWaresById(@HeaderParam("openId") String openId, @HeaderParam("token") String token,
                                      @QueryParam("waresId") int waresId){

        return waresService.deleteWaresById(waresId);

    }

    /**
     *  获取学点云课堂地址
     */
    @POST
    @Path("/liveWebUrl")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean liveWebUrl(@HeaderParam("openId") String openId, @HeaderParam("token") String token,
                                 M3JoinMtgParam m3JoinMtgParam){

        return waresService.liveWebUrl(m3JoinMtgParam);

    }

    /**
     * @description  学点云获取回访列表
     * @return
     */
    @POST
    @Path("/backUrlList")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean backUrlList(@HeaderParam("openId") String openId, @HeaderParam("token") String token,
                                  BackUrlListParamBean backUrlListParamBean){

        return waresService.backUrlList(backUrlListParamBean);

    }

    /**
     * @description  学点云获取回放路径
     * @return
     */
    @POST
    @Path("/backUrlInfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean backUrlInfo(@HeaderParam("openId") String openId, @HeaderParam("token") String token,
                                  BackUrlInfoParamBean backUrlInfoParamBean){

        return waresService.backUrlInfo(backUrlInfoParamBean);

    }

    /**
     * 移动端获取学点云课堂路径
     */
    @POST
    @Path("/appMtgInfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean appMtgInfo(@HeaderParam("openId") String openId, @HeaderParam("token") String token,
                                 M3JoinMtgParam m3JoinMtgParam){

        return waresService.appMtgInfo(m3JoinMtgParam);

    }

    /**
     *  上传视频
     */
    @POST
    @Path("/uploadVideo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean uploadVideo(@HeaderParam("tag")String tag, UploadVideoParamBean uploadVideoParamBean){
        return waresService.uploadVideo(tag,uploadVideoParamBean);
    }

    /**
     *  创建直播课
     */
    @POST
    @Path("/wareLive")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean wareLive(@HeaderParam("tag")String tag,WareLiveBean wareLiveBean){
        return waresService.wareLive(tag,wareLiveBean);
    }

    /**
     *  创建视频课
     */
    @POST
    @Path("/wareVideo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean wareVideo(@HeaderParam("tag")String tag,WareVideoBean wareVideoBean){
        return waresService.wareVideo(tag,wareVideoBean);
    }

    /**
     * 创建一对一课
     */
    @POST
    @Path("/wareOne2One")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean wareOne2One(@HeaderParam("tag")String tag,WareOne2OneBean wareOne2OneBean){
        return waresService.wareOne2One(tag,wareOne2OneBean);
    }

    /**
     *  提交诊断报告
     */
    @POST
    @Path("/commitExam")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean commitExam(@HeaderParam("tag")String tag,CommitExamBean commitExamBean){
        return waresService.commitExam(tag,commitExamBean);
    }

    /**
     *  修改课程
     */
    @POST
    @Path("/updateWaresLive")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean updateWaresLive(@HeaderParam("tag")String tag,WareLiveBean wareLiveBean){
        return waresService.updateWaresLive(tag,wareLiveBean);
    }

    /**
     * 检验学生是否交过卷
     */
    @GET
    @Path("/checkPaperCommitByStudent")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean checkPaperCommitByStudent(@HeaderParam("openId")String openId,
                                                @QueryParam("waresId") String waresId,@QueryParam("paperId")String paperId,
                                                @QueryParam("waresSlaveId")String waresSlaveId){

        return waresService.checkPaperCommitByStudent(waresId,waresSlaveId,paperId,openId);
    }
}
