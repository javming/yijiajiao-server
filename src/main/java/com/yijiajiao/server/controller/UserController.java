package com.yijiajiao.server.controller;

import com.yijiajiao.server.bean.*;
import com.yijiajiao.server.service.UserService;
import com.yijiajiao.server.service.impl.UserServiceImpl;
import com.yijiajiao.server.util.StringUtil;
import com.yijiajiao.server.util.TokenUtil;
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
}
