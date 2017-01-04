package com.yijiajiao.server.exception;

import com.alibaba.fastjson.JSON;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.SystemStatus;
import com.yijiajiao.server.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * 统一异常处理
 *
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-12-30-11:31
 */

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception>{

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public Response toResponse(Exception e) {

        e.printStackTrace();
        log.error("异常信息："+e.getMessage()+";异常类型："+e.getClass()+e.getLocalizedMessage());

        if (StringUtil.contains(e.getMessage(),"null for uri")){
            return Response.ok(JSON.toJSONString(ResultBean.getFailResult(404,"url not fond!")),
                    MediaType.APPLICATION_JSON).build();
        }else {
            return Response.ok(JSON.toJSONString(ResultBean.getFailResult(SystemStatus.SERVER_ERROR)),
                    MediaType.APPLICATION_JSON).build();
        }

    }
}
