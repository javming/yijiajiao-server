package com.yijiajiao.server.filter;

import com.yijiajiao.server.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;


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
      ServletRequest requestWrapper = new BufferedRequestWrapper(req);
      String body = FilterHelper.getBodyString(requestWrapper);
      if (req.getRequestURL().toString().contains(".ico")) return;
      log.info("\n __[请求地址:" + req.getRequestURL() + "]\n __[queryParams:?"+req.getQueryString()
              + "]\n __[请求方法:" + req.getMethod() + "]" + (StringUtil.isEmpty(body)?"":("\n __[bodyParams:"+body)));
      Enumeration<String> headerNames = req.getHeaderNames();
      while (headerNames.hasMoreElements()){
          String next = headerNames.nextElement();
          System.out.println(" __[header:"+next+"="+req.getHeader(next)+"]");
      }
      long bef = System.currentTimeMillis();
      chain.doFilter(requestWrapper, response);
      long aft = System.currentTimeMillis();
      log.info(" Request to " + req.getRequestURI() + "  use: " + (aft - bef)+ " ms");
  }

  public void init(FilterConfig filterConfig) throws ServletException {
      log.info("paramLogfilter init success!");
  }

}