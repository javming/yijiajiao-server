package com.yijiajiao.server.controller;

import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.SyncOrderInfo;
import com.yijiajiao.server.bean.SystemStatus;
import com.yijiajiao.server.bean.ThirdPartyLoginBean;
import com.yijiajiao.server.service.ThirdPartyService;
import com.yijiajiao.server.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-12-10:28
 */
@Path("/thirdParty")
@Controller
public class ThirdPartyController {

    @Autowired
    private ThirdPartyService thirdPartyService;

    /**
     * 第三方登录入口
     */
    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean login(ThirdPartyLoginBean loginBean){

        if (StringUtil.isEmpty(loginBean.getTelephone())){
            return ResultBean.getFailResult(SystemStatus.USERNAME_IS_NULL);
        }

        return thirdPartyService.loginOrRegister(loginBean);

    }

    /**
     * 第三方同步订单信息
     */
    @Path("/syncOrderInfo")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean syncOrderInfo(SyncOrderInfo syncOrderInfo){
        return thirdPartyService.syncOrderInfo(syncOrderInfo);

    }


    /**
     * 修改订单状态 2支付成功  8取消订单
     */
    @Path("/updateOrderStatus")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean updateOrderStatus(@QueryParam("orderNumber") String orderNumber,@QueryParam("status")int status){
        return thirdPartyService.updateOrderStatus(orderNumber,status);
    }
}
