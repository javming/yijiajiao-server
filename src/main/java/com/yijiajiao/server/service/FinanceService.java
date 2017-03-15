package com.yijiajiao.server.service;

import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.post.BindAliPayBean;

public interface FinanceService {

    /**
     * 获取答疑解答时长
     */
    ResultBean getRemainAnswerTime(String studentId, String teacherId, String gradeCode);

    /**
     * 通过openId查询绑定的支付宝账号
     */
    ResultBean queryAlipayByOpenId(String openId);

    /**
     * 账户解绑支付宝账号
     */
    ResultBean delAlipayByOpenId(String openId);

    ResultBean bindAliPay(BindAliPayBean bindAliPayBean);
}
