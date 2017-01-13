package com.yijiajiao.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.eeduspace.uuims.api.OauthClient;
import com.eeduspace.uuims.api.model.UserModel;
import com.eeduspace.uuims.api.request.user.DescribeUserByPhoneRequest;
import com.eeduspace.uuims.api.response.user.DescribeUserByPhoneResponse;
import com.eeduspace.uuims.api.util.Digest;
import com.eeduspace.uuims.api.util.GsonUtil;
import com.google.gson.Gson;
import com.yijiajiao.server.bean.LoginBean;
import com.yijiajiao.server.bean.RegisterBean;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.ThirdPartyLoginBean;
import com.yijiajiao.server.service.BaseService;
import com.yijiajiao.server.service.ThirdPartyService;
import com.yijiajiao.server.service.UserService;
import com.yijiajiao.server.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-12-13:35
 */

@Service("thirdPartyService")
public class ThirdPartyServiceImpl extends BaseService implements ThirdPartyService {
    @Autowired
    private UserService userService;
    private static final Logger log = LoggerFactory.getLogger(ThirdPartyServiceImpl.class);
    private static OauthFactory oauthFactory    = new OauthFactory();
    private static final String YJKJ_SCREATKEY=Config.getString("sun_yjkj_screatKey");

    @Override
    public ResultBean loginOrRegister(ThirdPartyLoginBean tdLoginBean) {
        tdLoginBean.setTelephone(DESEncode.decode(tdLoginBean.getTelephone(),YJKJ_SCREATKEY));
        tdLoginBean.setThirdPartyUserCode(DESEncode.decode(tdLoginBean.getThirdPartyUserCode(),YJKJ_SCREATKEY));
        LoginBean loginBean = new LoginBean();
        loginBean.setVersion(tdLoginBean.getVersion());
        loginBean.setClient_id(tdLoginBean.getClient_id());
        loginBean.setTelephone(tdLoginBean.getTelephone());
        UserModel userModel = validateTel(loginBean.getTelephone());
        if(userModel == null) {
            RegisterBean registerBean = new RegisterBean();
            String pass = Digest.md5Digest(loginBean.getTelephone());
            loginBean.setPassword(pass);
            registerBean.setTelephone(loginBean.getTelephone());
            registerBean.setPassword(pass);
            registerBean.setClient_id(loginBean.getClient_id());
            registerBean.setVersion(loginBean.getVersion());
            ResultBean register = userService.register(registerBean);
            if (register.getCode() != 200) return register;
            try {
                MSMUtil.msmUtil.send1(registerBean.getTelephone(),24);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            loginBean.setPassword(userModel.getPassword());
        }
        //保存第三方code
        String path = Config.getString("user.createthirduser");
        Map<String,String> body = new HashMap<String,String>();
        body.put("phone",tdLoginBean.getTelephone());
        body.put("sunShineUserCode",tdLoginBean.getThirdPartyUserCode());
        try {
            String response = ServerUtil.httpRest(USER_SERVER, path, null, body, "POST");
            ResultBean resultBean = JSON.parseObject(response,ResultBean.class);
            if(resultBean.getCode()!=200){
                log.error("保存第三方code时出错："+resultBean.getMessage());
            }
        } catch (Exception e) {
            log.error("保存第三方code时出错："+e.getMessage());
        }
        return userService.login(loginBean);

    }


    /**
     * 检验手机号是否存在
     * @param telephone
     */
    private UserModel validateTel(String telephone)  {
        UserModel user = null;
        OauthClient client = oauthFactory.getInteance();
        DescribeUserByPhoneRequest request = new DescribeUserByPhoneRequest();
        request.setPhone(telephone);
        Gson gson = new Gson();
        try {
            DescribeUserByPhoneResponse response = client.execute(request);
            if("200".equals(response.getHttpCode())){
                user = GsonUtil.fromObjectJson(gson.toJson(response), "result", "userModel", UserModel.class);
                log.debug("通过手机号查询用户的成功结果："+gson.toJson(user));
            }else {
                log.error("通过手机号查询用户的失败信息：" +gson.toJson(response));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
