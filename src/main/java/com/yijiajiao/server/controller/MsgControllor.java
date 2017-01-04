package com.yijiajiao.server.controller;

import com.yijiajiao.server.bean.IdBean;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-04-13:47
 */
@Path("/msg")
@Controller
public class MsgControllor {

    @Autowired
    private MsgService msgService;

    /**
     * 根据日期获取某个教师的课程表
     */
    @GET
    @Path("/mymsglist")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean myMsgList(@QueryParam("msgType") int msgType, @QueryParam("msgGroup") int msgGroup,
                                @QueryParam("isTeacher") int isTeacher,@QueryParam("pageNo") int pageNo,
                                @QueryParam("pageSize") int pageSize,@HeaderParam("openId") String teacherId) {

        return msgService.myMsgList(msgGroup, msgType,isTeacher, pageNo,pageSize,teacherId);

    }

    /**
     *  获取个人未读信息数量，按msgType
     */
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean count(@HeaderParam("openId") String openId, @QueryParam("isTeacher") int isTeacher) {

        return msgService.count(openId,isTeacher);

    }

    /**
     * 获取个人未读信息数量，按msgGroup
     */
    @GET
    @Path("/countByType")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean countByType(@QueryParam("msgType") int msgType,@HeaderParam("openId") String openId,
                                  @QueryParam("isTeacher") int isTeacher) {

        return msgService.countByType(msgType,openId,isTeacher);

    }


    /**
     * 标记已读
     */
    @PUT
    @Path("/setRead")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean setRead(IdBean ids,@HeaderParam("openId") String openId) {

        return msgService.setRead(ids);

    }


    /**
     * 根据Id删除消息
     */
    @GET
    @Path("/setDel")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean setDel(@HeaderParam("openId") String openId,@QueryParam("id") int id,@QueryParam("msgType") int msgType ) {

        return msgService.setDel(openId,id,msgType);

    }


    /**
     * 移动端消息模块最新一条
     */
    @GET
    @Path("/mynewmsg")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean myNewMsg(@HeaderParam("openId") String openId, @QueryParam("isTeacher") int isTeacher) {

        return msgService.myNewMsg(openId,isTeacher);

    }


    /**
     * 移动端获取个人消息设置
     */
    @GET
    @Path("/getmsgset")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getMsgSet(@HeaderParam("openId") String openId, @QueryParam("isTeacher") int isTeacher) {

        return msgService.getMsgSet(openId,isTeacher);

    }

    /**
     *	获取未读站内信数量
     */
    @GET
    @Path("/getStationCount")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getMsgStation(@HeaderParam("openId") String openId,@QueryParam("openId")String userOpenId,@QueryParam("isTeacher")int isTeacher){

        return msgService.getMsgStation(userOpenId,isTeacher);

    }
}
