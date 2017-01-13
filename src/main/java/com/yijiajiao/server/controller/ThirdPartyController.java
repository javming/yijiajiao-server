package com.yijiajiao.server.controller;

import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.SystemStatus;
import com.yijiajiao.server.bean.ThirdPartyLoginBean;
import com.yijiajiao.server.service.ThirdPartyService;
import com.yijiajiao.server.service.impl.ThirdPartyServiceImpl;
import com.yijiajiao.server.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
}
