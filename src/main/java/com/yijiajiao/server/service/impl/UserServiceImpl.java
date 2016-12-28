package com.yijiajiao.server.service.impl;

import com.eeduspace.uuims.api.OauthClient;
import com.eeduspace.uuims.api.exception.ApiException;
import com.eeduspace.uuims.api.model.UserModel;
import com.eeduspace.uuims.api.request.user.CreateUserRequest;
import com.eeduspace.uuims.api.request.user.ValidateUserRequest;
import com.eeduspace.uuims.api.response.user.CreateUserResponse;
import com.eeduspace.uuims.api.response.user.ValidateUserResponse;
import com.eeduspace.uuims.api.util.Digest;
import com.eeduspace.uuims.api.util.GsonUtil;
import com.google.gson.Gson;
import com.yijiajiao.server.bean.PlanUserBean;
import com.yijiajiao.server.bean.RegisterBean;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.SystemStatus;
import com.yijiajiao.server.bean.user.PlanRegisterUserBean;
import com.yijiajiao.server.bean.user.UserInfoBean;
import com.yijiajiao.server.bean.user.UserLoginBean;
import com.yijiajiao.server.bean.user.UserRegisterBean;
import com.yijiajiao.server.service.UserService;
import com.yijiajiao.server.util.Config;
import com.yijiajiao.server.util.OauthFactory;
import com.yijiajiao.server.util.ServerConfig;
import com.yijiajiao.server.util.ServerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-12-28-15:03
 */

@Service("userService")
public class UserServiceImpl implements UserService{

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private static OauthFactory oauthFactory    = new OauthFactory();

    private static OauthClient oauthClient = oauthFactory.getInteance();


    @Override
    public ResultBean validateTel(String tel) {
        ResultBean result = new ResultBean();
        ValidateUserRequest request = new ValidateUserRequest();
        request.setPhone(tel);
        request.setType("phone");
        try {
            ValidateUserResponse response = oauthClient.execute(request);
            log.info("phone-only return: {" + response.toString()+"}");
            if ("Success".equals(response.getMessage())) {
                result.setSucResult("手机号可以使用");
            } else {
                result.setFailMsg(SystemStatus.UNSUPPORTED_TEL);
            }
        } catch (ApiException e) {
            log.error("检验手机号唯一性异常："+e.toString());
            result.setFailMsg(SystemStatus.UUIMS_ERROR);
        }
        return result;
    }

    @Override
    public ResultBean register(RegisterBean registerBean) {
        ResultBean result = new ResultBean();
        // 请求uuims
        CreateUserRequest request = new CreateUserRequest();
        request.setPhone(registerBean.getTelephone());
        request.setPassword(registerBean.getPassword());
        try {
            CreateUserResponse response = oauthClient.execute(request);
            Gson gson = new Gson();
            if ("200".equals(response.getHttpCode())) {
                UserModel userModel = GsonUtil.fromObjectJson(gson.toJson(response), "result", "userModel", UserModel.class);
                // 添加用户系统
                log.info("正确信息： " + response.getResult().toString());
                boolean b = registerUser(registerBean,userModel);
                if(b){
                    // 注册环信
                    registerEaseob(userModel.getOpenId(),registerBean.getPassword(),registerBean.getTelephone());
                }
                result.setSucResult("注册成功");
            } else {
                log.info("错误信息： " + response.getMessage());
                result.setFailMsg(Integer.parseInt(response.getHttpCode() + ""), "注册失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setFailMsg(SystemStatus.SERVER_ERROR);
        }
        return result;
    }

    private ResultBean registerEaseob(String openId,String telephone, String password) {
        ResultBean userResult = null;
        String easemob_path = Config.getString("solution.easemob_register");
        UserLoginBean userloginBean = new UserLoginBean();
        userloginBean.setUsername(openId + telephone.substring(8, 11));
        userloginBean.setPassword(password);
        try {
            String easemob_response = ServerUtil.httpRest(ServerConfig.SOLUTION_SERVER, easemob_path, null, userloginBean, "POST");
            userResult = (ResultBean) ServerUtil.getTransObject(easemob_response, ResultBean.class);
            if (userResult.getCode() == 200 || (userResult.getCode() + "").equals("200")) {
                log.info("正确信息： " + userResult.getResult().toString());
            } else {
                log.info("错误信息： " + userResult.getMessage());
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
        return userResult;
    }

    private boolean registerUser(RegisterBean registerBean,UserModel userModel){
        ResultBean userResult = null;
        String path = Config.getString("user.register");
        UserRegisterBean userRegisterBean = new UserRegisterBean();
        userRegisterBean.setUsername(registerBean.getTelephone());
        userRegisterBean.setInvite_code(registerBean.getInvite_code());
        userRegisterBean.setVersion(registerBean.getVersion());
        userRegisterBean.setAk(userModel.getAccessKey());
        userRegisterBean.setInvite_selfcode(Digest.md5Digest(registerBean.getTelephone()+new Date().getTime()).substring(8, 16).toUpperCase());
        userRegisterBean.setSk(userModel.getSecretKey());
        userRegisterBean.setUserOpenId(userModel.getOpenId());
        userRegisterBean.setRegistDate(userModel.getCreateDate());
        boolean b = false;
        try {
            String userResponse = ServerUtil.httpRest(ServerConfig.USER_SERVER, path, null, userRegisterBean, "POST");
            userResult = (ResultBean) ServerUtil.getTransObject(userResponse, ResultBean.class);
            if (userResult.getCode() == 200 || (userResult.getCode() + "").equals("200")) {
                log.info("正确信息： " + userResult.getResult().toString());
                b=true;
            } else {
                log.info("错误信息： " + userResult.getMessage());
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
        return b;
    }

    @Override
    public ResultBean getPlanRegister(PlanUserBean planUserBean) {
        ResultBean result = new ResultBean();
        String path = Config.getString("user.login");
        UserInfoBean userInfoBean = null;
        Gson gson = new Gson();
        PlanRegisterUserBean planRegisterUserBean = null;
        try {
            //
            if (planUserBean.getTelephone() != null && !"".equals(planUserBean.getTelephone())) {
                UserModel userModel = null;
                // 登陆用户系统
                UserLoginBean userLoginBean = new UserLoginBean();
                userLoginBean.setUsername(planUserBean.getTelephone());
                String response1 = ServerUtil.httpRest(user_server, path, null, userLoginBean, "POST");
                ResultBean r1 = (ResultBean) ServerUtil.getTransObject(response1, ResultBean.class);
                if (r1.getCode() == 200 || (r1.getCode() + "").equals("200")) {
                    log.info("正确信息： " + (r1.getResult() == null ? null : r1.getResult().toString()));
                    userInfoBean = gson.fromJson(gson.toJson(r1.getResult()), UserInfoBean.class);
                    planRegisterUserBean = new PlanRegisterUserBean();
                    planRegisterUserBean.setAccd(userInfoBean.getAccid());
                    planRegisterUserBean.setImName(userInfoBean.getImName());
                    planRegisterUserBean.setImToken(userInfoBean.getImToken());
                    planRegisterUserBean.setOpenId(userInfoBean.getUserOpenId());
                    // planRegisterUserBean.setAccessKey(userModel.getAccessKey());
                    // planRegisterUserBean.setSecretKey(userModel.getSecretKey());
                    planRegisterUserBean.setEaseobName(userInfoBean.getUserOpenId()+ planUserBean.getTelephone().substring(8, 11));
                    planRegisterUserBean.setMobile(planUserBean.getTelephone());
                    //返回给保分计划后台
                    result.setSucResult(planRegisterUserBean);
                } else {
                    log.info("错误信息：" + (r1.getResult() == null ? null : r1.getResult().toString()));
                    result.setFailMsg(500, r1.getMessage());
                }
            } else {
                if (planUserBean.getOpenId() != null && !"".equals(planUserBean.getOpenId())) {
                    String path1 = Config.getString("user.findteacherStore") + "userOpenId=" + planUserBean.getOpenId();
                    log.info(user_server + path1);
                    ResultBean r2 = null;
                    String response = ServerUtil.httpRest(user_server, path1, null, null, "GET");
                    r2 = (ResultBean) ServerUtil.getTransObject(response, ResultBean.class);
                    if (r2.getCode() == 200 || (r2.getCode() + "").equals("200")) {
                        Map<String, Object> json = JSON.parseObject(response);
                        Map<String, Object> json2 = JSON.parseObject(json.get("result") + "");
                        planRegisterUserBean = new PlanRegisterUserBean();
                        planRegisterUserBean.setAccd(json2.get("accid") + "");
                        planRegisterUserBean.setImName(json2.get("imName") + "");
                        planRegisterUserBean.setImToken(json2.get("imToken") + "");
                        planRegisterUserBean.setOpenId(planUserBean.getOpenId());
                        planRegisterUserBean.setEaseobName(planUserBean.getOpenId() + (json2.get("username") + "").substring(8, 11));
                        planRegisterUserBean.setMobile(json2.get("username") + "");
                        //返回给保分计划后台
                        result.setSucResult(planRegisterUserBean);
                    } else {
                        log.info("错误信息：" + (r2.getResult() == null ? null : r2.getResult().toString()));
                        result.setFailMsg(500, r2.getMessage());
                    }
                } else {
                    result.setFailMsg(500, "获取信息失败，请检查openId");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setFailMsg(SystemStatus.SERVER_ERROR);
        }
        return result;
    }

}
