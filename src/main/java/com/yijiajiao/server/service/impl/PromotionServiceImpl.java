package com.yijiajiao.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.post.AddActivityBean;
import com.yijiajiao.server.bean.post.AddCouponBean;
import com.yijiajiao.server.bean.post.UpdateActivityBean;
import com.yijiajiao.server.bean.post.UpdateCouponBean;
import com.yijiajiao.server.bean.promotion.CommodityActivityBean;
import com.yijiajiao.server.bean.promotion.PromotionWare;
import com.yijiajiao.server.bean.wares.WaresBean;
import com.yijiajiao.server.bean.wares.WaresListBean;
import com.yijiajiao.server.service.PromotionService;
import com.yijiajiao.server.util.Config;
import com.yijiajiao.server.util.DateUtil;
import com.yijiajiao.server.util.ServerUtil;
import com.yijiajiao.server.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.yijiajiao.server.util.ServerUtil.*;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-05-14:40
 */
@Service("promotionService")
public class PromotionServiceImpl implements PromotionService {
    private static final Logger log = LoggerFactory.getLogger(PromotionServiceImpl.class);
    
    public String getGradeByStage(int stageCode){
        String path = Config.getString("wares.getGradeByStage") + stageCode;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");

        log.info("response :\n"+response);
        return response;

    }

    public String waresListByType(String data){
        ResultBean waresResult = JSON.parseObject(data, ResultBean.class);
        if (waresResult.getCode() != 200 ){
            return data;
        }
        JSONObject promotionWareList = JSON.parseObject(waresResult.getResult().toString());
        List<PromotionWare> list = JSON.parseArray(promotionWareList.get("list").toString(),PromotionWare.class) ;
        StringBuilder sids = new StringBuilder("");
        for (PromotionWare pw : list){
            sids.append("_"+pw.getId());
        }
        String ids = StringUtil.substring(sids.toString(),1);
        String path = Config.getString("promotion.getActivitiesByCommodityIds")+"courseIds="+ids;
        String response = ServerUtil.httpRest(PROMOTION_SERVER,path,null,null,"GET");
        ResultBean activityResult = JSON.parseObject(response,ResultBean.class);
        List<CommodityActivityBean> commodityActivities = JSON.parseArray(JSON.toJSONString(activityResult.getResult()),
                CommodityActivityBean.class);
        if (commodityActivities.size()>0){
            for (PromotionWare pw : list){
                for (CommodityActivityBean cab : commodityActivities){
                    if (!(pw.getId()+"").equals(cab.getCourseId())) continue;
                    pw.setActivityStatus(1);
                    pw.setActivityStartTime(cab.getStartDate());
                    pw.setActivityEndTime(cab.getEndDate());
                    pw.setDiscount(cab.getValue());
                }
            }
        }
        promotionWareList.put("list",list);
        return JSON.toJSONString(ResultBean.getSucResult(promotionWareList));

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
    public ResultBean activityWareList(String openId, int pageNo, int pageSize, int activityId, Integer activeStatus, String curriculumType) {
        //参加活动的课程
        if (activeStatus!=null && activeStatus==1 ){
            String path = Config.getString("promotion.waresListByCoupon")+"activityId="+activityId+"&pageNo=1"
                    +"&pageSize=100";
            String get = ServerUtil.httpRest(PROMOTION_SERVER, path, null, null, "GET");
            return dealResult(get);
        }

        //获取教师所有课程
        WaresListBean wares = getWareList(openId, curriculumType, null, null);

        //获取参加活动的课程
        String wareListByCoupon = Config.getString("promotion.wareListByCoupon") + "activityId=" + activityId;
        String response = ServerUtil.httpRest(PROMOTION_SERVER, wareListByCoupon, null, null, "GET");
        ResultBean resultBean = JSON.parseObject(response, ResultBean.class);
        String ids = (String) resultBean.getResult();
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

    @Override
    public ResultBean updateActivity(String tag, UpdateActivityBean updateActivityBean) {
        String path = Config.getString("promotion.updateActivity");
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, updateActivityBean, "POST");
        if (IF_MEM==1 && tag!=null) setMemcached(tag,response,log);
        return dealResult(response);
    }

    @Override
    public ResultBean addActivity(String tag, AddActivityBean activityBean) {
        String path = Config.getString("promotion.addActivity");
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, activityBean, "POST");
        if (IF_MEM==1 && tag!=null) setMemcached(tag,response,log);
        return dealResult(response);
    }

    @Override
    public ResultBean updateCoupon(String tag, UpdateCouponBean updateCouponBean) {
        String path = Config.getString("promotion.updateCoupon");
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, updateCouponBean, "POST");
        if (IF_MEM==1 && tag!=null) setMemcached(tag,response,log);
        return dealResult(response);
    }

    @Override
    public ResultBean addCoupon(String tag, AddCouponBean addCouponBean) {
        String path = Config.getString("promotion.addCoupon");
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, addCouponBean, "POST");
        if (IF_MEM==1 && tag!=null) setMemcached(tag,response,log);
        return dealResult(response);
    }

    @Override
    public ResultBean getActivitiesByCommodityId(String commodityId) {
        String path = Config.getString("promotion.getActivitiesByCommodityId")+"courseId="+commodityId;
        String response = httpRest(PROMOTION_SERVER,path,null,null,"GET");
        return dealResult(response);
    }

    @Override
    public WaresListBean getWareList(String teacherId, String curriculumType, Integer pageNo, Integer pageSize) {
        String wareIist = Config.getString("wares.wareslist") + "teacherId=" + teacherId
                + "&curriculumType=" + (StringUtil.isEmpty(curriculumType)?0:curriculumType)
                + "&pageNo="+(pageNo==null?1:pageNo) + "&pageSize="+(pageSize==null?100:pageSize);
        String response = ServerUtil.httpRest(WARES_SERVER, wareIist, null, null, "GET");
        ResultBean resultBean = JSON.parseObject(response, ResultBean.class);
        if (resultBean.getCode() != 200) {
            throw new RuntimeException(resultBean.getMessage());
        }
        WaresListBean wares = JSON.parseObject(JSON.toJSONString(resultBean.getResult()), WaresListBean.class);
        if (wares.getList().size()==0) return wares;
        List<WaresBean> resultWares = new ArrayList<>();
        for (WaresBean wb : wares.getList()){
            //过滤已经开课或者下架的课程
            if(StringUtil.isNotEmpty(wb.getStartTime())
                    && DateUtil.compareStringDate(wb.getStartTime(),DateUtil.getNowTime())==-1
                    && !"2".equals(wb.getStatus())){

                resultWares.add(wb);
            }
            else if (wb.getCurriculumType()==6 && !"2".equals(wb.getStatus())){
                resultWares.add(wb);
            }
        }
        wares.setList(resultWares);
        return wares;
    }

    @Override
    public ResultBean grantCoupon(String openId, Integer couponTriggerId) {
        String path = Config.getString("promotion.grantCoupon") + "openId=" + openId + "&couponTriggerId=" + couponTriggerId;
        String response = ServerUtil.httpRest(PROMOTION_SERVER, path, null, null, "GET");
        return dealResult(response);
    }
}
