package com.yijiajiao.server.bean.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyStudentsBean {

    private String teacherId;
    private int  pageNo;
    private int  pageSize;
    private int  curriculumType;

}
