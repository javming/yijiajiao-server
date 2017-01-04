package com.yijiajiao.server.controller;

import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-04-10:03
 */

@Path("/finance")
@Controller
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    /**
     * 获取答疑解答时长
     */
    @GET
    @Path("/getRemainAnswerTime")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getRemainAnswerTime(@QueryParam("studentId")String studentId, @QueryParam("teacherId")String
                                            teacherId,@QueryParam("gradeCode")String gradeCode) {

        return financeService.getRemainAnswerTime(studentId,teacherId,gradeCode);

    }

    /**
     * 通过openId查询绑定的支付宝账号
     */
    @GET
    @Path("/queryAlipayByOpenId")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean queryAlipayByOpenId(@HeaderParam("openId") String openId) {

        return financeService.queryAlipayByOpenId(openId);

    }

    /**
     * 账户解绑支付宝账号
     */
    @GET
    @Path("/delAlipayByOpenId")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean delAlipayByOpenId(@HeaderParam("openId") String openId) {

        return financeService.delAlipayByOpenId(openId);

    }
}
