package com.yijiajiao.server.exception;

import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.SystemStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        ResultBean result = new ResultBean();
        log.error(" **服务器运行时发生异常了: <"+e.getMessage()+">**");
        e.printStackTrace();
        result.setFailMsg(SystemStatus.SERVER_ERROR);
        return Response.ok().entity(result).build();
    }
}
