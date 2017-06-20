package com.yijiajiao.server.bean;

/**
 * 第三方登录实体
 *
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-12-30-15:35
 */
public class ThirdPartyLoginBean {

    private String telephone;
    private String password;
    private String client_id;
    private String version;// 1.0 亿家教自用户  2.0阳光保险用户 3.0中兴用户
    private String thirdPartyUserCode;//第三方用户code
    public ThirdPartyLoginBean() {
    }

    public ThirdPartyLoginBean(String telephone, String password, String client_id, String version, String thirdPartyUserCode) {
        this.telephone = telephone;
        this.password = password;
        this.client_id = client_id;
        this.version = version;
        this.thirdPartyUserCode = thirdPartyUserCode;
    }
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getThirdPartyUserCode() {
        return thirdPartyUserCode;
    }

    public void setThirdPartyUserCode(String thirdPartyUserCode) {
        this.thirdPartyUserCode = thirdPartyUserCode;
    }
}
