package com.yijiajiao.server.bean.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplySolutionPermissionBean {

    private String subjectCode;
    private String stageCode;

}
