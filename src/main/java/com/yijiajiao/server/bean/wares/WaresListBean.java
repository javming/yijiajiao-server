package com.yijiajiao.server.bean.wares;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaresListBean {
  
  
    private long total;
    private long pages;
    private long pageNo;
    private long pageSize;
    private List<WaresBean> list = new ArrayList<WaresBean>();

}
