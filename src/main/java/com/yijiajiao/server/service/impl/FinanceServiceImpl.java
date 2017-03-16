package com.yijiajiao.server.service.impl;

import com.yijiajiao.server.bean.IOSMoneyBean;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.post.BindAliPayBean;
import com.yijiajiao.server.service.BaseService;
import com.yijiajiao.server.service.FinanceService;
import com.yijiajiao.server.util.Config;
import com.yijiajiao.server.util.ServerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-04-10:06
 */

@Service("financeService")
public class FinanceServiceImpl extends BaseService implements FinanceService{

    private static final Logger log = LoggerFactory.getLogger(FinanceServiceImpl.class);

    @Override
    public ResultBean getRemainAnswerTime(String studentId, String teacherId, String gradeCode) {
        String  path = Config.getString("finance.getRemainAnswerTime")+"openId="+studentId
                +"&teacherId="+teacherId+"&gradeCode="+gradeCode;
        String response = ServerUtil.httpRest(FINANCE_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean queryAlipayByOpenId(String openId) {
        String  path = Config.getString("finance.queryAlipayByOpenId")+"openId="+openId;
        String response = ServerUtil.httpRest(FINANCE_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean delAlipayByOpenId(String openId) {
        String  path = Config.getString("finance.delAlipayByOpenId")+"openId="+openId;
        String response = ServerUtil.httpRest(FINANCE_SERVER, path, null, null, "GET");
        return dealResult(log ,response);
    }

    @Override
    public ResultBean bindAliPay(BindAliPayBean bindAliPayBean) {
        String  path = Config.getString("finance.bindAliPay");
        String response = ServerUtil.httpRest(FINANCE_SERVER, path, null, bindAliPayBean, "POST");
        return dealResult(log ,response);
    }

    @Override
    public ResultBean remainIOSMoney(String openId) {
        String  path = Config.getString("finance.remainIOSMoney")+"openId="+openId;
        String response = ServerUtil.httpRest(FINANCE_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean addIOSMoney(IOSMoneyBean iosMoneyBean) {
        String  path = Config.getString("finance.addIOSMoney");
        String response = ServerUtil.httpRest(FINANCE_SERVER, path, null, iosMoneyBean, "POST");
        return dealResult(log,response);
    }

    @Override
    public ResultBean consumeIOSMoney(IOSMoneyBean iosMoneyBean) {
        String  path = Config.getString("finance.consumeIOSMoney");
        String response = ServerUtil.httpRest(FINANCE_SERVER, path, null, iosMoneyBean, "POST");
        ResultBean result = dealResult(log,response);
        if (result == null || result.getCode()!= 200){
            log.info("IOS内购账户扣款失败，删除当前订单！！");
            String s = Config.getString("sale.updateOrderStatus") + "orderNumber=" + iosMoneyBean.getTransactionId()
                    + "&status=" + 9 +"&payType="+4;
            ResultBean put = dealResult(log,ServerUtil.httpRest(SALE_SERVER, s, null, null, "PUT"));
            if (put == null || put.getCode()!=200){
                return put;
            }
        }
        return result;
    }

}
