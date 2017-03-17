package com.yijiajiao.server.service;

import com.alibaba.fastjson.JSON;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.util.Config;
import net.rubyeye.xmemcached.MemcachedClient;
import org.slf4j.Logger;

/**
 * service基类
 *
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-04-10:16
 */
public class BaseService {

    public static final String USER_SERVER = Config.getString("user_server");

    public static final String TEACH_SERVER = Config.getString("teach_server");

    public static final String SOLUTION_SERVER = Config.getString("solution_server");

    public static final String WARES_SERVER = Config.getString("wares_server");

    public static final String FINANCE_SERVER = Config.getString("finance_server");

    public static final String MSG_SERVER = Config.getString("msg_server");

    public static final String OSS_SERVER = Config.getString("oss_server");

    public static final String CUSTOMER_SERVER = Config.getString("customer_server");

    public static final String SALE_SERVER = Config.getString("sale_server");

    public static final String DEPENDENT_SERVER = Config.getString("dependent_server");

    public static final String PROMOTION_SERVER = Config.getString("promotion_server");

    public static final String KEEPMARK_SERVER = Config.getString("keepmark_server");

    protected static final int IF_MEM = Config.getInt("if_mem");


    /**
     *  处理其他服务器返回结果
     */
    protected ResultBean dealResult(Logger log ,String response){
        ResultBean result = new ResultBean();
        ResultBean resultBean = JSON.parseObject(response, ResultBean.class);
        if (resultBean.getCode() == 200) {
            log.info("正确信息： " + resultBean.getResult());
            result.setSucResult(resultBean.getResult());
        } else {
            log.info("错误信息： " + resultBean.getMessage());
            result.setFailMsg(resultBean.getCode(), resultBean.getMessage());
        }
        return result;
    }

    protected void setMemcached(String tag, String value, MemcachedClient memcachedClient,Logger log) {

        try {
            boolean flag = memcachedClient.set(tag, 0, value);
            if (flag) {
                log.info("set memcached result is :    " + tag + " = " + value);
            }
        } catch (Exception e) {
            log.error("set MemcachedClient failed!!");
            e.printStackTrace();
        }
    }
}
