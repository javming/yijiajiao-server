package com.yijiajiao.server.bean.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoBean { 
    private int id;
    private String username;//用户名
    private String name;//姓名
    private String sex;//性别
    private String teacher_grade;//职称(教师)
    private String mail;//邮箱
    private String birthday;
    private String school;
    private String authDate;//认证时间
    private int teachAge; //教龄（老师）
    private String description;//描述
    private int status_st;//状态  (学生or老师)1:学生，2：学生，正在认证教师，3：认证教师
    private int state;    //状态（是否拉黑）1:未来黑，2：拉黑
    private String phone;
    private String pic; //头像图片
    private String scoreGrade;//积分等级
    private String registDate;
    private String lastDate;//最后登录时间
    private List<GradeModel> gradeModel;
    private String provinceName;
    private int video_permission;  //视频授权的权限  1:有权限 0：无权限
    private int facingTeachPermission;
    private int solutionpermission;
    private String checkDescription;
    private String stageCode;
    private String stageName;
    private String idCard;//身份证号
    private String frontPic;//身份证正面照片
    private String backPic;//身份证反面照片
    private String qualifyPic;//教师资格证书
    private String otherPic;//其他证书
    private String idPic;//手持身份证照片
    private String subjectCode;
    private String subjectName;
    private String userOpenId;
    private String createTime;
    private String storePic;
    private String storeOpenId;
    private double storeScore;
    private Integer count;//当前人数
    private String provinceCode;
    private String cityCode;
    private String areaCode;
    private String cityName;
    private String areaName;
    private String storeName;//店铺名称
    private String introduction;//老师简介
    private String brief;//店铺个性简介
    private String label;//标签
    private String customLabel;//自定义标签
    private String storeBackPic;//背景图片
    private String teachPic;
    private String parentName;
    private String parentPhone;
    private String address;
    private String invite_code;
    private String invite_selfcode;
    private String accid;
    private String imToken;
    private String imName;
    private int isProxyed;
    private int type;

}