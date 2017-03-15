package com.yijiajiao.server.service.impl;

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
        String  path = Config.getString("bindAliPay");
        String response = ServerUtil.httpRest(FINANCE_SERVER, path, null, bindAliPayBean, "POST");
        return dealResult(log ,response);
    }

}
