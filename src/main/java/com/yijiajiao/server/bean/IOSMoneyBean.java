package com.yijiajiao.server.bean;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-02-14-17:06
 */
public class IOSMoneyBean {

    private String openId;
    private double money;
    private String transactionId;
    private String receipt;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }
}
