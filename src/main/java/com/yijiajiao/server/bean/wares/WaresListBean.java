package com.yijiajiao.server.bean.wares;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruyage on 2015/12/5.
 */
public class WaresListBean {
  
  
    private long total;
    private long pages;
    private long pageNo;
    private long pageSize;

    private List<WaresBean> list = new ArrayList<WaresBean>();

    public List<WaresBean> getList() {
        return list;
    }

    public void setList(List<WaresBean> list) {
        this.list = list;
    }

    public long getTotal() {
      return total;
    }

    public void setTotal(long total) {
      this.total = total;
    }

    public long getPages() {
      return pages;
    }

    public void setPages(long pages) {
      this.pages = pages;
    }

    public long getPageNo() {
      return pageNo;
    }

    public void setPageNo(long pageNo) {
      this.pageNo = pageNo;
    }

    public long getPageSize() {
      return pageSize;
    }

    public void setPageSize(long pageSize) {
      this.pageSize = pageSize;
    }
    
    
}
