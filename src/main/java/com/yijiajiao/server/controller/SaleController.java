package com.yijiajiao.server.controller;

import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.post.CreateOrderBean;
import com.yijiajiao.server.bean.post.CreateRefundBean;
import com.yijiajiao.server.bean.post.UpdateAppraiseBean;
import com.yijiajiao.server.bean.post.UpdateAppraiseRebackBean;
import com.yijiajiao.server.service.SaleService;
import com.yijiajiao.server.service.WaresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-16-9:42
 */
@Path("/sale")
@Controller
public class SaleController {
    @Autowired
    private SaleService saleService;
    @Autowired
    private WaresService waresService;

    /**
     * 获取签名结果
     */
    @GET
    @Path("/getsign")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getOrderSign(@HeaderParam("openId") String openId, @QueryParam("orderNo") String orderNo,
                                   @QueryParam("payType") String payType, @QueryParam("payMent") int payMent,
                                   @QueryParam("returnWapUrl")String returnWapUrl) {

        return saleService.getOrderSign(openId, orderNo, payType,payMent,returnWapUrl);

    }

    /**
     * 查询订单列表
     */
    @GET
    @Path("/orderlist")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getOrderList(@HeaderParam("openId") String openId, @QueryParam("pageNo") int pageNo,
                                   @QueryParam("pageSize") int pageSize, @QueryParam("status") String status,
                                   @QueryParam("curriculumType") int curriculumType) {

        return saleService.getOrderList(openId, pageNo, pageSize, status,curriculumType);

    }

    /**
     * 根据ID查询订单详情
     */
    @GET
    @Path("/orderById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getOrderById(@HeaderParam("openId") String openId,@QueryParam("id") int id) {

        return saleService.getOrderById(openId, id);

    }

    /**
     * 根据订单号查询订单详情
     */
    @GET
    @Path("/orderByorderNo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getOrderByOrderNo(@HeaderParam("openId") String openId,@QueryParam("orderNo") String orderNo) {

        return saleService.getOrderByOrderNo(openId, orderNo);

    }

    /**
     * 取消或者删除订单
     */
    @GET
    @Path("/delOrderById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean delOrderById(@HeaderParam("openId") String openId,
                                   @QueryParam("id") int id,
                                   @QueryParam("status") String status,
                                   @QueryParam("cancel") String cancelReason,
                                   @QueryParam("canceldesc") String canceldesc) {

        return saleService.delOrderById(openId, id, status, cancelReason, canceldesc);

    }

    /**
     * 根据日期获取某个学生的课程表
     */
    @GET
    @Path("/stutimetable")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean stuTimeTable(@QueryParam("startDate") String startDate,
                                   @QueryParam("endDate") String endDate,
                                   @HeaderParam("openId") String openId) {

        return saleService.stuTimeTable(startDate, endDate, openId);

    }

    /**
     * 个人中心获取某天课程详情
     */
    @GET
    @Path("/stutimetableinfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean stuTimeTableInfo(@QueryParam("date") String date,@HeaderParam("openId") String openId) {

        return saleService.stuTimeTableInfo(date, openId);

    }

    /**
     * 个人中心我的课程
     */
    @GET
    @Path("/studentClassList")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean studentClassList(@QueryParam("curriculumType") String curriculumType,
                                       @HeaderParam("openId") String openId,
                                       @QueryParam("pageNo") int pageNo,
                                       @QueryParam("pageSize") int pageSize,
                                       @QueryParam("subjectCode") String subjectCode,
                                       @QueryParam("gradeCode") String gradeCode) {

        return saleService.getStuClassList(openId, curriculumType, pageNo, pageSize, subjectCode, gradeCode);

    }

    /**
     * 根据日期获取某个学生评价列表
     */
    @GET
    @Path("/appraiselist")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getAppraiseList(@HeaderParam("openId") String openId,
                                      @QueryParam("pageNo") int pageNo,
                                      @QueryParam("pageSize") int pageSize,
                                      @QueryParam("appraiseType") String appraiseType,
                                      @QueryParam("curriculumType") String curriculumType) {

        return saleService.getAppraiseList(curriculumType, openId, appraiseType, pageNo, pageSize);

    }

    /**
     * 教师店铺评价列表
     */
    @GET
    @Path("/appraiselist4teacher")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getAppraiseTecaehrList(@HeaderParam("openId") String openId,
                                             @QueryParam("pageNo") int pageNo,
                                             @QueryParam("pageSize") int pageSize,
                                             @QueryParam("appraiseType") String appraiseType,
                                             @QueryParam("curriculumType") String curriculumType) {

        return saleService.getAppraiseTecaehrList(curriculumType, openId, appraiseType, pageNo, pageSize);

    }


    /**
     * 根据商品ID获取评价列表
     */
    @GET
    @Path("/appraiseListByCurriculumId")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean AppraiseListByCurriculumId(@QueryParam("curriculumId")int curriculumId,
                                                 @QueryParam("pageNo")int pageNo,
                                                 @QueryParam("pageSize") int pageSize) {

        return saleService.AppraiseListByCurriculumId(curriculumId, pageNo, pageSize);

    }

    /**
     * 评价有用
     */
    @GET
    @Path("/isUsefulAppraise")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean isUsefulAppraise(@HeaderParam("openId") String openId, @QueryParam("id") int id) {

        return saleService.isUsefulAppraise(openId, id);

    }

    /**
     * 教师店铺学生列表
     */
    @GET
    @Path("/myStudents")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean myStudents(@HeaderParam("openId") String openId,
                                 @QueryParam("curriculumType") int curriculumType,
                                 @QueryParam("pageNo") int pageNo,
                                 @QueryParam("pageSize") int pageSize) {

        return saleService.myStudents(openId, curriculumType, pageNo, pageSize);

    }

    /**
     * 视频播放列表
     */
    @GET
    @Path("/getVediosById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getVediosById(@HeaderParam("openId") String openId, @QueryParam("id") int id) {

        return saleService.getVediosById(openId, id);

    }

    /**
     * 教师店铺订单列表
     */
    @GET
    @Path("/getOrderList4Teacher")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getOrderList4Teacher(@HeaderParam("openId") String openId,
                                           @QueryParam("pageSize") int pageSize,
                                           @QueryParam("orderStatus") int orderStatus,
                                           @QueryParam("pageNo") int pageNo,
                                           @QueryParam("commodityType") int commodityType) {

        return saleService.getOrderList4Teacher(openId, commodityType, orderStatus, pageNo, pageSize);

    }

    /**
     * 教师店铺同意或拒绝退款订单
     */
    @GET
    @Path("/choseReturnOrder")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean choseReturnOrder(@HeaderParam("openId") String openId,
                                       @QueryParam("orderNumber") String orderNumber,
                                       @QueryParam("refundType") int refundType,
                                       @QueryParam("objection") String objection ,
                                       @QueryParam("objectionCause") int objectionCause) {

        return saleService.choseReturnOrder(openId, orderNumber,refundType,objection,objectionCause);

    }

    /**
     * 教师店铺同意或拒绝退款订单
     */
    @GET
    @Path("/delOrder4Teacher")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean delOrder4Teacher(@HeaderParam("openId") String openId, @QueryParam("id") int id) {

        return saleService.delOrder4Teacher(openId, id);

    }


    /**
     * 教师店铺查看订单详情
     */
    @GET
    @Path("/orderInfo4Teacher")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean orderInfo4Teacher(@HeaderParam("openId") String openId, @QueryParam("id") int id) {

        return saleService.orderInfo4Teacher(openId, id);

    }

    /**
     * 答疑时长商品列表
     */
    @GET
    @Path("/solutionWaresList")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean solutionWaresList() {

        return saleService.solutionWaresList();

    }

    /**
     *  学生端课程列表
     */
    @GET
    @Path("/studentWaresList")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean studentWaresList( @HeaderParam("openId") String openId,
                                        @QueryParam("pageNo") int pageNo,
                                        @QueryParam("curriculumType") int curriculumType,
                                        @QueryParam("pageSize") int pageSize) {

        return saleService.studentWaresList(openId,curriculumType,pageNo,pageSize);

    }

    /**
     *我的教师列表
     */
    @GET
    @Path("/myStu4List")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean myStu4List(@HeaderParam("openId") String openId,
                                 @QueryParam("curriculumType") int curriculumType,
                                 @QueryParam("pageNo") int pageNo,
                                 @QueryParam("pageSize") int pageSize) {

        return saleService.myStu4List(openId,curriculumType,pageNo,pageSize);

    }

    /**
     *  老师/学生-账户管理-交易记录
     */
    @GET
    @Path("/tradingList")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean tradingList(@HeaderParam("openId") String openId, @QueryParam("pageNo") int pageNo,
                                  @QueryParam("pageSize") int pageSize, @QueryParam("type") int type) {

        return saleService.tradingList(openId,pageNo,pageSize,type);

    }

    /**
     *  老师-账户管理统计
     */
    @GET
    @Path("/countAccount")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean countAccount(@HeaderParam("openId") String openId) {

        return saleService.countAccount(openId);

    }

    /**
     *  老师-账户管理 --课程按类型统计
     */
    @GET
    @Path("/curriculumTypeCount")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean curriculumTypeCount(@HeaderParam("openId") String openId) {

        return saleService.curriculumTypeCount(openId);

    }

    /**
     *  教师发作业
     */
    @GET
    @Path("/teacherWork")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean teacherWork(@HeaderParam("openId") String openId,
                                  @QueryParam("commodityId") int commodityId,
                                  @QueryParam("slaveId") int slaveId,
                                  @QueryParam("homeWorkCode") String homeWorkCode){
        ResultBean result;
        if(slaveId==-1){
            result = waresService.homework(commodityId, "wares",homeWorkCode);
        }else{
            result = waresService.homework(slaveId, "wareslive",homeWorkCode);
        }
        if(result.getCode() != 200) return result;
        return saleService.teacherWork(openId,commodityId,slaveId,homeWorkCode);

    }

    /**
     *	教师店铺查询购买某个课程的学生
     */
    @GET
    @Path("/getStudent")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getStudent(@HeaderParam("openId") String openId,
                                 @QueryParam("commodityId") String commodityId,
                                 @QueryParam("teacherId")String teacherId,
                                 @QueryParam("pageNo")int pageNo,
                                 @QueryParam("pageSize")int pageSize){

        return saleService.getStudent(commodityId,teacherId,pageNo,pageSize);

    }

    /**
     *	我的答疑列表（交易记录）
     */
    @GET
    @Path("/getMyAnswerList")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getMyAnswerList(@HeaderParam("openId") String openId,
                                      @QueryParam("pageNo") int pageNo,
                                      @QueryParam("pageSize") int pageSize,
                                      @QueryParam("answerId")int answerId){

        return saleService.getMyAnswerList(openId,pageNo,pageSize,answerId);

    }

    /**
     *	查询我的老师或者我的学生的数量
     */
    @GET
    @Path("/getMyCount")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getMyCount(@HeaderParam("openId") String openId){

        return saleService.getMyCount(openId);

    }

    /**
     *	移动端获取某天学生课表
     */
    @GET
    @Path("/syllabusPhone")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean syllabusPhone(@HeaderParam("openId") String openId, @QueryParam("startTime") String startTime){

        return saleService.syllabusPhone(openId,startTime);

    }

    /**
     *  校验时间冲突
     */
    @GET
    @Path("/checkTime")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean checkTime(@HeaderParam("token") String token,
                                @HeaderParam("openId") String openId,
                                @QueryParam("startTime") String startTime,
                                @QueryParam("endTime") String endTime){

        return saleService.checkTime(openId,startTime, endTime);

    }

    /**
     * 根据类型查询我的学生列表信息
     */
    @GET
    @Path("/myStudentsByCurriculumType")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean myStudentsByCurriculumType(@HeaderParam("openId") String openId,
                                                 @QueryParam("pageNo")int pageNo,
                                                 @QueryParam("pageSize")int pageSize,
                                                 @QueryParam("curriculumType") Integer curriculumType){

        return saleService.myStudentsByCurriculumType(openId,curriculumType,pageNo,pageSize);

    }

    /**
     *
     */
    @POST
    @Path("/updateAppraise")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean updateAppraise( UpdateAppraiseBean updateAppraiseBean ){

        return saleService.updateAppraise( updateAppraiseBean );
    }

    /**
     * 提交订单
     */
    @POST
    @Path("/createOrder")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean createOrder( CreateOrderBean createOrderBean ){

        return saleService.createOrder( createOrderBean );
    }

    /**
     *
     */
    @POST
    @Path("/updateAppraiseReback")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean updateAppraiseReback( UpdateAppraiseRebackBean updateAppraiseRebackBean ){

        return saleService.updateAppraiseReback( updateAppraiseRebackBean );

    }

    /**
     * 退款
     */
    @POST
    @Path("/createRefund")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean createRefund( CreateRefundBean createRefundBean ){

        return saleService.createRefund( createRefundBean );
    }


    /**
     * 我的学习中心 课程详情
     *@since  2017/8/9 10:33
     */
    @GET
    @Path("/getClassByOrderNumer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getClassByOrderNumer( @QueryParam("orderNum") String orderNum ){

        return saleService.getClassByOrderNumer(orderNum);

    }

}
