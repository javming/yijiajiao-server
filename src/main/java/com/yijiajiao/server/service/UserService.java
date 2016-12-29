package com.yijiajiao.server.service;

import com.yijiajiao.server.bean.LoginBean;
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

    /**
     * 获取手机验证码
     */
    ResultBean getVerifyCode(String tel, int type);

    /**
     * 验证验证码是否正确
     */
    ResultBean verifyCode(String tel, int type, String telcode);

    /**
     * 用户登录
     */
    ResultBean login(LoginBean loginBean);

    /**
     * 查询教师列表
     */
    ResultBean findteacher(int pageNo, int pageSize, String gradeCode, String subjectCode, String orderType, String orders);
}
