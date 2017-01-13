package com.yijiajiao.server.controller;

import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.service.WaresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-12-14:50
 */

@Path("/wares")
@Controller
public class WaresController {

    @Autowired
    private WaresService waresService;

    /**
     * 根据日期获取某个教师的课程表
     */
    @GET
    @Path("/timetable")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean timeTable(@HeaderParam("openId") String openId,
                                @QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {

        return waresService.timeTable(startDate, endDate, openId);

    }

    /**
     * 根据日期获取某天教师的课程信息列表
     */
    @GET
    @Path("/getwaresbydate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getWaresByDate(@HeaderParam("openId") String teacherId,
                                     @QueryParam("classDate") String classDate, @QueryParam("waresType") String waresType) {

        return waresService.getWaresByDate(classDate, waresType, teacherId);

    }

    /**
     * 课程首页列表
     */
    @GET
    @Path("/wareslist")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean wareslist(@QueryParam("curriculumType") Integer curriculumType, @QueryParam("subjectCode") String subjectCode, @QueryParam("gradeCode") String gradeCode,
                                @QueryParam("bookTypeCode") String bookTypeCode, @QueryParam("pageNo") int pageNo, @QueryParam("pageSize") int pageSize, @QueryParam("order") String order,
                                @QueryParam("orderType") String orderType,@QueryParam("stageCode") String stageCode,@QueryParam("reservePrice") String reservePrice,
                                @QueryParam("peakPrice") String peakPrice,@QueryParam("smallCourseware") String smallCourseware,@QueryParam("teacherId") String teacherId,
                                @QueryParam("status") String status,@QueryParam("isYjj")String isYjj,@QueryParam("yjjCode")String yjjCode){

        return waresService.wareslist(pageNo, pageSize, subjectCode, gradeCode,
                    bookTypeCode, curriculumType, order, orderType,stageCode,reservePrice,
                    peakPrice,smallCourseware,teacherId,status,isYjj,yjjCode);

    }

    /**
     * 课程详情页
     */
    @GET
    @Path("/waresInfoById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean waresInfoById(@QueryParam("id") int id) {

        return waresService.waresInfoById(id);

    }

    /**
     * 教师获取个人视频列表
     */
    @GET
    @Path("/getViedoById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getViedoById(@HeaderParam("token") String token, @HeaderParam("openId") String openId, @QueryParam("gradeCode") String gradeCode, @QueryParam("bookTypeCode") String bookTypeCode,@QueryParam("useType")int useType) {

        return waresService.getViedoById(openId, gradeCode, bookTypeCode,useType);

    }

    /**
     * 教师按照审核状态获取个人视频列表
     */
    @GET
    @Path("/getViedoByExamination")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean getViedoByExamination(@HeaderParam("token") String token, @HeaderParam("openId") String openId, @QueryParam("gradeCode") String gradeCode,
                                            @QueryParam("bookTypeCode") String bookTypeCode, @QueryParam("examination") String examination, @QueryParam("pageNo") int pageNo, @QueryParam("pageSize") int pageSize) {

        return waresService.getViedoByExamination(openId, gradeCode, bookTypeCode, examination, pageNo, pageSize);

    }

    /**
     * 验证教师课程时间冲突
     */
    @GET
    @Path("/checktime")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultBean checkTime(@HeaderParam("token") String token, @HeaderParam("openId") String openId, @QueryParam("startTime") String startTime,
                                @QueryParam("endTime") String endTime) {

        return waresService.checkTime(openId,startTime, endTime);

    }
}
