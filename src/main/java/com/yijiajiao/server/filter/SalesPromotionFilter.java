package com.yijiajiao.server.filter;

import com.yijiajiao.server.service.PromotionService;
import com.yijiajiao.server.service.impl.PromotionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 促销活动检索
 *
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-12-22-10:32
 */

public class SalesPromotionFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(SalesPromotionFilter.class);

    private PromotionService promotionService = new PromotionServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("SalesPromotionFilter init success!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        //HttpServletResponse respon = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        log.info("__[request.uri:"+uri+"]");
        CoverResponse resp = new CoverResponse((HttpServletResponse) response);
        chain.doFilter(request,resp);
        byte[] data = resp.getResponseData();
        String redata = new String(data,"UTF-8");
        if (uri.contains("/wares/waresListByType")){
            redata = promotionService.waresListByType(redata);
        }
        response.setContentType("application/json; charset=utf-8");
        log.info("The return's data:\n __"+redata);
        PrintWriter writer = response.getWriter();
        writer.print(redata);
        writer.close();

    }

    @Override
    public void destroy() {

    }
}
