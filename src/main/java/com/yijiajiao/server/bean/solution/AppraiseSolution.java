package com.yijiajiao.server.bean.solution;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author  zhaoming@eduspace
 * @since  2017-07-14-13:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppraiseSolution {

    private Integer id;
    private String openId;
    private Integer solutionId;
    private int star; //星级 好：4、5；中：3；差：1、2
    private String content; // 内容
    private String reply; //老师回复
    private boolean isAnonymity; // 是不是匿名 false不是 true 是
    private Long createTime;

}
