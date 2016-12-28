package com.yijiajiao.server.bean.wares;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-11-15-11:21
 */
public class BackUrlListParamBean {
    private String mtgKey;
    private String pageNo;
    private String pageSize;
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getMtgKey() {
        return mtgKey;
    }

    public void setMtgKey(String mtgKey) {
        this.mtgKey = mtgKey;
    }

    @Override
    public String toString() {
        return "BackUrlListParamBean{" +
                "mtgKey='" + mtgKey + '\'' +
                ", pageNo='" + pageNo + '\'' +
                ", pageSize='" + pageSize + '\'' +
                '}';
    }
}
