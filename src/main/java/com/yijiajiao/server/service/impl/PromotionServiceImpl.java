package com.yijiajiao.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.promotion.PromotionWare;
import com.yijiajiao.server.bean.promotion.PromotionWareList;
import com.yijiajiao.server.bean.wares.WaresBean;
import com.yijiajiao.server.bean.wares.WaresListBean;
import com.yijiajiao.server.service.BaseService;
import com.yijiajiao.server.service.PromotionService;
import com.yijiajiao.server.util.Config;
import com.yijiajiao.server.util.ServerUtil;
import com.yijiajiao.server.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-05-14:40
 */
@Service("promotionService")
public class PromotionServiceImpl extends BaseService implements PromotionService {
    private static final Logger log = LoggerFactory.getLogger(PromotionService.class);

    public String getGradeByStage(int stageCode){
        String path = Config.getString("wares.getGradeByStage") + stageCode;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");

        log.info("response :\n"+response);
        return response;

    }

    public String waresListByType(String data) throws Exception {
        ResultBean r = JSON.parseObject(data, ResultBean.class);
        if (r.getCode() != 200 ){
            return data;
        }
        PromotionWareList promotionWareList = (PromotionWareList)r.getResult();
        for (PromotionWare pw : promotionWareList.getList()){
            String id = pw.getId();
        }
        r.setSucResult(promotionWareList);
        return JSON.toJSONString(r);

    }


    public String waresInfoById(String redata) {
        return null;
    }

    /**
     *  处理其他服务器返回结果
     */
    private ResultBean dealResult(String response){
        ResultBean resultBean = JSON.parseObject(response, ResultBean.class);
        if (resultBean.getCode() == 200) {
            log.info("正确信息： " + resultBean.getResult());
            return ResultBean.getSucResult(resultBean.getResult());
        } else {
            log.info("错误信息： " + resultBean.getMessage());
            return ResultBean.getFailResult(resultBean.getCode(), resultBean.getMessage());
        }
    }

    @Override
    public ResultBean operateCouponState(int couponId,String state) {
        String path = Config.getString("promotion.operateCouponState") +"couponId="+couponId+"&state="+state;
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, null, "GET");
        return dealResult(response);
    }

    @Override
    public ResultBean pageShowCoupon(int couponId,String display) {
        String path = Config.getString("promotion.pageShowCoupon") +"couponId="+couponId+"&display="+display;
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, null, "GET");
        return dealResult(response);
    }

    @Override
    public ResultBean sendCouponCode2Stu(int couponId, String stuOpenids) {
        String path = Config.getString("promotion.sendCouponCode2Stu") +"couponId="+couponId+"&stuOpenids="+stuOpenids;
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, null, "GET");
        return dealResult(response);
    }

    @Override
    public ResultBean acquireCouponCodeSelf(int couponId, String stuOpenid) {
        String path = Config.getString("promotion.acquireCouponCodeSelf") +"couponId="+couponId+"&stuOpenid="+stuOpenid;
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, null, "GET");
        return dealResult(response);
    }

    @Override
    public ResultBean couponInfo(int id) {
        String path = Config.getString("promotion.couponInfo") +"id="+id;
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, null, "GET");
        return dealResult(response);
    }

    @Override
    public ResultBean myCoupons4Teacher(String openId, int pageNo, int pageSize, int state, Double minValue, Double maxValue) {
        String path = Config.getString("promotion.myCoupons4Teacher") +"teacherOpenid="+openId+"&pageNo="+pageNo
                +"&pageSize="+pageSize+"&state="+state+(minValue==null?"":("&minValue="+minValue))
                +(maxValue==null?"":("&maxValue="+maxValue));
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, null, "GET");
        return dealResult(response);
    }

    @Override
    public ResultBean coupons(String teacherId) {
        String path = Config.getString("promotion.coupons") +"teacherOpenid="+teacherId;
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, null, "GET");
        return dealResult(response);
    }

    @Override
    public ResultBean couponCodePageForShop(int couponId, int pageNo, int pageSize,int type) {
        String path = Config.getString("promotion.couponCodePageForShop") +"couponId="+couponId+"&pageNo="+pageNo
                +"&pageSize="+pageSize+"&type="+type;
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, null, "GET");
        return dealResult(response);
    }

    @Override
    public ResultBean couponCodePageForStu(String openId, int pageNo, int pageSize, Integer state) {
        String path = Config.getString("promotion.couponCodePageForStu") +"userOpenid="+openId+"&pageNo="+pageNo
                +"&pageSize="+pageSize+(state==null?"":("&state="+state));
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, null, "GET");
        return dealResult(response);
    }

    @Override
    public ResultBean couponCodeListForStuOrder(String openId, String teacherId, String wareId) {
        String path = Config.getString("promotion.couponCodeListForStuOrder") +"userOpenid="+openId
                +(StringUtil.isEmpty(wareId)?"":("&wareId="+wareId))+(StringUtil.isEmpty(teacherId)?"":("&teacherOpenid="+teacherId));
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, null, "GET");
        return dealResult(response);
    }

    @Override
    public ResultBean activityInfoById(int activityId) {
        String path = Config.getString("promotion.activityInfoById") +"activityId="+activityId;
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, null, "GET");
        return dealResult(response);
    }

    @Override
    public ResultBean addActivityCourse(int activityId,String courseIds) {
        String path = Config.getString("promotion.addActivityCourse") +"activityId="+activityId+"&courseIds="+courseIds;
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, null, "GET");
        return dealResult(response);
    }

    @Override
    public ResultBean setActivityState(int activityId, String state) {
        String path = Config.getString("promotion.setActivityState") +"activityId="+activityId+"&state="+state;
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, null, "GET");
        return dealResult(response);
    }

    @Override
    public ResultBean activities(String openId, int pageNo, int pageSize,int state) {
        String path = Config.getString("promotion.activities") +"teacherOpenid="+openId+"&pageNo="+pageNo
                +"&pageSize="+pageSize+"&state="+state;
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, null, "GET");
        return dealResult(response);
    }

    @Override
    public ResultBean checkInputCouponCode(String openId, String couponCode, Integer wareId,String teacherId) {
        String path = Config.getString("promotion.checkInputCouponCode") +"stuOpenid="+openId+"&couponCode="+couponCode
                +(wareId==null?"":("&wareId="+wareId))+(StringUtil.isEmpty(teacherId)?"":("&teacherOpenid"+teacherId));
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, null, "GET");
        return dealResult(response);
    }

    @Override
    public ResultBean activityWareList(String openId, int pageNo, int pageSize, int activityId, Integer activeStatus) {
        //参加活动的课程
        if (activeStatus!=null && activeStatus==1 ){
            String path = Config.getString("promotion.waresListByCoupon")+"activityId="+activityId+"&pageNo="+pageNo
                    +"&pageSize="+pageSize;
            String get = ServerUtil.httpRest(PROMOTION_SERVER, path, null, null, "GET");
            return dealResult(get);
        }

        String wareIist = Config.getString("wares.wareslist") + "teacherId=" + openId;
        String wareListByCoupon = Config.getString("promotion.wareListByCoupon") + "activityId=" + activityId;

        //获取教师所有课程
        String response = ServerUtil.httpRest(WARES_SERVER, wareIist, null, null, "GET");
        ResultBean resultBean = JSON.parseObject(response, ResultBean.class);
        if (resultBean.getCode() != 200) {
            return ResultBean.getFailResult(resultBean.getCode(), resultBean.getMessage());
        }
        WaresListBean wares = JSON.parseObject(JSON.toJSONString(resultBean.getResult()), WaresListBean.class);
        if (wares.getList().size()==0) return ResultBean.getSucResult(wares);
        //获取参加活动的课程
        String ids = null;
        response = ServerUtil.httpRest(PROMOTION_SERVER, wareListByCoupon, null, null, "GET");
        resultBean = JSON.parseObject(response, ResultBean.class);
        ids = (String) resultBean.getResult();
        log.info("__[参加活动的课程ids:"+ids+"]");
        if (StringUtil.isEmpty(ids)){
            return ResultBean.getSucResult(wares);
        }
        List<String> strings = Arrays.asList(StringUtil.split(ids, '_'));
        log.info("__[strings.size:"+strings.size()+"]");
        for (WaresBean rwb : wares.getList()) {
            if (strings.contains(rwb.getId())) {
                rwb.setActiveStatus(1);
            }
        }
        return ResultBean.getSucResult(wares);

    }

    @Override
    public ResultBean grabRedEnvelope(String openId) {
        String path = Config.getString("promotion.grabRedEnvelope")+"openId="+openId;
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, null, "GET");
        return dealResult(response);
    }
}
