package com.yijiajiao.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.SystemStatus;
import com.yijiajiao.server.bean.post.CreateOrderBean;
import com.yijiajiao.server.bean.post.CreateRefundBean;
import com.yijiajiao.server.bean.post.UpdateAppraiseBean;
import com.yijiajiao.server.bean.post.UpdateAppraiseRebackBean;
import com.yijiajiao.server.bean.sale.*;
import com.yijiajiao.server.bean.solution.CountBean;
import com.yijiajiao.server.util.Config;
import com.yijiajiao.server.util.ServerUtil;
import com.yijiajiao.server.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.yijiajiao.server.util.ServerUtil.*;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-16-9:44
 */
@Service("saleService")
public class SaleServiceImpl implements com.yijiajiao.server.service.SaleService {

    private static final Logger log = LoggerFactory.getLogger(SaleServiceImpl.class);

    @Override
    public ResultBean getOrderSign(String openId, String orderNo, String payType, int payMent, String returnWapUrl) {
        String  path=Config.getString("sale.getsign")+"orderNumber="+orderNo+"&payType="+payType+"&payMent="+payMent
                +(StringUtil.isEmpty(returnWapUrl)?"":("&returnWapUrl="+returnWapUrl));
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getOrderList(String openId, int pageNo, int pageSize, String status, int curriculumType) {
        String  path = Config.getString("sale.getOrderList");
        OrderQueryBean ors =new  OrderQueryBean(pageNo,pageSize,status,openId,curriculumType);
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, ors, "POST");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getOrderById(String openId, int id) {
        String  path = Config.getString("sale.getOrderById")+id;
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getOrderByOrderNo(String openId, String orderNo) {
        String  path = Config.getString("sale.getOrderByOrderNo")+"orderNo="+orderNo;
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean delOrderById(String openId, int id, String status, String cancelReason, String canceldesc) {
        String  path = Config.getString("sale.delOrderById");
        CancelOrderBean cancelOrderBean = new CancelOrderBean (id,status,cancelReason,canceldesc);
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, cancelOrderBean, "PUT");
        return dealResult(log,response);
    }

    @Override
    public ResultBean stuTimeTable(String startDate, String endDate, String teacherId) {
        String path = Config.getString("sale.timeTable");
        SaleTimeQueryBean saleTimeBEan = new SaleTimeQueryBean(teacherId,startDate,endDate);
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, saleTimeBEan, "POST");
        return dealResult(log,response);
    }

    @Override
    public ResultBean stuTimeTableInfo(String date, String openId) {
        String path = Config.getString("sale.timeTableinfo");
        SaleTimeQueryOnlyBean saleTimeBean = new SaleTimeQueryOnlyBean(openId,date);
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, saleTimeBean, "POST");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getStuClassList(String openId, String curriculumType) {
        String path = Config.getString("sale.stuclasses")+"/"+openId+"/"+curriculumType;
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getAppraiseList(String curriculumType, String openId, String appraiseType, int pageNo, int pageSize) {
        String path = Config.getString("sale.appraiseStudentList");
        StuAppraiseQueryBean stuAppraiseQueryBean = new StuAppraiseQueryBean(openId,pageNo,pageSize,appraiseType,
                curriculumType+"");
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, stuAppraiseQueryBean, "POST");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getAppraiseTecaehrList(String curriculumType, String openId, String appraiseType, int pageNo, int pageSize) {
        String path = Config.getString("sale.appraiseTeacherList");
        TeaAppraiseQueryBean teaAppraiseQueryBean = new TeaAppraiseQueryBean(openId,pageNo,pageSize,appraiseType,
                curriculumType+"");
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, teaAppraiseQueryBean, "POST");
        return dealResult(log,response);
    }

    @Override
    public ResultBean AppraiseListByCurriculumId(int curriculumId, int pageNo, int pageSize) {
        String  path = Config.getString("sale.AppraiseListByCurriculumId");
        FindAppraiseListByCurriculumId findAppraiseListByCurriculumId= new FindAppraiseListByCurriculumId(curriculumId,
                pageNo,pageSize);
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, findAppraiseListByCurriculumId, "POST");
        return dealResult(log,response);
    }

    @Override
    public ResultBean isUsefulAppraise(String openId, int id) {
        String  path = Config.getString("sale.isUsefulAppraise");
        IsUsefulAppraiseBean isUsefulAppraiseBean = new IsUsefulAppraiseBean(openId,id);
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, isUsefulAppraiseBean, "POST");
        return dealResult(log,response);
    }

    @Override
    public ResultBean myStudents(String openId, int curriculumType, int pageNo, int pageSize) {
        String  path = Config.getString("sale.myStudents");
        MyStudentsBean myStudentsBean = new MyStudentsBean(openId,pageNo,pageSize,curriculumType);
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, myStudentsBean, "POST");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getVediosById(String openId, int id) {
        String  path = Config.getString("sale.getVediosByIdAndOpenId")+"id="+id+"&openId="+openId;
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getOrderList4Teacher(String openId, int commodityType, int orderStatus, int pageNo, int pageSize) {
        String  path = Config.getString("sale.getOrderList4Teacher")+"commodityType="+commodityType+"&openId="+openId
                +"&orderStatus="+orderStatus+"&pageNo="+pageNo+"&pageSize="+pageSize;
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean choseReturnOrder(String openId, String orderNumber, int refundType, String objection,
                                       int objectionCause) {
        String  path = Config.getString("sale.choseReturnOrder")+"orderNumber="+orderNumber+"&refundType="+refundType
                +(refundType==6?("&objectionCause="+objectionCause+"&objection="+objection):"");
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, null, "PUT");
        return dealResult(log,response);
    }

    @Override
    public ResultBean delOrder4Teacher(String openId, int id) {
        String  path = Config.getString("sale.delOrder4Teacher")+"id="+id;
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, null, "PUT");
        return dealResult(log,response);
    }

    @Override
    public ResultBean orderInfo4Teacher(String openId, int id) {
        String  path = Config.getString("sale.orderInfo4Teacher")+"id="+id;
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean solutionWaresList() {
        String  path = Config.getString("sale.solutionWaresList");
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean studentWaresList(String openId, int curriculumType, int pageNo, int pageSize) {
        String  path = Config.getString("sale.studentWaresList")+"openId="+openId+""+"&curriculumType="+curriculumType
                +"&pageNo="+pageNo+"&pageSize="+pageSize;
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean myStu4List(String openId, int curriculumType, int pageNo, int pageSize) {
        String  path = Config.getString("sale.myStu4List")+"openId="+openId+""+"&curriculumType="+curriculumType
                +"&pageNo="+pageNo+"&pageSize="+pageSize;
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean tradingList(String openId, int pageNo, int pageSize, int type) {
        String  path = Config.getString("sale.tradingList")+"openId="+openId+"&pageNo="+pageNo+"&pageSize="+pageSize
                            +"&openType="+type;
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean countAccount(String openId) {
        String  path = Config.getString("sale.countAccount")+"openId="+openId;
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean curriculumTypeCount(String openId) {
        String  path = Config.getString("sale.curriculumTypeCount")+"openId="+openId;
        String  path2 = Config.getString("solution.getStatistics")+openId;
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, null, "GET");
        ResultBean r =  JSON.parseObject(response,ResultBean.class);
        if(r.getCode()==200){
            log.info("正确信息： "+r.getResult());
            CurriculumTypeCountBean  curriculumTypeCountBean = JSON.parseObject(JSON.toJSONString(r.getResult()), CurriculumTypeCountBean.class);
            String response2 = ServerUtil.httpRest(SOLUTION_SERVER, path2, null, null, "GET");
            ResultBean r2 = JSON.parseObject(response2,ResultBean.class);
            if(r2.getCode()==200){
                CountBean countBean = JSON.parseObject(JSON.toJSONString(r2.getResult()), CountBean.class);
                curriculumTypeCountBean.setAnswer(countBean.getCount());
                curriculumTypeCountBean.setAnswerPrice(countBean.getTotal());
            }
            return ResultBean.getSucResult(r.getResult());
        }else{
            log.info("错误信息： " + r.getMessage());
            return ResultBean.getFailResult(Integer.parseInt(r.getCode()+""),r.getMessage());
        }

    }

    @Override
    public ResultBean teacherWork(String openId, int commodityId, int slaveId, String homeWorkCode) {
        String  path = Config.getString("sale.teacherWork")+"openId="+openId+"&commodityId="+commodityId
                +"&slaveId="+slaveId+ (StringUtil.isEmpty(homeWorkCode)?"":("&homeWorkCode="+homeWorkCode));
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, null, "PUT");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getStudent(String commodityId, String teacherId, int pageNo, int pageSize) {
        String  path = Config.getString("sale.getStudent")+"teacherId="+teacherId+"&commodityId="+commodityId
                +"&pageNo="+pageNo+"&pageSize="+pageSize;
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getMyAnswerList(String openId, int pageNo, int pageSize, int answerId) {
        String  path = Config.getString("sale.getMyAnswerList")+"openId="+openId+"&answerId="+answerId+
                "&pageNo="+pageNo+"&pageSize="+pageSize;
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getMyCount(String openId) {
        String  path = Config.getString("sale.getMyCount")+"openId="+openId;
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean syllabusPhone(String openId, String startTime) {
        String  path = Config.getString("sale.syllabusPhone")+"openId="+openId+"&startTime="+startTime;
        String response = ServerUtil.httpRest(SALE_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean checkTime(String openId, String startTime, String endTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long delayTime= Config.getInt("delayTime");
        long now = new Date().getTime();
        try{
            long change = simpleDateFormat.parse(URLDecoder.decode(startTime)).getTime()-delayTime;
            log.info("delayTime="+delayTime+",statTime="+simpleDateFormat.parse(startTime)+
                    ",now = "+new Date(now)+",change="+new Date(change));
            if((simpleDateFormat.parse(startTime).getTime()-delayTime)<=now){
                log.info("开始时间必须大于当前时间5分钟");
                return ResultBean.getSucResult(false);
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResultBean.getFailResult(SystemStatus.SERVER_ERROR);
        }

        String salePath = Config.getString("sale.checkTime");
        CheckTimeBean checkTimeBean = new CheckTimeBean(openId,startTime,endTime);
        String sale_response = ServerUtil.httpRest(SALE_SERVER, salePath, null, checkTimeBean, "POST");
        ResultBean result = JSON.parseObject(sale_response, ResultBean.class);
        ResultBean resultBean = new ResultBean();
        if (result.getCode() == 200) {
            log.info("正确信息： " + result.getResult());
            if ("true".equals(result.getResult().toString())) {
                resultBean.setSucResult(result.getResult());
            } else {
                resultBean.setSucResult(false);
            }
        } else {
            log.info("错误信息： " + result.getMessage());
            resultBean.setFailMsg(Integer.parseInt(result.getCode() + ""), result.getMessage());
        }
        return resultBean;
    }

    @Override
    public ResultBean myStudentsByCurriculumType(String openId, Integer curriculumType, int pageNo, int pageSize) {
        String path = Config.getString("sale.myStudentsByCurriculumType")+"teacherId="+openId+"&curriculumType="+curriculumType
                +"&pageNo="+pageNo+"&pageSize="+pageSize;
        String response = ServerUtil.httpRest(SALE_SERVER,path,null,null,"GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean updateAppraise(String tag, UpdateAppraiseBean updateAppraiseBean) {
        String path = Config.getString("sale.updateAppraise");
        String response = ServerUtil.httpRest(SALE_SERVER,path,null,updateAppraiseBean,"POST");
        if (IF_MEM==1 && tag!=null) setMemcached(tag,response,log);
        return dealResult(log,response);
    }

    @Override
    public ResultBean createOrder(String tag, CreateOrderBean createOrderBean) {
        String path = Config.getString("sale.createOrder");
        String response = ServerUtil.httpRest(SALE_SERVER,path,null,createOrderBean,"POST");
        if (IF_MEM==1 && tag!=null) setMemcached(tag,response,log);
        return dealResult(log,response);
    }

    @Override
    public ResultBean updateAppraiseReback(String tag, UpdateAppraiseRebackBean updateAppraiseRebackBean) {
        String path = Config.getString("sale.updateAppraiseReback");
        String response = ServerUtil.httpRest(SALE_SERVER,path,null,updateAppraiseRebackBean,"PUT");
        if (IF_MEM==1 && tag!=null) setMemcached(tag,response,log);
        return dealResult(log,response);
    }

    @Override
    public ResultBean createRefund(String tag, CreateRefundBean createRefundBean) {
        String path = Config.getString("sale.createRefund");
        String response = ServerUtil.httpRest(SALE_SERVER,path,null,createRefundBean,"POST");
        if (IF_MEM==1 && tag!=null) setMemcached(tag,response,log);
        return dealResult(log,response);
    }
}
