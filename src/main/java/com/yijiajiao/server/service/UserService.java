package com.yijiajiao.server.service;

import com.yijiajiao.server.bean.*;

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

    /**
     * 根据openId查询用户信息
     */
    ResultBean findUserInfo(String openId);

    /**
     * 查询教师各种认证权限
     */
    ResultBean getPermissionInfo(String openId);

    /**
     * 获取答疑认证题
     */
    ResultBean applySolutionPermission(String subjectCode, String stageCode);

    /**
     * 查询用户申请成为教师的状态
     */
    ResultBean applyStatusBean(String openId);

    /**
     * 修改密码
     */
    ResultBean updatePass(String token, String openId, UpdatePasswordBean updatePassBean);
}
