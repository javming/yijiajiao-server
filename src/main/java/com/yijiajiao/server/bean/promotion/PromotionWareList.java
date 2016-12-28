package com.yijiajiao.server.bean.promotion;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-12-22-20:21
 */
public class PromotionWareList {
    private int total;
    private int pages;
    private int pageNo;
    private int pageSize;
    private List<PromotionWare> list = new ArrayList<PromotionWare>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<PromotionWare> getList() {
        return list;
    }

    public void setList(List<PromotionWare> list) {
        this.list = list;
    }
}
