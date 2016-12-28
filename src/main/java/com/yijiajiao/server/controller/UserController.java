package com.yijiajiao.server.controller;

import com.yijiajiao.server.bean.PlanUserBean;
import com.yijiajiao.server.bean.RegisterBean;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.SystemStatus;
import com.yijiajiao.server.service.UserService;
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

}
