package com.yijiajiao.server.service;

import com.yijiajiao.server.bean.*;
import com.yijiajiao.server.bean.user.UuidBean;

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

    /**
     * 获取邀请码邀请的好友列表
     */
    ResultBean findInviteUser(String openId, int pageNo, int pageSize);

    /**
     * 查询身份证是否存在
     */
    ResultBean findUserByIdCard(String idCard);

    /**
     * 根据openId获取面授审核记录详情
     */
    ResultBean findPermissionInfo(String openId, int type);

    /**
     * 收藏课程
     */
    ResultBean collect(String openId, String waresId, int type);

    /**
     * 查看是否收藏过该课程
     */
    ResultBean findcollect(String openId, String waresId);

    /**
     * 学习中心我的收藏
     */
    ResultBean findCollectById(String openId, int pageNo, int pageSize, Integer type);

    /**
     * 删除学习中心我的收藏
     */
    ResultBean delCollect(String openId, String ids);

    /**
     * 查询邀请验证码是否可用
     */
    ResultBean invitecode(String code);

    /**
     * 添加关注
     */
    ResultBean insertAttention(String studentId, String teacherId);

    /**
     * 取消关注
     */
    ResultBean cancelAttention(String studentId, String teacherId);

    /**
     * 查询我关注的老师
     */
    ResultBean findAttentionTeach(String openId, int pageNo, int pageSize);

    /**
     * 查询关注我的学生
     */
    ResultBean findAttentionStu(String teacherId, int pageNo, int pageSize);

    /**
     * 获得唯一uuid，生成二维码
     */
    ResultBean makeqrcode();

    /**
     * 扫码登录
     */
    ResultBean qrcodeanduser(UuidBean uuidBean);

    /**
     * 查询面试时间列表
     */
    ResultBean findRoomByTime(String day, String subjectCode);

    /**
     * 查询面授时间
     */
    ResultBean findFacingTeachByTime(String day, String subjectCode);

    /**
     *	查看面试详情
     */
    ResultBean findInterviewDetail(String userOpenId);

    /**
     *	查询学生是否关注过该教师
     */
    ResultBean findAttentionExist(String openId, String teacherId);

    /**
     *	签到
     */
    ResultBean signIn(String openId);

    /**
     *	查询积分明细
     */
    ResultBean findIntegralDetail(String openId, int pageNo, int pageSize);

    /**
     *	查询签到时间列表
     */
    ResultBean findSigninList(String openId, String startDay, String endDay);

    /**
     *	三级联动加载地区列表
     */
    ResultBean findLocation(String provinceCode, String cityCode);

    /**
     * 获取教师数量,学生数量,关注数量,收藏数量
     */
    ResultBean getMyCountUser(String openId);

    /**
     *	从教研系统中查询诊断卷列表
     */
    ResultBean findDiagnosislist(String subjectCode, String gradeCode, String bookType, String type, int pageNo, int pageSize, String orderType, String orders, String paperName);

    /**
     *	查询我的诊断列表
     */
    ResultBean getUserDiaglist(String openId, int pageNo, int pageSize);

    /**
     *	通过id查询诊断卷详情
     */
    ResultBean getDiagpaperByid(String paperId);

    /**
     *	获取诊断试卷基本信息
     */
    ResultBean getDiagnosisById(String paperId);

    /**
     *  查询诊断卷分析结果
     */
    ResultBean getDiagResult(String openId, String paperId);

    /**
     *	获取个人诊断卷详情(诊断完毕后)
     */
    ResultBean getDiagResultDetail(String openId, String paperId);

    /**
     *  查看购买诊断享受的折扣
     */
    ResultBean getDiscountById(String openId);

    /**
     *  一级代理下的二级代理订单数量 金额等信息
     */
    ResultBean getMyProxtInfoList(String openId, int pageNo, int pageSize, Integer year, Integer month);

    /**
     * 查询二级代理订单
     */
    ResultBean getSecondProxyInfoList(String proxyOpenId, String secondProxyOpenId, int pageNo, int pageSize, Integer year, Integer month);

    /**
     *  查询一级代理订单详情
     */
    ResultBean getMyOrderInfoList(String openId, int pageNo, int pageSize, Integer year, Integer month);

    /**
     *  查询一级代理下二级代理的订单量 订单金额,平台分成等信息
     */
    ResultBean getMyProxyInfo(String openId, Integer year, Integer month);

    /**
     *  查询二级代理下的订单量 订单金额,平台分成等信息
     */
    ResultBean getSecondProxyInfo(String proxyOpenId, String secondProxyOpenId, Integer year, Integer month);

    /**
     *  查询一级代理自己订单量，订单金额,平台分成等信息
     */
    ResultBean getMyOrderInfo(String openId, Integer year, Integer month);
}
