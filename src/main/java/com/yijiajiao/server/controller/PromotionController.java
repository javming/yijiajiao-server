package com.yijiajiao.server.controller;

import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.post.AddActivityBean;
import com.yijiajiao.server.bean.post.AddCouponBean;
import com.yijiajiao.server.bean.post.UpdateActivityBean;
import com.yijiajiao.server.bean.post.UpdateCouponBean;
import com.yijiajiao.server.bean.wares.WaresListBean;
import com.yijiajiao.server.service.PromotionService;
import com.yijiajiao.server.service.impl.PromotionServiceImpl;
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
    public ResultBean operateCouponState(@HeaderParam("openId")String openId, @QueryParam("couponId") int couponId,
                                         @QueryParam("state")String state){

        return promotionService.operateCouponState(couponId,state);

    }

    /**
     * 设置优惠券主页展示
     */
    @Path("/pageShowCoupon")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean pageShowCoupon(@HeaderParam("openId")String openId, @QueryParam("couponId") int couponId,
                                     @QueryParam("display") String display){

        return promotionService.pageShowCoupon(couponId,display);

    }


    /**
     * 老师发送优惠券给学生
     */
    @Path("/sendCouponCode2Stu")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean sendCouponCode2Stu(@HeaderParam("openId")String openId, @QueryParam("couponId")int couponId,
                                         @QueryParam("stuOpenids")String stuOpenids){

        return promotionService.sendCouponCode2Stu(couponId,stuOpenids);

    }

    /**
     * 学生工作室主页领取优惠券
     */
    @Path("/acquireCouponCodeSelf")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean acquireCouponCodeSelf(@HeaderParam("openId")String openId, @QueryParam("couponId")int couponId){

        return promotionService.acquireCouponCodeSelf(couponId,openId);

    }

    /**
     * 检验输入优惠码是否可用
     */
    @Path("/checkInputCouponCode")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean checkInputCouponCode(@HeaderParam("openId")String openId, @QueryParam("couponCode")String couponCode,
                                           @QueryParam("wareId")Integer wareId, @QueryParam("teacherId")String teacherId){

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
    public ResultBean myCoupons4Teacher(@HeaderParam("openId")String openId, @QueryParam("pageNo") int pageNo,
                                        @QueryParam("pageSize")int pageSize,@QueryParam("state")int state,
                                 @QueryParam("minValue")Double minValue,@QueryParam("maxValue")Double maxValue){

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
    public ResultBean couponCodePageForShop(@HeaderParam("openId")String openId,
                                        @QueryParam("pageNo") int pageNo,@QueryParam("pageSize")int pageSize,
                                        @QueryParam("couponId") int couponId,@QueryParam("type") int type){

        return promotionService.couponCodePageForShop(couponId,pageNo,pageSize,type);
    }

    /**
     * 我的学习中心查看优惠券列表
     */
    @Path("/couponCodePageForStu")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean couponCodePageForStu(@HeaderParam("openId")String openId,
                                        @QueryParam("pageNo") int pageNo,@QueryParam("pageSize")int pageSize,
                                        @QueryParam("state") Integer state){

        return promotionService.couponCodePageForStu(openId,pageNo,pageSize,state);
    }

    /**
     * 可用的优惠券列表
     */
    @Path("/couponCodeListForStuOrder")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean couponCodeListForStuOrder(@HeaderParam("openId")String openId,
                                        @QueryParam("teacherId") String teacherId,@QueryParam("wareId") String wareId){

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
    public ResultBean addActivityCourse(@HeaderParam("openId")String openId,
                                        @QueryParam("activityId") int activityId,@QueryParam("courseIds")String courseIds){

        return promotionService.addActivityCourse(activityId,courseIds);
    }

    /**
     * 设置活动状态
     */
    @Path("/operateActivityState")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean setActivityState( @HeaderParam("openId")String openId,
                                        @QueryParam("activityId") int activityId,@QueryParam("state")String state){

        return promotionService.setActivityState(activityId,state);
    }

    /**
     * 工作室活动列表
     */
    @Path("/activities")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean activities(@HeaderParam("openId")String openId,
                                 @QueryParam("pageNo") int pageNo,@QueryParam("pageSize")int pageSize,
                                 @QueryParam("state")int state){

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
    public ResultBean activityWareList( @HeaderParam("openId")String openId,
                                       @QueryParam("pageNo") int pageNo,@QueryParam("pageSize")int pageSize,
                                       @QueryParam("activityId") int activityId,@QueryParam("activeStatus") Integer activeStatus){

        return promotionService.activityWareList(openId,pageNo,pageSize,activityId,activeStatus);
    }


    /**
     * 首页抢红包
     */
    @Path("/grabRedEnvelope")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean grabRedEnvelope(@HeaderParam("openId")String openId){

        return promotionService.grabRedEnvelope(openId);
    }

    /**
     * 根据商品id查看所参加的活动
     */
    @Path("/activitiesByCommodityId")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean activitiesByCommodityId(@QueryParam("commodityId") String commodityId){

        return promotionService.getActivitiesByCommodityId(commodityId);
    }

    /**
     * 添加优惠券
     */
    @POST
    @Path("/addCoupon")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean addCoupon(@HeaderParam("tag")String tag,AddCouponBean addCouponBean){

        return promotionService.addCoupon(tag,addCouponBean);
    }

    /**
     * 修改优惠券信息
     */
    @POST
    @Path("/updateCoupon")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean updateCoupon(@HeaderParam("tag")String tag,UpdateCouponBean updateCouponBean){

        return promotionService.updateCoupon(tag,updateCouponBean);
    }

    /**
     * 创建活动
     */
    @POST
    @Path("/addActivity")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean addActivity(@HeaderParam("tag")String tag,AddActivityBean activityBean){

        return promotionService.addActivity(tag,activityBean);
    }

    /**
     * 修改活动信息
     */
    @POST
    @Path("/updateActivity")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean updateActivity(@HeaderParam("tag")String tag,UpdateActivityBean updateActivityBean){

        return promotionService.updateActivity(tag,updateActivityBean);
    }

    /**
     * 获取教师课程列表（用于添加优惠券）
     */
    @GET
    @Path("wareListForCoupon")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean getWareListForCoupon(@QueryParam("teacherId") String teacherId,@QueryParam("pageNo") Integer pageNo,
                                           @QueryParam("pageSize") Integer pageSize,@QueryParam("curriculumType") String curriculumType){

        WaresListBean waresListBean = promotionService.getWareList(teacherId, "0", null, null);
        return ResultBean.getSucResult(waresListBean);
    }


    /**
     * 赠送优惠券
     *
     * @param couponTriggerId 类型
     */
    @GET
    @Path("/grantCoupon")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean grantCoupon2User( @QueryParam("couponTriggerId") Integer couponTriggerId,
                                        @QueryParam("openId") String openId){

        return promotionService.grantCoupon(openId, couponTriggerId);

    }

}
