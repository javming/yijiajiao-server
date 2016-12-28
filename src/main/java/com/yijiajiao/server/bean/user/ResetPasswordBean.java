package com.yijiajiao.server.bean.user;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-11-17-18:47
 */
public class ResetPasswordBean {
    private String phoneNum;
    private String password;
    private String phoneCode;

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ResetPasswordBean{" +
                "phoneNum='" + phoneNum + '\'' +
                ", password='" + password + '\'' +
                ", phoneCode='" + phoneCode + '\'' +
                '}';
    }
}
