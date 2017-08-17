package com.yijiajiao.server.bean.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CancelOrderBean {

    private int id;
    private String status;//calloff,delete
    private String cancelReason;
    private String cancelNeiRong;

}
