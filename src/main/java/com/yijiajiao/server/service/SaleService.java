package com.yijiajiao.server.service;

import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.post.CreateOrderBean;
import com.yijiajiao.server.bean.post.CreateRefundBean;
import com.yijiajiao.server.bean.post.UpdateAppraiseBean;
import com.yijiajiao.server.bean.post.UpdateAppraiseRebackBean;


public interface SaleService {

    ResultBean getOrderSign(String openId, String orderNo, String payType, int payMent, String returnWapUrl);

    ResultBean getOrderList(String openId, int pageNo, int pageSize, String status, int curriculumType);

    ResultBean getOrderById(String openId, int id);

    ResultBean getOrderByOrderNo(String openId, String orderNo);

    ResultBean delOrderById(String openId, int id, String status, String cancelReason, String canceldesc);

    ResultBean stuTimeTable(String startDate, String endDate, String openId);

    ResultBean stuTimeTableInfo(String date, String openId);

    ResultBean getStuClassList(String openId, String curriculumType);

    ResultBean getAppraiseList(String curriculumType, String openId, String appraiseType, int pageNo, int pageSize);

    ResultBean getAppraiseTecaehrList(String curriculumType, String openId, String appraiseType, int pageNo, int pageSize);

    ResultBean AppraiseListByCurriculumId(int curriculumId, int pageNo, int pageSize);

    ResultBean isUsefulAppraise(String openId, int id);

    ResultBean myStudents(String openId, int curriculumType, int pageNo, int pageSize);

    ResultBean getVediosById(String openId, int id);

    ResultBean getOrderList4Teacher(String openId, int commodityType, int orderStatus, int pageNo, int pageSize);

    ResultBean choseReturnOrder(String openId, String orderNumber, int refundType, String objection, int objectionCause);

    ResultBean delOrder4Teacher(String openId, int id);

    ResultBean orderInfo4Teacher(String openId, int id);

    ResultBean solutionWaresList();

    ResultBean studentWaresList(String openId, int curriculumType, int pageNo, int pageSize);

    ResultBean myStu4List(String openId, int curriculumType, int pageNo, int pageSize);

    ResultBean tradingList(String openId, int pageNo, int pageSize, int type);

    ResultBean countAccount(String openId);

    ResultBean curriculumTypeCount(String openId);

    ResultBean teacherWork(String openId, int commodityId, int slaveId, String homeWorkCode);

    ResultBean getStudent(String commodityId, String teacherId, int pageNo, int pageSize);

    ResultBean getMyAnswerList(String openId, int pageNo, int pageSize, int answerId);

    ResultBean getMyCount(String openId);

    ResultBean syllabusPhone(String openId, String startTime);

    ResultBean checkTime(String openId, String startTime, String endTime);

    ResultBean myStudentsByCurriculumType(String openId, Integer curriculumType, int pageNo, int pageSize);

    ResultBean updateAppraise(String tag, UpdateAppraiseBean updateAppraiseBean);

    ResultBean createOrder(String tag, CreateOrderBean createOrderBean);

    ResultBean updateAppraiseReback(String tag, UpdateAppraiseRebackBean updateAppraiseRebackBean);

    ResultBean createRefund(String tag, CreateRefundBean createRefundBean);
}
