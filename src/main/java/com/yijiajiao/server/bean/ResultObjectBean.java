package com.yijiajiao.server.bean;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-11-18-14:23
 */
public class ResultObjectBean {
    private int status;
    private String message;

    public ResultObjectBean() {
    }

    public ResultObjectBean(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
