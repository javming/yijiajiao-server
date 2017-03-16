package com.yijiajiao.server.bean;

import java.util.List;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-02-07-13:32
 */
public class SyncOrderInfo {
    private String userCode; //用户code
    private String orderNum; //订单号
    private String commodityId; // 商品id
    private double orderPrice; // 订单价格
    private int commodityType;// 商品类型:(1课程,2答疑,3诊断,4诊断）
    private String teacherId; //教师用户id（商品为诊断时可不传）
    private String diagnosisGoodsDetailCode;//诊断商品code
    private String diagnosisGoodsCode;//诊断商品code
    private String multiPaperCode;//综合试卷code
    private String diagnosticRecordsName;//诊断商品名称
    private List<SlaveIdBean> slaves;
    private String examStartDate;
    private String examEndDate;
    private int orderSource;// 订单来源：0易家教 1阳光 2 5184(网站)
    private String used;//(保分计划诊断)
    private Double discountPrice;//折扣后价格
    private String discountYard;// 优惠码
    private String price;//原价

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(int commodityType) {
        this.commodityType = commodityType;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getDiagnosisGoodsDetailCode() {
        return diagnosisGoodsDetailCode;
    }

    public void setDiagnosisGoodsDetailCode(String diagnosisGoodsDetailCode) {
        this.diagnosisGoodsDetailCode = diagnosisGoodsDetailCode;
    }

    public String getDiagnosisGoodsCode() {
        return diagnosisGoodsCode;
    }

    public void setDiagnosisGoodsCode(String diagnosisGoodsCode) {
        this.diagnosisGoodsCode = diagnosisGoodsCode;
    }

    public String getMultiPaperCode() {
        return multiPaperCode;
    }

    public void setMultiPaperCode(String multiPaperCode) {
        this.multiPaperCode = multiPaperCode;
    }

    public String getDiagnosticRecordsName() {
        return diagnosticRecordsName;
    }

    public void setDiagnosticRecordsName(String diagnosticRecordsName) {
        this.diagnosticRecordsName = diagnosticRecordsName;
    }

    public List<SlaveIdBean> getSlaves() {
        return slaves;
    }

    public void setSlaves(List<SlaveIdBean> slaves) {
        this.slaves = slaves;
    }

    public String getExamStartDate() {
        return examStartDate;
    }

    public void setExamStartDate(String examStartDate) {
        this.examStartDate = examStartDate;
    }

    public String getExamEndDate() {
        return examEndDate;
    }

    public void setExamEndDate(String examEndDate) {
        this.examEndDate = examEndDate;
    }

    public int getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(int orderSource) {
        this.orderSource = orderSource;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDiscountYard() {
        return discountYard;
    }

    public void setDiscountYard(String discountYard) {
        this.discountYard = discountYard;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SyncOrderInfo{" +
                "userCode='" + userCode + '\'' +
                ", commodityId='" + commodityId + '\'' +
                ", orderPrice=" + orderPrice +
                ", commodityType=" + commodityType +
                ", teacherId='" + teacherId + '\'' +
                ", diagnosisGoodsDetailCode='" + diagnosisGoodsDetailCode + '\'' +
                ", diagnosisGoodsCode='" + diagnosisGoodsCode + '\'' +
                ", multiPaperCode='" + multiPaperCode + '\'' +
                ", diagnosticRecordsName='" + diagnosticRecordsName + '\'' +
                ", slaves=" + slaves +
                ", examStartDate='" + examStartDate + '\'' +
                ", examEndDate='" + examEndDate + '\'' +
                ", orderSource=" + orderSource +
                ", used='" + used + '\'' +
                ", discountPrice=" + discountPrice +
                ", discountYard='" + discountYard + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
