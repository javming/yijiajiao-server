package com.yijiajiao.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.eeduspace.uuims.api.OauthClient;
import com.eeduspace.uuims.api.exception.ApiException;
import com.eeduspace.uuims.api.model.UserModel;
import com.eeduspace.uuims.api.request.login.LoginRequest;
import com.eeduspace.uuims.api.request.user.ActivationUserRequest;
import com.eeduspace.uuims.api.request.user.CreateUserRequest;
import com.eeduspace.uuims.api.request.user.ValidateUserRequest;
import com.eeduspace.uuims.api.response.login.LoginResponse;
import com.eeduspace.uuims.api.response.user.ActivationUserResponse;
import com.eeduspace.uuims.api.response.user.CreateUserResponse;
import com.eeduspace.uuims.api.response.user.ValidateUserResponse;
import com.eeduspace.uuims.api.util.Digest;
import com.eeduspace.uuims.api.util.GsonUtil;
import com.yijiajiao.server.bean.*;
import com.yijiajiao.server.bean.solution.EaseObUserInfoBean;
import com.yijiajiao.server.bean.user.*;
import com.yijiajiao.server.service.UserService;
import com.yijiajiao.server.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    @Override
    public ResultBean validateTel(String tel) {
        ResultBean result = new ResultBean();
        OauthClient oauthClient = oauthFactory.getInteance();
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
        OauthClient oauthClient = oauthFactory.getInteance();
        CreateUserRequest request = new CreateUserRequest();
        request.setPhone(registerBean.getTelephone());
        request.setPassword(registerBean.getPassword());
        try {
            CreateUserResponse response = oauthClient.execute(request);
            if ("200".equals(response.getHttpCode())) {
                UserModel userModel = GsonUtil.fromObjectJson(JSON.toJSONString(response), "result", "userModel", UserModel.class);
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
            userResult = JSON.parseObject(easemob_response, ResultBean.class);
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
            userResult = JSON.parseObject(userResponse, ResultBean.class);
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
        PlanRegisterUserBean planRegisterUserBean = null;
        try {
            //
            if (StringUtil.isNotEmpty(planUserBean.getTelephone())) {
                // 登陆用户系统
                UserLoginBean userLoginBean = new UserLoginBean();
                userLoginBean.setUsername(planUserBean.getTelephone());
                String response = ServerUtil.httpRest(ServerConfig.USER_SERVER, path, null, userLoginBean, "POST");
                ResultBean resultBean = JSON.parseObject(response, ResultBean.class);
                if (resultBean.getCode() == 200) {
                    log.info("正确信息： " + (resultBean.getResult() == null ? null : JSON.toJSONString(resultBean.getResult())));
                    UserInfoBean userInfoBean = JSON.parseObject(JSON.toJSONString(resultBean.getResult()), UserInfoBean.class);
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
                    log.info("错误信息：" + resultBean.getMessage());
                    result.setFailMsg(500, resultBean.getMessage());
                }
            } else {
                if ( StringUtil.isNotEmpty(planUserBean.getOpenId())) {
                    String path1 = Config.getString("user.findteacherStore") + "userOpenId=" + planUserBean.getOpenId();
                    ResultBean resultBean = null;
                    String response = ServerUtil.httpRest(ServerConfig.USER_SERVER, path1, null, null, "GET");
                    resultBean = JSON.parseObject(response, ResultBean.class);
                    if (resultBean.getCode() == 200 || (resultBean.getCode() + "").equals("200")) {
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
                        log.info("错误信息：" + (resultBean.getResult() == null ? null : resultBean.getResult().toString()));
                        result.setFailMsg(500, resultBean.getMessage());
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

    @Override
    public ResultBean getVerifyCode(String tel, int type) {
        MSMUtil msmUtil = MSMUtil.msmUtil;
        ResultBean result = msmUtil.send1(tel,type);
        if(result.getCode()==200){
            RedisUtil.setEx(tel+type, 60, (String) result.getResult());
        }
        return result;
    }

    @Override
    public ResultBean verifyCode(String tel, int type, String telcode) {
        ResultBean result = new ResultBean();
        String code = RedisUtil.getValue(tel+type);
        log.info("正确验证码："+code);
        if(telcode.equals(code)){
            result.setSucResult(true);
        }else{
            result.setSucResult(false);
        }
        return result;
    }

    @Override
    public ResultBean login(LoginBean login) {
        ResultBean resultBean = new ResultBean();
        String path = Config.getString("user.login");
        OauthClient client = oauthFactory.getInteance();
        // UUIMS登录
        LoginRequest request = new LoginRequest();
        request.setProductType(Config.uuimsString("productType"));
        request.setEquipmentType(TokenUtil.getClientType(login.getClient_id()));
        request.setPhone(login.getTelephone());
        request.setPassword(login.getPassword());
        UserInfoResultBean userInfoResultBean = new UserInfoResultBean();
        LoginResponse login_response = null;
        try {
            login_response = client.execute(request);
        } catch (ApiException e) {
            log.error("uuims登录异常："+e.getMessage());
            throw new RuntimeException(e);
        }
        log.info("登录uuims返回值：\n __"+JSON.toJSONString(login_response));
        // 登陆uuims成功
        if ("200".equals(login_response.getHttpCode())) {
            UserModel userModel = null;
            UserInfoBean userInfoBean = null;
            try {
                userModel = GsonUtil.fromObjectJson(JSON.toJSONString(login_response), "result", "userModel", UserModel.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            userInfoResultBean.setToken(userModel.getToken());
            userInfoResultBean.setRefreshToken(userModel.getRefreshToken());
            userInfoResultBean.setCreateDate(userModel.getCreateDate());
            userInfoResultBean.setExpires(userModel.getExpires());
            userInfoResultBean.setOpenId(userModel.getOpenId());
            userInfoResultBean.setUser_status(userModel.getStatus());
            userInfoResultBean.setTelePhone(userModel.getPhone());
            userInfoResultBean.setSex(userModel.getSex());
            userInfoResultBean.setName(userModel.getRealName());
            userInfoResultBean.setNickName(userModel.getNickName());
            userInfoResultBean.setSessionId(userModel.getSessionId());
            // 登陆用户系统
            UserLoginBean userLoginBean = new UserLoginBean();
            userLoginBean.setUsername(login.getTelephone());
            String response = ServerUtil.httpRest(ServerConfig.USER_SERVER, path, null, userLoginBean, "POST");
            ResultBean userResult = JSON.parseObject(response, ResultBean.class);
            if (userResult.getCode() == 200) {
                userInfoBean = JSON.parseObject(JSON.toJSONString(userResult.getResult()), UserInfoBean.class);
                userInfoResultBean.setName(userInfoBean.getName());
                userInfoResultBean.setSex(userInfoBean.getSex());
                userInfoResultBean.setAuthDate(userInfoBean.getAuthDate());
                userInfoResultBean.setBackPic(userInfoBean.getBackPic());
                userInfoResultBean.setUsername(userInfoBean.getUsername());
                userInfoResultBean.setBirthday(userInfoBean.getBirthday());
                userInfoResultBean.setDescription(userInfoBean.getDescription());
                userInfoResultBean.setFrontPic(userInfoBean.getFrontPic());
                userInfoResultBean.setGradeModel(userInfoBean.getGradeModel());
                userInfoResultBean.setIdCard(userInfoBean.getIdCard());
                userInfoResultBean.setIDPic(userInfoBean.getIdPic());
                userInfoResultBean.setLastDate(userInfoBean.getLastDate());
                userInfoResultBean.setMail(userInfoBean.getMail());
                userInfoResultBean.setOtherPic(userInfoBean.getOtherPic());
                userInfoResultBean.setProvinceCode(userInfoBean.getProvinceCode());
                userInfoResultBean.setProvinceName(userInfoBean.getProvinceName());
                userInfoResultBean.setSchool(userInfoBean.getSchool());
                userInfoResultBean.setScoreGrade(userInfoBean.getScoreGrade());
                userInfoResultBean.setParentName(userInfoBean.getParentName());
                userInfoResultBean.setStageName(userInfoBean.getStageName());
                userInfoResultBean.setStageCode(userInfoBean.getStageCode());
                userInfoResultBean.setState(userInfoBean.getState());
                userInfoResultBean.setStatus_st(userInfoBean.getStatus_st());
                userInfoResultBean.setStorePic(userInfoBean.getStorePic());
                userInfoResultBean.setSubjectName(userInfoBean.getSubjectName());
                userInfoResultBean.setSubjectCode(userInfoBean.getSubjectCode());
                userInfoResultBean.setTeachAge(userInfoBean.getTeachAge());
                userInfoResultBean.setTeacher_grade(userInfoBean.getTeacher_grade());
                userInfoResultBean.setVideo_permission(userInfoBean.getVideo_permission());
                userInfoResultBean.setSolutionpermission(userInfoBean.getSolutionpermission());
                userInfoResultBean.setTeacher_grade(userInfoBean.getTeacher_grade());
                userInfoResultBean.setPic(userInfoBean.getPic());
                userInfoResultBean.setFacingTeachPermission(userInfoBean.getFacingTeachPermission());
                userInfoResultBean.setQualifyPic(userInfoBean.getQualifyPic());
                userInfoResultBean.setOtherPic(userInfoBean.getOtherPic());
                userInfoResultBean.setStoreScore(userInfoBean.getStoreScore());
                userInfoResultBean.setCount(userInfoBean.getCount());
                userInfoResultBean.setCityCode(userInfoBean.getCityCode());
                userInfoResultBean.setCityName(userInfoBean.getCityName());
                userInfoResultBean.setAreaCode(userInfoBean.getAreaCode());
                userInfoResultBean.setAreaName(userInfoBean.getAreaName());
                userInfoResultBean.setStoreName(userInfoBean.getStoreName());
                userInfoResultBean.setIntroduction(userInfoBean.getIntroduction());
                userInfoResultBean.setBrief(userInfoBean.getBrief());
                userInfoResultBean.setLabel(userInfoBean.getLabel());
                userInfoResultBean.setAddress(userInfoBean.getAddress());
                userInfoResultBean.setParentPhone(userInfoBean.getParentPhone());
                userInfoResultBean.setStoreBackPic(userInfoBean.getStoreBackPic());
                userInfoResultBean.setCustomLabel(userInfoBean.getCustomLabel());
                userInfoResultBean.setInvite_selfcode(userInfoBean.getInvite_selfcode());
                userInfoResultBean.setTeachPic(userInfoBean.getTeachPic());
                userInfoResultBean.setAccid(userInfoBean.getAccid());
                userInfoResultBean.setImToken(userInfoBean.getImToken());
                userInfoResultBean.setImName(userInfoBean.getImName());
                userInfoResultBean.setIsProxyed(userInfoBean.getIsProxyed());
                userInfoResultBean.setType(userInfoBean.getType());
            } else {
                // 激活账户
                String user_exist_path = Config.getString("user.exist") + login.getTelephone();
                String user_exist_response = ServerUtil.httpRest(ServerConfig.USER_SERVER, user_exist_path, null, null, "GET");
                if (user_exist_response.contains("false")) {
                    log.info(" 账户：" + login.getTelephone() + "注册时失败，激活账户!");
                    ActivationUserRequest arequest = new ActivationUserRequest();
                    arequest.setPhone(login.getTelephone());
                    arequest.setPassword(login.getPassword());
                    UserModel auserModel = null;
                    try {
                        ActivationUserResponse active_response = client.execute(arequest);
                        auserModel = GsonUtil.fromObjectJson(JSON.toJSONString(active_response), "result", "userModel", UserModel.class);
                        log.info(auserModel.getAccessKey() + "  " + auserModel.getSecretKey() + "  " + auserModel.getOpenId());
                        log.info(active_response.toString());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    String register_path = Config.getString("user.register");
                    UserRegisterBean userRegisterBean = new UserRegisterBean();
                    userRegisterBean.setUsername(login.getTelephone());
                    userRegisterBean.setInvite_code("");
                    String selfcode = Digest.md5Digest(login.getTelephone()+new Date().getTime());
                    userRegisterBean.setInvite_selfcode(selfcode);
                    userRegisterBean.setVersion("1.0");
                    userRegisterBean.setAk(auserModel.getAccessKey());
                    userRegisterBean.setSk(auserModel.getSecretKey());
                    userRegisterBean.setUserOpenId(auserModel.getOpenId());
                    try {
                        //注册用户
                        String register_response = ServerUtil.httpRest(ServerConfig.USER_SERVER, register_path, null, userRegisterBean, "POST");
                        ResultBean  user_result = JSON.parseObject(register_response, ResultBean.class);
                        if (user_result.getCode() == 200) {
                            log.info("正确信息： " + user_result.getResult().toString());
                            userInfoResultBean.setInvite_selfcode(selfcode);
                            //注册环信
                            registerEaseob(auserModel.getOpenId(), login.getTelephone(), login.getPassword());
                        } else {
                            log.info("错误信息： " + user_result.getMessage());
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            EaseObUserInfoBean euser = userGetEaseobByOpenId(userInfoBean.getUserOpenId());
            userInfoResultBean.setEaseobPassword(euser.getPassword());
            userInfoResultBean.setEaseobUserName(euser.getUsername());
            resultBean.setSucResult(userInfoResultBean);
            //缓存登录信息
            TokenUtil.putToken(userInfoResultBean.getOpenId(), userInfoResultBean.getToken(), login.getClient_id());

            //保分计划部分保存登录信息保存
            try {
                TokenUtil.putToken(userInfoResultBean.getOpenId(), userInfoResultBean.getToken(), login.getClient_id(),
                        ServerConfig.KEEPMARK_SERVER, Config.getString("stuLogin"));
            } catch (Exception e) {
                log.error("调用保分计划部分保存登录信息保存出错："+e.getMessage());
            }
        } else {
            resultBean.setFailMsg(SystemStatus.USERNAME_PASSWORD_IS_ERROR);
        }
        return resultBean;
    }

    private EaseObUserInfoBean userGetEaseobByOpenId(String openId){
        EaseObUserInfoBean u = new EaseObUserInfoBean();
        String  path = Config.getString("solution.userGetEaseobByOpenId")+openId;
        try {
            String response = ServerUtil.httpRest(ServerConfig.SOLUTION_SERVER, path, null, null, "GET");
            ResultBean r =  JSON.parseObject(response,ResultBean.class);
            if(r.getCode()==200){
                log.info("正确信息： "+r.getResult());
                u = JSON.parseObject(JSON.toJSONString(r.getResult()), EaseObUserInfoBean.class);
            }else{
                log.info("错误信息： " + r.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    /**
     *  处理其他服务器返回结果
     */
    private ResultBean dealResult(String response){
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

    @Override
    public ResultBean findteacher(int pageNo, int pageSize, String gradeCode, String subjectCode, String orderType, String orders) {
        String path = Config.getString("user.findteacher") + "pageNo=" + pageNo + "&pageSize=" + pageSize + "&orders="+orders+
                (StringUtil.isEmpty(orderType)?"":("&orderType=" + orderType))+ (StringUtil.isEmpty(gradeCode)?"":("&gradeCode=" + gradeCode))
                + (StringUtil.isEmpty(subjectCode)?"":("&subjectCode=" + subjectCode));
        String response = ServerUtil.httpRest(ServerConfig.USER_SERVER, path, null, null, "GET");
        return dealResult(response);
    }

    @Override
    public ResultBean findUserInfo(String openId) {
        String path = Config.getString("user.finduserinfo") + "userOpenId=" + openId;
        String response = ServerUtil.httpRest(ServerConfig.USER_SERVER, path, null, null, "GET");
        return dealResult(response);
    }

    @Override
    public ResultBean getPermissionInfo(String openId) {
        String path = Config.getString("user.getPermissionInfo") + "userOpenId=" + openId;
        String response = ServerUtil.httpRest(ServerConfig.USER_SERVER, path, null, null, "GET");
        return dealResult(response);
    }

    @Override
    public ResultBean applySolutionPermission(String subjectCode, String stageCode) {
        String path = Config.getString("user.applysolutionpermission");
        ApplySolutionPermissionBean applySolutionPermission = new ApplySolutionPermissionBean(subjectCode,stageCode);
        String response = ServerUtil.httpRest(ServerConfig.TEACH_SERVER, path, null, applySolutionPermission, "POST");
        return dealResult(response);
    }

    @Override
    public ResultBean applyStatusBean(String openId) {
        String path = Config.getString("user.applyStatusBean") + "userOpenId=" + openId;
        String response = ServerUtil.httpRest(ServerConfig.USER_SERVER, path, null, null, "GET");
        return dealResult(response);
    }

    @Override
    public ResultBean updatePass(String token, String openId, UpdatePasswordBean updatePassBean) {

    }


}
