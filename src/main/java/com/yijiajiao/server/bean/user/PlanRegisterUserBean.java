package com.yijiajiao.server.bean.user;

/**
 * Created by ruyage on 2016/8/29.
 */
public class PlanRegisterUserBean {
    private String accessKey;
    private String secretKey;
    private String openId;
    private String accd;
    private String imToken;
    private String imName;
    private String easeobName;
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAccd() {
        return accd;
    }

    public void setAccd(String accd) {
        this.accd = accd;
    }

    public String getImToken() {
        return imToken;
    }

    public void setImToken(String imToken) {
        this.imToken = imToken;
    }

    public String getImName() {
        return imName;
    }

    public void setImName(String imName) {
        this.imName = imName;
    }

    public String getEaseobName() {
        return easeobName;
    }

    public void setEaseobName(String easeobName) {
        this.easeobName = easeobName;
    }
}
