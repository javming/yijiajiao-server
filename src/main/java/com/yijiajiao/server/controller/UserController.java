package com.yijiajiao.server.controller;

import com.yijiajiao.server.bean.*;
import com.yijiajiao.server.bean.post.*;
import com.yijiajiao.server.bean.user.UuidBean;
import com.yijiajiao.server.service.UserService;
import com.yijiajiao.server.service.impl.UserServiceImpl;
import com.yijiajiao.server.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 验证手机号唯一性
     */
    @GET
    @Path("/verifyphone")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean validateTel(@QueryParam("tel") String tel){

        return userService.validateTel(tel);

    }

    /**
     * 用户注册
     */
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean register(RegisterBean registerBean){

        return userService.register(registerBean);

    }

    /**
     * 保分计划注册用户
     */
    @POST
    @Path("/planRegister")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean planRegister(PlanUserBean planUserBean) {

        return userService.getPlanRegister(planUserBean);

    }

    /**
     * 获取手机验证码
     * @param type：1注册 2修改密码 3老师资格认证 4找回密码 5保分计划用户注册 6绑定支付宝
     */
    @GET
    @Path("/get/verifycode")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getVerifyCode(@QueryParam("tel") String tel,@QueryParam("type") int type) {

        return userService.getVerifyCode(tel,type);

    }

    /**
     *	验证验证码是否正确
     */
    @GET
    @Path("/verifycode")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean verifyCode(@QueryParam("tel")String tel,@QueryParam("type") int type,@QueryParam("telcode")String telcode){

        return userService.verifyCode(tel,type,telcode);

    }

    /**
     * 用户登录
     */
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean login(LoginBean loginBean) {
        ResultBean result = new ResultBean();
        if(StringUtil.isEmpty(loginBean.getTelephone())){
            result.setFailMsg(SystemStatus.USERNAME_IS_NULL);
            return result;
        }
        if(StringUtil.isEmpty(loginBean.getPassword())){
            result.setFailMsg(SystemStatus.PASSWORD_IS_NULL);
            return result;
        }
        return userService.login(loginBean);

    }

    /**
     * 查询教师列表
     * @param orderType 排序条件 好评storeScore   人气popularity
     * @param orders 升序降序
     * @return
     */
    @GET
    @Path("/findteacher")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean findteacher(@QueryParam("pageNo") int pageNo, @QueryParam("pageSize") int pageSize,
                                  @QueryParam("gradeCode") String gradeCode, @QueryParam("subjectCode") String subjectCode,
                                  @QueryParam("orderType") String orderType,@QueryParam("orders") String orders) {
        if(StringUtil.isEmpty(orders)){
            orders="desc";
        }
        return userService.findteacher(pageNo, pageSize, gradeCode, subjectCode, orderType,orders);
    }

    /**
     * 查询教师店铺信息
     * @param teacherId
     * @return
     */
    @GET
    @Path("/findteacherstore")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean findteacherStore(@QueryParam("teacherId") String teacherId) {

         return userService.findteacherStore(teacherId);

    }

    /**
     * 查询用户个人信息
     */
    @GET
    @Path("/finduserinfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean findUserInfo(@QueryParam("openId") String openId) {

        return userService.findUserInfo(openId);

    }

    /**
     * 查询教师各种认证权限
     */
    @GET
    @Path("/findteacherpermission")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean GetPermissionInfo(@HeaderParam("openId") String openId) {

        return userService.getPermissionInfo(openId);

    }

    /**
     * 获取答疑认证题
     */
    @GET
    @Path("/applysolutionpermission")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean applySolutionPermission(@QueryParam("subjectCode") String subjectCode, @QueryParam("stageCode") String stageCode) {

        return userService.applySolutionPermission(subjectCode, stageCode);

    }

    /**
     * 查询用户申请成为教师的状态
     */
    @GET
    @Path("/applyStatusBean")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean applyStatusBean(@HeaderParam("openId") String openId) {

        return userService.applyStatusBean(openId);

    }

    /**
     * 修改密码
     */
    @POST
    @Path("/updatePass")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean updatePass(@HeaderParam("token") String token, @HeaderParam("openId") String openId,UpdatePasswordBean updatePassBean) {

        return userService.updatePass(token,openId,updatePassBean);

    }

    /**
     * 获取邀请码邀请的好友列表
     */
    @GET
    @Path("/findinviteuser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean findInviteUser(@HeaderParam("openId") String openId,@QueryParam("pageNo") int pageNo,@QueryParam("pageSize") int pageSize) {

        return userService.findInviteUser(openId,pageNo,pageSize);

    }

    /**
     * 验证身份证是否存在
     */
    @GET
    @Path("/findUserByIdCard")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean findUserByIdCard(@QueryParam("idCard") String idCard) {

        if (StringUtil.isEmpty(idCard)){
            return ResultBean.getFailResult(SystemStatus.PARAM_IS_NULL);
        }

        return userService.findUserByIdCard(idCard);

    }

    /**
     * 根据openId获取面授审核记录详情
     */
    @GET
    @Path("/findPermissionInfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean findPermissionInfo(@HeaderParam("openId") String openId,@QueryParam("type") int type) {

        return userService.findPermissionInfo(openId,type);

    }

    /**
     * 收藏课程
     */
    @GET
    @Path("/collect")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean collect(@HeaderParam("openId") String openId,@QueryParam("waresId") String waresId,@QueryParam("type") int type) {

        return userService.collect(openId,waresId,type);

    }

    /**
     * 课程是否被收藏
     * @return
     */
    @GET
    @Path("/findcollect")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean findcollect(@HeaderParam("openId") String openId,@QueryParam("waresId") String waresId) {

        return userService.findcollect(openId,waresId);

    }

    /**
     * 学习中心我的收藏
     */
    @GET
    @Path("/findCollectById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean findCollectById(@HeaderParam("openId") String openId,@QueryParam("pageNo") int  pageNo,@QueryParam("pageSize") int  pageSize,@QueryParam("type") Integer  type) {

        return userService.findCollectById(openId,pageNo,pageSize,type);

    }

    /**
     * 删除学习中心我的收藏
     */
    @GET
    @Path("/delcollect")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean delCollect(@HeaderParam("openId") String openId,@QueryParam("ids") String  ids) {

        return userService.delCollect(openId,ids);

    }

    /**
     * 查询邀请验证码是否可用
     * @return
     */
    @GET
    @Path("/invitecode")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean invitecode(@QueryParam("code") String  code) {

        return userService.invitecode(code);

    }

    /**
     * 添加关注
     */
    @GET
    @Path("/insertAttention")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean insertattention(@QueryParam("studentId") String studentId,@QueryParam("teacherId") String teacherId){

        return userService.insertAttention(studentId,teacherId);

    }

    /**
     * 取消关注
     */
    @GET
    @Path("/cancelAttention")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean cancelAttention(@QueryParam("studentId") String studentId,@QueryParam("teacherId") String teacherId){

        return userService.cancelAttention(studentId,teacherId);

    }

    /**
     *  查询我关注的老师
     */
    @GET
    @Path("/findAttentionTeach")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean findAttentionTeach(@HeaderParam("openId") String openId,@QueryParam("pageNo") int pageNo,
                                         @QueryParam("pageSize") int pageSize){

        return userService.findAttentionTeach(openId,pageNo,pageSize);

    }
    /**
     *  查询关注我的学生
     */
    @GET
    @Path("/findAttentionStu")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean findAttentionStu(@QueryParam("teacherId") String teacherId,@QueryParam("pageNo") int pageNo,@QueryParam("pageSize") int pageSize){

        return userService.findAttentionStu(teacherId,pageNo,pageSize);

    }

    /**
     *  获得唯一uuid，生成二维码
     */
    @GET
    @Path("/makeqrcode")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean makeqrcode(){

        return userService.makeqrcode();

    }

    /**
     *	扫码登录
     */
    @POST
    @Path("/qrcodeanduser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean qrcodeanduser(UuidBean uuidBean){

        return userService.qrcodeanduser(uuidBean);

    }

    /**
     *	查询面试时间列表
     */
    @GET
    @Path("/findroombytime")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean findRoomByTime(@QueryParam("day") String day,@QueryParam("subjectCode")String subjectCode){

        return userService.findRoomByTime(day,subjectCode);

    }

    /**
     * 查询面授时间
     */
    @GET
    @Path("/findfacingteachbytime")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean findFacingTeachByTime(@HeaderParam("token") String token, @HeaderParam("openId") String openId,@QueryParam("day") String day,@QueryParam("subjectCode")String subjectCode){

        return  userService.findFacingTeachByTime(day,subjectCode);

    }

    /**
     *	查看面试详情
     */
    @GET
    @Path("/findInterviewDetail")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean findInterviewDetail(@QueryParam("openId") String userOpenId){

        return userService.findInterviewDetail(userOpenId);

    }

    /**
     *	查询学生是否关注过该教师
     */
    @GET
    @Path("/findattentionexist")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean findAttentionExist(@HeaderParam("openId") String openId,@QueryParam("teacherId") String teacherId){

        return userService.findAttentionExist(openId,teacherId);

    }

    /**
     *	签到
     */
    @GET
    @Path("/signin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean signIn(@HeaderParam("openId") String openId){

        return userService.signIn(openId);

    }

    /**
     *	查询积分明细
     */
    @GET
    @Path("/findIntegralDetail")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean findIntegralDetail(@HeaderParam("openId") String openId,@QueryParam("pageNo") int pageNo,@QueryParam("pageSize")int pageSize){

        return userService.findIntegralDetail(openId,pageNo,pageSize);

    }

    /**
     *	查询签到时间列表
     */
    @GET
    @Path("/findSigninList")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean findSigninList(@HeaderParam("openId") String openId,
                                     @QueryParam("startDay")String startDay,@QueryParam("endDay")String endDay){
        return userService.findSigninList(openId,startDay,endDay);

    }

    /**
     *	三级联动加载地区列表
     */
    @GET
    @Path("/findLocation")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean findLocation(@QueryParam("provinceCode")String provinceCode,@QueryParam("cityCode")String cityCode){

        return userService.findLocation(provinceCode,cityCode);

    }

    /**
     * 获取教师数量,学生数量,关注数量,收藏数量
     */
    @GET
    @Path("/getMyCountUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getMyCountUser(@HeaderParam("openId") String openId){

        return userService.getMyCountUser(openId);

    }

    /**
     *	从教研系统中查询诊断卷列表
     */
    @GET
    @Path("/findDiagnosislist")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean findDiagnosislist(@QueryParam("subjectCode")String subjectCode,@QueryParam("type")String type,
                                        @QueryParam("gradeCode")String gradeCode,@QueryParam("bookType") String bookType,
                                        @QueryParam("pageNo") int pageNo,@QueryParam("pageSize") int pageSize,
                                        @QueryParam("orderType") String orderType,@QueryParam("orders")String orders,
                                        @QueryParam("paperName") String paperName){

        return userService.findDiagnosislist(subjectCode,gradeCode,bookType,type,
                pageNo,pageSize,orderType,orders,paperName);

    }


    /**
     *	查询我的诊断列表
     */
    @GET
    @Path("/getUserDiaglist")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getUserDiaglist(@HeaderParam("openId") String openId,
                                      @QueryParam("pageNo")int pageNo,@QueryParam("pageSize") int pageSize){

        return userService.getUserDiaglist(openId,pageNo,pageSize);

    }

    /**
     *	通过id查询诊断卷详情
     */
    @GET
    @Path("/getDiagpaperByid")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getDiagpaperByid(@QueryParam("paperId") String paperId ) {

        return userService.getDiagpaperByid(paperId);

    }


    /**
     *	获取诊断试卷基本信息
     */
    @GET
    @Path("/getDiagnosisById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getDiagnosisById(@QueryParam("paperId")String paperId){

        return userService.getDiagnosisById(paperId);

    }

    /**
     *  查询诊断卷分析结果
     */
    @GET
    @Path("/getDiagResult")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getDiagResult( @HeaderParam("openId") String openId,
                                    @QueryParam("paperId")String paperId){

        return userService.getDiagResult(openId,paperId);

    }

    /**
     *	获取个人诊断卷详情(诊断完毕后)
     */
    @GET
    @Path("/getDiagResultDetail")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getDiagResultDetail( @HeaderParam("openId") String openId,
                                          @QueryParam("paperId")String paperId){

        return userService.getDiagResultDetail(openId,paperId);

    }

    /**
     *  查看购买诊断享受的折扣
     */
    @GET
    @Path("/getDiscountById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getDiscountById( @HeaderParam("openId") String openId){

        return userService.getDiscountById(openId);

    }

    /**
     *  一级代理下的二级代理订单数量 金额等信息
     */
    @GET
    @Path("/getMyProxtInfoList")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getMyProxtInfoList(@HeaderParam("openId") String openId,
                                         @QueryParam("pageNo")int pageNo,@QueryParam("pageSize")int pageSize,
                                         @QueryParam("year")Integer year,@QueryParam("month")Integer month){

        return userService.getMyProxtInfoList(openId,pageNo,pageSize,year,month);

    }

    /**
     * 查询二级代理订单
     */
    @GET
    @Path("/getSecondProxyInfoList")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getSecondProxyInfoList(@QueryParam("proxyOpenId")String proxyOpenId,@QueryParam("secondProxyOpenId")
                                                     String secondProxyOpenId,
                                             @QueryParam("pageNo")int pageNo,@QueryParam("pageSize")int pageSize,
                                             @QueryParam("year")Integer year,@QueryParam("month")Integer month){

        return userService.getSecondProxyInfoList(proxyOpenId,secondProxyOpenId,pageNo,pageSize,year,month);

    }

    /**
     *  查询一级代理订单详情
     */
    @GET
    @Path("/getMyOrderInfoList")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getMyOrderInfoList(@HeaderParam("openId") String openId,
                                         @QueryParam("pageNo")int pageNo,@QueryParam("pageSize")int pageSize,
                                         @QueryParam("year")Integer year,@QueryParam("month")Integer month){

        return userService.getMyOrderInfoList(openId,pageNo,pageSize,year,month);

    }

    /**
     *  查询一级代理下二级代理的订单量 订单金额,平台分成等信息
     */
    @GET
    @Path("/getMyProxyInfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getMyProxyInfo(@HeaderParam("openId") String openId,
                                     @QueryParam("year")Integer year,@QueryParam("month")Integer month){

        return userService.getMyProxyInfo(openId,year,month);

    }
    /**
     *  查询二级代理下的订单量 订单金额,平台分成等信息
     */
    @GET
    @Path("/getSecondProxyInfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getSecondProxyInfo(@QueryParam("proxyOpenId")String proxyOpenId,@QueryParam("secondProxyOpenId")
                                                 String secondProxyOpenId,
                                         @QueryParam("year")Integer year,@QueryParam("month")Integer month){

        return userService.getSecondProxyInfo(proxyOpenId,secondProxyOpenId,year,month);

    }
    /**
     *  查询一级代理自己订单量，订单金额,平台分成等信息
     */
    @GET
    @Path("/getMyOrderInfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getMyOrderInfo(@HeaderParam("openId") String openId,
                                     @QueryParam("year")Integer year,@QueryParam("month")Integer month){

        return userService.getMyOrderInfo(openId,year,month);

    }

    /**
     * 申请面授
     */
    @POST
    @Path("/applyPermission")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean applyPermission(@HeaderParam("tag")String tag, ApplyPermissionBean applyPermissionBean){

        return userService.applyPermission(tag,applyPermissionBean);

    }
    /**
     * 提交基础测试
     */
    @POST
    @Path("/passTest")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean passTest(@HeaderParam("tag")String tag, PassTestBean passTestBean){

        return userService.passTest(tag,passTestBean);

    }
    /**
     * 答疑提交申请
     */
    @POST
    @Path("/insertAnswerPermission")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean insertAnswerPermission(@HeaderParam("tag")String tag, UpdateanswerpermissionBean updateanswerpermissionBean){

        return userService.insertAnswerPermission(tag,updateanswerpermissionBean);

    }
    /**
     *  申请免试时间
     */
    @POST
    @Path("/applyInterviewTime")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean applyInterviewTime(@HeaderParam("tag")String tag, ApplyinterviewtimeBean applyinterviewtimeBean){

        return userService.applyInterviewTime(tag,applyinterviewtimeBean);

    }
    /**
     * 申请面授时间
     */
    @POST
    @Path("/applyFacingTeachTime")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean applyFacingTeachTime(@HeaderParam("tag")String tag, ApplyfacingteachtimeBean applyfacingteachtimeBean){

        return userService.applyFacingTeachTime(tag,applyfacingteachtimeBean);

    }

    /**
     *  诊断试卷提交答案
     */
    @POST
    @Path("/diagnoseAnswerSubmit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean diagnoseAnswerSubmit(@HeaderParam("tag")String tag, DiagnoseAnswerSubmitBean diagnoseAnswerSubmitBean){

        return userService.diagnoseAnswerSubmit(tag,diagnoseAnswerSubmitBean);

    }

    /**
     *  申请教师
     */
    @POST
    @Path("/applyTeacher")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean applyTeacher(@HeaderParam("tag")String tag, ApplyTeacherBean applyTeacherBean){

        return userService.applyTeacher(tag,applyTeacherBean);

    }
    /**
     *  工作室设置
     */
    @POST
    @Path("/setStore")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean setStore(@HeaderParam("tag")String tag,SetStoreBean setStoreBean){

        return userService.setStore(tag,setStoreBean);

    }
    /**
     *  完善个人信息
     */
    @POST
    @Path("/complete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean complete(@HeaderParam("tag")String tag,CompleteInfoBean completeInfoBean){

        return userService.complete(tag,completeInfoBean);

    }

}
