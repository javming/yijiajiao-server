package com.yijiajiao.server.service;


import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.SyncOrderInfo;
import com.yijiajiao.server.bean.ThirdPartyLoginBean;

public interface ThirdPartyService {

    ResultBean loginOrRegister(ThirdPartyLoginBean loginBean);

    ResultBean syncOrderInfo(SyncOrderInfo syncOrderInfo);

    ResultBean updateOrderStatus(String orderNumber, int status);
}
