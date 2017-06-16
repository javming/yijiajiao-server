package com.yijiajiao.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eeduspace.uuims.api.OauthClient;
import com.eeduspace.uuims.api.model.UserModel;
import com.eeduspace.uuims.api.request.user.DescribeUserByPhoneRequest;
import com.eeduspace.uuims.api.response.user.DescribeUserByPhoneResponse;
import com.eeduspace.uuims.api.util.Digest;
import com.eeduspace.uuims.api.util.GsonUtil;
import com.google.gson.Gson;
import com.yijiajiao.server.bean.*;
import com.yijiajiao.server.bean.post.CreateOrderBean;
import com.yijiajiao.server.service.ThirdPartyService;
import com.yijiajiao.server.service.UserService;
import com.yijiajiao.server.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-12-13:35
 */

@Service("thirdPartyService")
public class ThirdPartyServiceImpl implements ThirdPartyService {
    private static final Logger log = LoggerFactory.getLogger(ThirdPartyServiceImpl.class);
    private static OauthFactory oauthFactory    = new OauthFactory();
    private UserService userService = new UserServiceImpl();
    private static final String user_server = Config.getString("user_server");
    private static final String sale_server = Config.getString("sale_server");
    private static final String YJKJ_SCREATKEY=Config.getString("sun_yjkj_screatKey");

    @Override
    public ResultBean loginOrRegister(ThirdPartyLoginBean tdLoginBean) {
        //非阳光保险用户不需要解密
        String telephone = "2.0".equals(tdLoginBean.getClient_id())?
                    DESEncode.decode(tdLoginBean.getTelephone(),YJKJ_SCREATKEY):tdLoginBean.getTelephone();
        String thirdUserCode = "2.0".equals(tdLoginBean.getClient_id())?
                DESEncode.decode(tdLoginBean.getThirdPartyUserCode(),YJKJ_SCREATKEY):tdLoginBean.getThirdPartyUserCode();
        //通过手机号获取用户
        UserModel userModel = validateTel(telephone);
        LoginBean loginBean = new LoginBean(telephone, null, tdLoginBean.getClient_id(), tdLoginBean.getVersion());
        if(userModel == null) {
            RegisterBean registerBean = new RegisterBean();
            String pass = Digest.md5Digest(telephone);
            loginBean.setPassword(pass);
            registerBean.setTelephone(telephone);
            registerBean.setPassword(pass);
            registerBean.setClient_id(loginBean.getClient_id());
            registerBean.setVersion(loginBean.getVersion());
            ResultBean register = userService.register(registerBean);
            if (register.getCode() != 200) return register;
            try {
                MSMUtil.msmUtil.send1(telephone,24);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            loginBean.setPassword(userModel.getPassword());
        }
        //保存第三方code
        String path = Config.getString("user.createthirduser");
        Map<String,String> body = new HashMap<String,String>();
        body.put("phone", telephone);
        body.put("sunShineUserCode", thirdUserCode);
        try {
            String response = ServerUtil.httpRest(user_server, path, null, body, "POST");
            ResultBean resultBean = JSON.parseObject(response,ResultBean.class);
            if(resultBean.getCode()!=200){
                log.error("保存第三方code时出错："+resultBean.getMessage());
            }
        } catch (Exception e) {
            log.error("保存第三方code时出错："+e.getMessage());
        }
        return  userService.login(loginBean);
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

    @Override
    public ResultBean syncOrderInfo(SyncOrderInfo syncOrderInfo) {
        log.info("syncOrderInfo_params:\n  "+syncOrderInfo);
        //根据第三方用户code获得亿家教用户openId
        String path = Config.getString("user.thirdUserInfo")+"sunShineCode="+syncOrderInfo.getUserCode();
        String userResponse = ServerUtil.httpRest(user_server, path, null, null, "GET");
        ResultBean resultBean = JSON.parseObject(userResponse, ResultBean.class);
        if (resultBean.getCode()!=200){
            return ResultBean.getFailResult(400001,"用户code异常!");
        }else if (null == resultBean.getResult()){
            String phone = DESEncode.encode(syncOrderInfo.getUserCode(),YJKJ_SCREATKEY);
            String version = "1.0";
            if (syncOrderInfo.getOrderSource()==1) version = "2.0";
            else if (syncOrderInfo.getOrderSource()==2) version = "3.0";
            ThirdPartyLoginBean tdLoginBean = new ThirdPartyLoginBean(phone,null,"E-web",version,phone);
            ResultBean loginResult = loginOrRegister(tdLoginBean);
            if (loginResult.getCode() != 200){
                return loginResult;
            }else {
                resultBean.setSucResult(loginResult.getResult());
            }
        }
        String openId = JSON.parseObject(JSON.toJSONString(resultBean.getResult())).getString("openId");
        //同步订单信息
        CreateOrderBean createOrderBean = new CreateOrderBean();
        createOrderBean.setOrder_number(syncOrderInfo.getOrderNum());
        createOrderBean.setCommodity_id(syncOrderInfo.getCommodityId());
        createOrderBean.setOpen_id(openId);
        createOrderBean.setOrder_price(syncOrderInfo.getOrderPrice());
        createOrderBean.setCommodityType(syncOrderInfo.getCommodityType());
        createOrderBean.setDiagnosisGoodsDetailCode(syncOrderInfo.getDiagnosisGoodsDetailCode());
        createOrderBean.setDiagnosisGoodsCode(syncOrderInfo.getDiagnosisGoodsCode());
        createOrderBean.setDiagnosticRecordsName(syncOrderInfo.getDiagnosticRecordsName());
        createOrderBean.setMultiPaperCode(syncOrderInfo.getMultiPaperCode());
        createOrderBean.setUsed(syncOrderInfo.getUsed());
        createOrderBean.setDiscountPrice(null==syncOrderInfo.getDiscountPrice()?syncOrderInfo.getOrderPrice():syncOrderInfo.getDiscountPrice());
        createOrderBean.setExamStartDate(syncOrderInfo.getExamStartDate());
        createOrderBean.setExamEndDate(syncOrderInfo.getExamEndDate());
        createOrderBean.setDiscountYard(syncOrderInfo.getDiscountYard());
        createOrderBean.setPrice(syncOrderInfo.getPrice());
        createOrderBean.setSunshine(syncOrderInfo.getOrderSource());
        createOrderBean.setTeacherId(syncOrderInfo.getTeacherId());
        JSONObject bodyParam = JSON.parseObject(JSON.toJSONString(createOrderBean));
        String path2 = Config.getString("sale.createOrder");
        String saleResponse = ServerUtil.httpRest(sale_server, path2, null, bodyParam, "POST");
        ResultBean bean = JSON.parseObject(saleResponse, ResultBean.class);
        if (bean.getCode()!= 200){
            return ResultBean.getFailResult(bean.getCode(),bean.getMessage());
        }
        return ResultBean.getSucResult("syncOrderInfo success!");
    }

    @Override
    public ResultBean updateOrderStatus(String orderNumber, int status) {
        String path = Config.getString("sale.updateOrderStatus")+"orderNumber="+orderNumber+"&status="+status
                        +"&payType=" +5;
        String response = ServerUtil.httpRest(sale_server, path, null, null, "PUT");
        ResultBean resultBean = JSON.parseObject(response, ResultBean.class);
        if (resultBean.getCode()!=200){
            return ResultBean.getFailResult(resultBean.getCode(),resultBean.getMessage());
        }
        return ResultBean.getSucResult("updateOrderStatus success!");
    }
}
