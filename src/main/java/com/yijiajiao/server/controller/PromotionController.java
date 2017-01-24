package com.yijiajiao.server.controller;

import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.SystemStatus;
import com.yijiajiao.server.service.PromotionService;
import com.yijiajiao.server.service.impl.PromotionServiceImpl;
import com.yijiajiao.server.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-05-14:42
 */
@Path("/promotion")
@Controller
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    /**
     * 设置优惠券状态（上线/下线/删除）
     */
    @Path("/operateCouponState")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean operateCouponState(@HeaderParam("token")String token,@HeaderParam("openId")String openId,
                                   @QueryParam("couponId") int couponId,@QueryParam("state")String state){

        if (!TokenUtil.verifyToken(token,openId)){
            return ResultBean.getFailResult(SystemStatus.TOKEN_TIME_OUT);
        }
        promotionService = new PromotionServiceImpl();
        return promotionService.operateCouponState(couponId,state);

    }

    /**
     * 设置优惠券主页展示
     */
    @Path("/pageShowCoupon")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean pageShowCoupon(@HeaderParam("token")String token,@HeaderParam("openId")String openId,
                                     @QueryParam("couponId") int couponId,@QueryParam("display") String display){

        if (!TokenUtil.verifyToken(token,openId)){
            return ResultBean.getFailResult(SystemStatus.TOKEN_TIME_OUT);
        }
        promotionService = new PromotionServiceImpl();
        return promotionService.pageShowCoupon(couponId,display);

    }


    /**
     * 老师发送优惠券给学生
     */
    @Path("/sendCouponCode2Stu")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean sendCouponCode2Stu(@HeaderParam("token")String token,@HeaderParam("openId")String openId,
                                         @QueryParam("couponId")int couponId,@QueryParam("stuOpenids")String stuOpenids){

        if (!TokenUtil.verifyToken(token,openId)){
            return ResultBean.getFailResult(SystemStatus.TOKEN_TIME_OUT);
        }
        promotionService = new PromotionServiceImpl();
        return promotionService.sendCouponCode2Stu(couponId,stuOpenids);

    }

    /**
     * 学生工作室主页领取优惠券
     */
    @Path("/acquireCouponCodeSelf")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean acquireCouponCodeSelf(@HeaderParam("token")String token,@HeaderParam("openId")String openId,
                                            @QueryParam("couponId")int couponId){

        if (!TokenUtil.verifyToken(token,openId)){
            return ResultBean.getFailResult(SystemStatus.TOKEN_TIME_OUT);
        }
        promotionService = new PromotionServiceImpl();
        return promotionService.acquireCouponCodeSelf(couponId,openId);

    }

    /**
     * 检验输入优惠码是否可用
     */
    @Path("/checkInputCouponCode")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean checkInputCouponCode(@HeaderParam("token")String token,@HeaderParam("openId")String openId,
                                           @QueryParam("couponCode")String couponCode,@QueryParam("wareId")Integer wareId,
                                           @QueryParam("teacherId")String teacherId){

        if (!TokenUtil.verifyToken(token,openId)){
            return ResultBean.getFailResult(SystemStatus.TOKEN_TIME_OUT);
        }
        promotionService = new PromotionServiceImpl();
        return promotionService.checkInputCouponCode(openId,couponCode,wareId,teacherId);
    }

    /**
     * 获取优惠券信息
     */
    @Path("/couponInfo")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean couponInfo(@QueryParam("id") int id ){

        promotionService = new PromotionServiceImpl();
        return promotionService.couponInfo(id);
    }

    /**
     * 我的工作室优惠券列表
     */
    @Path("/myCoupons4Teacher")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean myCoupons4Teacher(@HeaderParam("token")String token, @HeaderParam("openId")String openId,
                                 @QueryParam("pageNo") int pageNo,@QueryParam("pageSize")int pageSize,@QueryParam("state")int state,
                                 @QueryParam("minValue")Double minValue,@QueryParam("maxValue")Double maxValue){

        if (!TokenUtil.verifyToken(token,openId)){
            return ResultBean.getFailResult(SystemStatus.TOKEN_TIME_OUT);
        }
        promotionService = new PromotionServiceImpl();
        return promotionService.myCoupons4Teacher(openId,pageNo,pageSize,state,minValue,maxValue);
    }

    /**
     * 工作室主页优惠券列表
     */
    @Path("/coupons")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean coupons(@QueryParam("teacherId") String teacherId){

        promotionService = new PromotionServiceImpl();
        return promotionService.coupons(teacherId);

    }

    /**
     * 我的工作室查看优惠券领取和使用信息
     */
    @Path("/couponCodePageForShop")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean couponCodePageForShop(@HeaderParam("token")String token, @HeaderParam("openId")String openId,
                                        @QueryParam("pageNo") int pageNo,@QueryParam("pageSize")int pageSize,
                                        @QueryParam("couponId") int couponId,@QueryParam("type") int type){

        if (!TokenUtil.verifyToken(token,openId)){
            return ResultBean.getFailResult(SystemStatus.TOKEN_TIME_OUT);
        }
        promotionService = new PromotionServiceImpl();
        return promotionService.couponCodePageForShop(couponId,pageNo,pageSize,type);
    }

    /**
     * 我的学习中心查看优惠券列表
     */
    @Path("/couponCodePageForStu")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean couponCodePageForStu(@HeaderParam("token")String token, @HeaderParam("openId")String openId,
                                        @QueryParam("pageNo") int pageNo,@QueryParam("pageSize")int pageSize,
                                        @QueryParam("state") Integer state){

        if (!TokenUtil.verifyToken(token,openId)){
            return ResultBean.getFailResult(SystemStatus.TOKEN_TIME_OUT);
        }
        promotionService = new PromotionServiceImpl();
        return promotionService.couponCodePageForStu(openId,pageNo,pageSize,state);
    }

    /**
     * 可用的优惠券列表
     */
    @Path("/couponCodeListForStuOrder")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean couponCodeListForStuOrder(@HeaderParam("token")String token, @HeaderParam("openId")String openId,
                                        @QueryParam("teacherId") String teacherId,@QueryParam("wareId") String wareId){

        if (!TokenUtil.verifyToken(token,openId)){
            return ResultBean.getFailResult(SystemStatus.TOKEN_TIME_OUT);
        }
        promotionService = new PromotionServiceImpl();
        return promotionService.couponCodeListForStuOrder(openId,teacherId,wareId);
    }

    /**
     * 查看活动信息
     */
    @Path("/activityInfoById")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean activityInfoById(@QueryParam("activityId") int activityId){

        promotionService = new PromotionServiceImpl();
        return promotionService.activityInfoById(activityId);
    }

    /**
     * 给活动添加课程
     */
    @Path("/addActivityCourse")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean addActivityCourse(@HeaderParam("token")String token, @HeaderParam("openId")String openId,
                                        @QueryParam("activityId") int activityId,@QueryParam("courseIds")String courseIds){

        if (!TokenUtil.verifyToken(token,openId)){
            return ResultBean.getFailResult(SystemStatus.TOKEN_TIME_OUT);
        }
        promotionService = new PromotionServiceImpl();
        return promotionService.addActivityCourse(activityId,courseIds);
    }

    /**
     * 设置活动状态
     */
    @Path("/operateActivityState")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean setActivityState(@HeaderParam("token")String token, @HeaderParam("openId")String openId,
                                        @QueryParam("activityId") int activityId,@QueryParam("state")String state){

        if (!TokenUtil.verifyToken(token,openId)){
            return ResultBean.getFailResult(SystemStatus.TOKEN_TIME_OUT);
        }
        promotionService = new PromotionServiceImpl();
        return promotionService.setActivityState(activityId,state);
    }

    /**
     * 工作室活动列表
     */
    @Path("/activities")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean activities(@HeaderParam("token")String token, @HeaderParam("openId")String openId,
                                 @QueryParam("pageNo") int pageNo,@QueryParam("pageSize")int pageSize,
                                 @QueryParam("state")int state){

        if (!TokenUtil.verifyToken(token,openId)){
            return ResultBean.getFailResult(SystemStatus.TOKEN_TIME_OUT);
        }
        promotionService = new PromotionServiceImpl();
        return promotionService.activities(openId,pageNo,pageSize,state);
    }

    /**
     * 活动课程列表
     *@param activeStatus 不传表示查全部，1表示已参加该活动，0表示未参加该活动
     */
    @Path("/activityWareList")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean activityWareList(@HeaderParam("token")String token, @HeaderParam("openId")String openId,
                                       @QueryParam("pageNo") int pageNo,@QueryParam("pageSize")int pageSize,
                                       @QueryParam("activityId") int activityId,@QueryParam("activeStatus") Integer activeStatus){

        if (!TokenUtil.verifyToken(token,openId)){
            return ResultBean.getFailResult(SystemStatus.TOKEN_TIME_OUT);
        }
        promotionService = new PromotionServiceImpl();
        return promotionService.activityWareList(openId,pageNo,pageSize,activityId,activeStatus);
    }


    /**
     * 首页抢红包
     */
    @Path("/grabRedEnvelope")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean grabRedEnvelope(@HeaderParam("token")String token, @HeaderParam("openId")String openId){
        if (!TokenUtil.verifyToken(token,openId)){
            return ResultBean.getFailResult(SystemStatus.TOKEN_TIME_OUT);
        }
        promotionService = new PromotionServiceImpl();
        return promotionService.grabRedEnvelope(openId);
    }

}
