package com.yijiajiao.server.service;

import com.yijiajiao.server.bean.PlanUserBean;
import com.yijiajiao.server.bean.RegisterBean;
import com.yijiajiao.server.bean.ResultBean;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-12-28-15:00
 */
public interface UserService {

    /**
     * 验证手机号唯一性
     */
    ResultBean validateTel(String tel);

    /**
     * 用户注册
     */
    ResultBean register(RegisterBean registerBean);

    /**
     * 保分计划注册用户
     */
    ResultBean getPlanRegister(PlanUserBean planUserBean);
}
