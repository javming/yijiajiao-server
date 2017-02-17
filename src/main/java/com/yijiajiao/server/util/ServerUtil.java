package com.yijiajiao.server.util;

import com.alibaba.fastjson.JSON;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class ServerUtil {
    private static final Logger log = LoggerFactory.getLogger(ServerUtil.class);
    /**
     * httpclient请求器  （格式1）
     *
     * @param server      服务器地址
     * @param url         资源URL
     * @param headParams  请求头参数列表
     * @param bodyParam   Body参数
     * @param method      请求方式（POST,GET,PUT,DELETE）
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String httpRest(String server, String url, Map<String, Object> headParams, Object bodyParam, String method) {
        ClientConfig config = new DefaultClientConfig();
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client c = Client.create(config);
        c.setConnectTimeout(300*1000);
        WebResource webResource = c.resource("http://"+server + url);
        log.info(" 请求其它系统路径：===>>\n __[http://"+server + url+"]"+
                (bodyParam==null?"": ("\n __[bodyParams]:"+JSON.toJSONString(bodyParam))));
        Builder builder = webResource.header("Content-Type", MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON);
        ClientResponse response = null;
        if (headParams != null) {
            Iterator i = headParams.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry en = (Map.Entry) i.next();
                builder.header((String) en.getKey(), en.getValue());
            }
        }
        if ("POST".equals(method)) {
            response = builder.post(ClientResponse.class, bodyParam);
        } else if ("GET".equals(method)) {
            response = builder.get(ClientResponse.class);
        } else if ("PUT".equals(method)) {
            response = builder.put(ClientResponse.class, bodyParam);
        } else if ("DELETE".equals(method)) {
            response = builder.delete(ClientResponse.class);
        }
        String result = response.getEntity(String.class);
        log.info(" 其他系统返回：<<===\n __"+result);
        return result;
    }



    /**
     * httpclient——Form格式请求器  （格式2）
     *
     * @param server      服务器地址
     * @param url         资源URL
     * @param headParams  请求头参数列表
     * @param bodyParam   Body参数
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String httpRestForm(String server, String url, Map<String, Object> headParams, Object bodyParam) {
      ClientConfig config = new DefaultClientConfig();
      config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
      Client c = Client.create(config);
      WebResource r = c.resource("http://"+server + url);
      log.info(" 请求其它系统路径：===>>\n __[http://"+server + url+"]\n  __[bodyParam:]"+ JSON.toJSONString(bodyParam));
      Builder builder = r.header("Content-Type", MediaType.APPLICATION_FORM_URLENCODED).type(MediaType.APPLICATION_FORM_URLENCODED);
      ClientResponse response = null;
      if (headParams != null) {
        Iterator i = headParams.entrySet().iterator();
        while (i.hasNext()) {
          Map.Entry en = (Map.Entry) i.next();
          builder.header((String) en.getKey(), en.getValue());
        }
      }
      response = builder.post(ClientResponse.class, bodyParam);
      String result = response.getEntity(String.class);
      log.info(" 其他系统返回：<<===\n __"+result+"");
      return result;
    }

    /**
     * httpclient请求器  （格式2） //不支持请求头放参数
     *
     * @param url          服务器地址    + 资源URL
     * @param bodyParam   Body参数
     * @param method      请求方式（POST,GET,PUT,DELETE）
     * @return
     */

    public static ClientResponse resource(String url, Object bodyParam, String method) {
      ClientConfig config = new DefaultClientConfig();
      config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
      Client c = Client.create(config);
      WebResource r = c.resource(url);
      ClientResponse response = null;
      if ("POST".equals(method)) {
        response = r.header("Content-Type", MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
            .post(ClientResponse.class, bodyParam);
      } else if ("GET".equals(method)) {
        response = r.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
      } else if ("PUT".equals(method)) {
        response = r.header("Content-Type", MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
            .put(ClientResponse.class, bodyParam);
      }
      return response;
    }


    // json转化成对象
    public static Object getTransObject(String param, Class<?> clazz) throws Exception {
      ObjectMapper mapper = new ObjectMapper();
      //Object obj = mapper.readValue(URLDecoder.decode(param, "UTF-8"), clazz);
      Object obj = mapper.readValue(param, clazz);
      return obj;
    }

    public static Date long2Date(long time) throws ParseException{
      Date result = null;
        String since = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time*1000));
        result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(since);
      return result;
    }

    public static String getDateStr() throws ParseException{
      String result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
      return result;
    }


    public static String randomCode() {
      String sRand = "";
      for (int i = 0; i < 6; i++) {
        String rand = getRandomChar();
        sRand = sRand.concat(rand);
      }
      return sRand;
    }

    private static String getRandomChar() {
      String randChar = "";
      randChar = String.valueOf(Math.round(Math.random() * 9));
      return randChar;
    }

    //string类型转换为long类型
    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType) throws ParseException, ParseException {
        Date date = new SimpleDateFormat(formatType).parse(strTime);
        long currentTime = date.getTime(); // date类型转成long类型
        return currentTime;
    }

    public static void main(String[] args) {
      try {
        long a = stringToLong("2016-02-20 10:46:00","yyyy-MM-dd hh:mm:ss");
        System.out.println(a);
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }



}
