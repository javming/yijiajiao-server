package com.yijiajiao.server.bean.sale;

public class CancelOrderBean {
    private int id;
    private String status;//calloff,delete
    private String cancelReason;
    private String cancelNeiRong;

    public CancelOrderBean() {}

    public CancelOrderBean(int id, String status, String cancelReason, String cancelNeiRong) {
        this.id = id;
        this.status = status;
        this.cancelReason = cancelReason;
        this.cancelNeiRong = cancelNeiRong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getCancelNeiRong() {
        return cancelNeiRong;
    }

    public void setCancelNeiRong(String cancelNeiRong) {
        this.cancelNeiRong = cancelNeiRong;
    }
}
