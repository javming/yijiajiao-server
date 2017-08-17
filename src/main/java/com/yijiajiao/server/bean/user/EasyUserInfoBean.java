package com.yijiajiao.server.bean.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EasyUserInfoBean {

    private String username;
    private String name;
    private String provinceCode;
    private String cityCode;
    private String areaCode;
    private String provinceName;
    private String cityName;
    private String areaName;
    private String storeScore;
    private String storePic;
    private String teachPic;
    private String label;
    private String customLabel;
    private String introduction;
    private String brief;
    private String userOpenId;
    private String storeName;
    private String video_permission;
    private String facingReachPermission;
    private String pic;

}
