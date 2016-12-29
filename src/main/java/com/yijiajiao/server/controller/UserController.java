package com.yijiajiao.server.controller;

import com.yijiajiao.server.bean.*;
import com.yijiajiao.server.service.UserService;
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
        ResultBean result = new ResultBean();
        try {
            result = userService.validateTel(tel);
        } catch (Exception e) {
            e.printStackTrace();
            result.setFailMsg(SystemStatus.SERVER_ERROR);
        }
        return result;
    }

    /**
     * 用户注册
     */
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean register(RegisterBean registerBean){
        ResultBean result = new ResultBean();
        try {
            result = userService.register(registerBean);
        } catch (Exception e) {
            e.printStackTrace();
            result.setFailMsg(SystemStatus.SERVER_ERROR);
        }
        return result;
    }

    /**
     * 保分计划注册用户
     */
    @POST
    @Path("/planRegister")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean planRegister(PlanUserBean planUserBean) {
        ResultBean result = new ResultBean();
        try {
            result = userService.getPlanRegister(planUserBean);
        } catch (Exception e) {
            e.printStackTrace();
            result.setFailMsg(SystemStatus.SERVER_ERROR);
        }
        return result;
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
        ResultBean result = new ResultBean();
        try {
            result = userService.getVerifyCode(tel,type);
        } catch (Exception e) {
            e.printStackTrace();
            result.setFailMsg(SystemStatus.SERVER_ERROR);
        }
        return result;
    }

    /**
     *	验证验证码是否正确
     */
    @GET
    @Path("/verifycode")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean verifyCode(@QueryParam("tel")String tel,@QueryParam("type") int type,@QueryParam("telcode")String telcode){
        ResultBean result = new ResultBean();
        try {
            result = userService.verifyCode(tel,type,telcode);
        } catch (Exception e) {
            e.printStackTrace();
            result.setFailMsg(SystemStatus.SERVER_ERROR);
        }
        return result;
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
        try {
            result = userService.login(loginBean);
        } catch (Exception e) {
            e.printStackTrace();
            result.setFailMsg(SystemStatus.SERVER_ERROR);
        }
        return result;
    }

    /**
     * 查询教师列表
     * @param orderType 排序条件
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
        ResultBean result = new ResultBean();
        if(StringUtil.isEmpty(orders)){
            orders="desc";
        }
        try {
            result = userService.findteacher(pageNo, pageSize, gradeCode, subjectCode, orderType,orders);
        } catch (Exception e) {
            e.printStackTrace();
            result.setFailMsg(SystemStatus.SERVER_ERROR);
        }
        return result;
    }
}
