package com.yijiajiao.server.controller;

import com.yijiajiao.server.bean.BaofenUserBean;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.post.FeedBackBean;
import com.yijiajiao.server.service.OSSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-04-15:00
 */

@Path("/oss")
@Controller
public class OSSController {

    @Autowired
    private OSSService ossService;

    /**
     * 前端首页展示焦点图
     */
    @GET
    @Path("/focus")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean frontfocus(@QueryParam("belongs") int belongs, @QueryParam("area") int area) {

        return ossService.frontfocus(belongs,area);

    }


    /**
     * 前端首页展示排行榜
     */
    @GET
    @Path("/showbill")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean showbill(@QueryParam("area") int area) {

        return ossService.showbill(area);

    }


    /**
     * 前端首页展示商品和名师推荐
     */
    @GET
    @Path("/showgoods")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean showgoods(@QueryParam("belongs") int belongs,@QueryParam("area") int area) {

        return ossService.showgoods(belongs,area);

    }

    /**
     * 学生端首页展示名师推荐
     */
    @GET
    @Path("/recommendTeacher")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean recommendTeacher(@QueryParam("belongs") int belongs,@QueryParam("area") int area) {

        return ossService.recommendTeacher(belongs,area);

    }

    /**
     *	查询积分规则
     */
    @GET
    @Path("/integralRuleList")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean integralRuleList(@QueryParam("pageNo") int pageNo,@QueryParam("pageSize") int pageSize){

        return ossService.integralRuleList(pageNo,pageSize);

    }

    /**
     *	保分计划用户登记
     */
    @POST
    @Path("/baofenUserAdd")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean baofenUserAdd(BaofenUserBean baoUserBean){

        return ossService.baofenUserAdd(baoUserBean);

    }
    /**
     *
     */
    @POST
    @Path("/feedBack")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean feedBack(FeedBackBean feedBackBean){

        return ossService.feedBack(feedBackBean);

    }


}
