package com.yijiajiao.server.bean.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StuAppraiseQueryBean {

    private String openId;
    private int pageNo = 1;
    private int pageSize;
    private String appraiseType;
    private String curriculumType;

}
