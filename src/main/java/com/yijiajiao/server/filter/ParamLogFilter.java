package com.yijiajiao.server.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;


/**
 * Tomcat GET方式表单提交乱码解决。
 * 
 * @author BeanSoft
 * 
 */
public class ParamLogFilter implements Filter {

  private static final Logger log = LoggerFactory.getLogger(ParamLogFilter.class);

  public void destroy() {
    // TODO Auto-generated method stub
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
                      ServletException {
      HttpServletRequest req = (HttpServletRequest) request;
      req.setCharacterEncoding("utf-8");
      log.info("\n__[请求地址:" + req.getRequestURL()+"]\n__[queryParams:"+req.getQueryString()+"]\n__[请求方法:" + req.getMethod()
                + "]\n__[ token: " + req.getHeader("token")+"]");
      int length = req.getContentLength();
      if (length > 0) {
          BufferedRequestWrapper bufferedRequest = new BufferedRequestWrapper(req, length);
          InputStream is = bufferedRequest.getInputStream();
          byte[] content = new byte[length];
          int pad = 0;
          while (pad < length) {
            pad += is.read(content, pad, length);
          }
          log.info("bodyParams:\n" + new String(content, "utf-8"));
          request = bufferedRequest;
      }
      long bef = System.currentTimeMillis();
      chain.doFilter(request, response);
      long aft = System.currentTimeMillis();
      log.info(" Request to " + req.getRequestURI() + "  use: " + (aft - bef)+ " ms");
  }

  public void init(FilterConfig filterConfig) throws ServletException {
      log.info("创建filter");
  }

}