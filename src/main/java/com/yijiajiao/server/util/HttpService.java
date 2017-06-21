package com.yijiajiao.server.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-06-21-11:07
 */
@Service("httpService")
public class HttpService {
    @Autowired
    private CloseableHttpClient httpClient;
    @Autowired
    private RequestConfig requestConfig;

    public String httpRest(String server, String url, Map<String, String> headers,
                                  Object body, String method){
        String res;
        switch (method) {
            case "GET":
                res = doGet(server + url, headers);
                break;
            case "POST":
                res = doPostJson(server + url, JSON.toJSONString(body), headers);
                break;
            case "DELETE":
                res = doDelete(server + url, headers);
                break;
            case "PUT":
                res = doPutJson(server + url, JSON.toJSONString(body), headers);
                break;
            default:
                res = "";
        }
        return res;
    }

    /**
     * 执行GET请求
     *
     */
    public String doGet(String url, Map<String, String> headers){
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(this.requestConfig);
        if (headers != null && !headers.isEmpty()){
            for (String key : headers.keySet()){
                httpGet.setHeader(key, headers.get(key));
            }
        }
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    /**
     * 带有参数的GET请求
     *
     */
    public String doGet(String url, Map<String, String> params, Map<String, String> headers)
            throws IOException, URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(url);
        for (String key : params.keySet()) {
            uriBuilder.addParameter(key, params.get(key));
        }
        return this.doGet(uriBuilder.build().toString(), headers);
    }

    public String doDelete(String url, Map<String, String> headers){
        // 创建http GET请求
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.setConfig(this.requestConfig);
        if (headers != null && !headers.isEmpty()){
            for (String key : headers.keySet()){
                httpDelete.setHeader(key, headers.get(key));
            }
        }
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpDelete);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 执行POST请求
     */
    public String doPost(String url, Map<String, String> params) throws IOException {
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(this.requestConfig);
        if (params != null) {
            // 设置2个post参数，一个是scope、一个是q
            List<NameValuePair> parameters = new ArrayList<>();
            for (String key : params.keySet()) {
                parameters.add(new BasicNameValuePair(key, params.get(key)));
            }
            // 构造一个form表单式的实体
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(formEntity);
        }

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * 执行POST请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public String doPost(String url) throws IOException {
        return this.doPost(url, null);
    }

    /**
     * 提交json数据
     */
    public String doPostJson(String url, String json, Map<String, String> headers){
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(this.requestConfig);

        if (json != null) {
            // 构造一个form表单式的实体
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(stringEntity);
        }

        if ( headers != null && !headers.isEmpty()){
            for (String key : headers.keySet()){
                httpPost.setHeader(key, headers.get(key));
            }
        }

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = this.httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String doPutJson(String url, String json, Map<String, String> headers){
        // 创建http PUT请求
        HttpPut httpPut = new HttpPut(url);
        httpPut.setConfig(this.requestConfig);

        if (json != null) {
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPut.setEntity(stringEntity);
        }

        if ( headers != null && !headers.isEmpty()){
            for (String key : headers.keySet()){
                httpPut.setHeader(key, headers.get(key));
            }
        }

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = this.httpClient.execute(httpPut);
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
