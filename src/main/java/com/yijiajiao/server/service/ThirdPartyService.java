package com.yijiajiao.server.service;


import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.ThirdPartyLoginBean;

public interface ThirdPartyService {

    ResultBean loginOrRegister(ThirdPartyLoginBean loginBean);
}
