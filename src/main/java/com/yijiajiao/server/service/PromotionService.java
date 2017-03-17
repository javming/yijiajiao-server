package com.yijiajiao.server.service;

import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.post.AddActivityBean;
import com.yijiajiao.server.bean.post.AddCouponBean;
import com.yijiajiao.server.bean.post.UpdateActivityBean;
import com.yijiajiao.server.bean.post.UpdateCouponBean;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-12-22-16:56
 */
public interface PromotionService {

    String getGradeByStage(int stageCode);
    String waresListByType(String data) throws Exception;
    String waresInfoById(String redata);

    /**
     * 设置优惠券状态（上线/下线）
     */
    ResultBean operateCouponState(int couponId, String state);

    /**
     * 设置优惠券主页展示
     */
    ResultBean pageShowCoupon(int couponId, String display);

    /**
     * 老师发送优惠码给学生
     */
    ResultBean sendCouponCode2Stu(int couponId, String stuOpenids);

    /**
     * 学生工作室主页领取优惠码
     */
    ResultBean acquireCouponCodeSelf(int couponId, String stuOpenid);

    /**
     * 获取优惠券信息
     */
    ResultBean couponInfo(int id);

    /**
     * 我的工作室优惠券列表
     */
    ResultBean myCoupons4Teacher(String openId, int pageNo, int pageSize, int state, Double minValue, Double maxValue);

    /**
     * 工作室主页优惠券列表
     */
    ResultBean coupons(String teacherId);

    /**
     * 我的工作室查看优惠券领取和使用信息
     */
    ResultBean couponCodePageForShop(int couponId, int pageNo, int pageSize, int type);

    /**
     * 我的学习中心查看优惠券列表
     */
    ResultBean couponCodePageForStu(String openId, int pageNo, int pageSize, Integer state);

    /**
     * 我的学习中心查看优惠券列表
     */
    ResultBean couponCodeListForStuOrder(String openId, String teacherId, String wareId);

    /**
     * 查看活动信息
     */
    ResultBean activityInfoById(int activityId);

    /**
     * 给活动添加课程
     */
    ResultBean addActivityCourse(int activityId, String courseIds);

    /**
     * 设置活动状态
     */
    ResultBean setActivityState(int activityId, String state);

    /**
     * 我的工作室活动列表
     */
    ResultBean activities(String openId, int pageNo, int pageSize, int state);

    /**
     * 检验输入优惠码是否可用
     */
    ResultBean checkInputCouponCode(String openId, String couponCode, Integer wareId, String teacherId);

    /**
     * 活动课程列表
     */
    ResultBean activityWareList(String openId, int pageNo, int pageSize, int activityId, Integer activeStatus);

    /**
     * 首页抢红包
     */
    ResultBean grabRedEnvelope(String openId);

    ResultBean updateActivity(String tag, UpdateActivityBean updateActivityBean);

    ResultBean addActivity(String tag, AddActivityBean activityBean);

    ResultBean updateCoupon(String tag, UpdateCouponBean updateCouponBean);

    ResultBean addCoupon(String tag, AddCouponBean addCouponBean);
}
