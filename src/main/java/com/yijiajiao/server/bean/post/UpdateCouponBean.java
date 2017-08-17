package com.yijiajiao.server.bean.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author  zhaoming
 * @since  2017-01-09-11:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCouponBean {

    private int id; //代金券id
    private int addAmount; //增加开通的数量
    private String endDate; //代金券结束时间

}
