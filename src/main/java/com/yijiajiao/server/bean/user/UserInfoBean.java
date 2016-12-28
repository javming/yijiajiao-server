package com.yijiajiao.server.bean.user;


import java.util.List;

public class UserInfoBean { 
    private int id;
    //用户名
    private String username;
    //姓名
    private String name;
    //性别
    private String sex;
    //职称(教师)
    private String teacher_grade;
    //邮箱
    private String mail;
    //生日
    private String birthday;
    //年级

    //学校
    private String school;
    //认证时间
     private String authDate;
    //教龄（老师）
    private int teachAge;
    //描述
    private String description;
    //状态  (学生or老师)
    /**
     * 1:学生，2：学生，正在认证教师，3：认证教师
     */
    private int status_st;
    //状态（是否拉黑）
    /**
     *  1:未来黑，2：拉黑
     */

    private int state;
    //电话
    private String phone;
    //头像图片
    private String pic;
    //积分等级
    private String scoreGrade;
    //注册时间
    private String registDate;
    //最后登录时间
    private String lastDate;
    private List<GradeModel> gradeModel;


    private String provinceName;

    //视频授权的权限  1:有权限 0：无权限
    private int video_permission;
    private int facingTeachPermission=0;
    private int solutionpermission;

    private String checkDescription;
    //学段
    private String stageCode;
    private String stageName;
    
    //身份证号
    private String idCard;
    //身份证正面照片
    private String frontPic;
    //身份证反面照片
    private String backPic;
    //教师资格证书
    private String qualifyPic;
    //其他证书
    private String otherPic;
    //手持身份证照片
    private String idPic;
    private String subjectCode;
    private String subjectName;
    private String userOpenId;
    private String createTime;
    private String storePic;
    private String storeOpenId;
    private double storeScore;
    //当前人数
    private Integer count;
    private String provinceCode;
    private String cityCode;
    private String areaCode;
    private String cityName;
    private String areaName;
    //店铺名称
    private String storeName;
    //老师简介
    private String introduction;
    //店铺个性简介
    private String brief;
    //标签
    private String label;
    //自定义标签
    private String customLabel;
    //背景图片
    private String storeBackPic;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsProxyed() {
        return isProxyed;
    }

    public void setIsProxyed(int isProxyed) {
        this.isProxyed = isProxyed;
    }

    public String getTeachPic() {
        return teachPic;
    }

    public void setTeachPic(String teachPic) {
        this.teachPic = teachPic;
    }

    public String getInvite_code() {
      return invite_code;
    }
    public void setInvite_code(String invite_code) {
      this.invite_code = invite_code;
    }
    public String getInvite_selfcode() {
      return invite_selfcode;
    }
    public void setInvite_selfcode(String invite_selfcode) {
      this.invite_selfcode = invite_selfcode;
    }
    public int getId() {
      return id;
    }
    public void setId(int id) {
      this.id = id;
    }
    public String getUsername() {
      return username;
    }
    public void setUsername(String username) {
      this.username = username;
    }
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public String getSex() {
      return sex;
    }
    public void setSex(String sex) {
      this.sex = sex;
    }
    
    public int getSolutionpermission() {
      return solutionpermission;
    }
    public void setSolutionpermission(int solutionpermission) {
      this.solutionpermission = solutionpermission;
    }
    public String getTeacher_grade() {
      return teacher_grade;
    }
    public void setTeacher_grade(String teacher_grade) {
      this.teacher_grade = teacher_grade;
    }
    public String getMail() {
      return mail;
    }
    
    public String getBirthday() {
      return birthday;
    }
    public void setBirthday(String birthday) {
      this.birthday = birthday;
    }
    public String getSchool() {
      return school;
    }
    public void setSchool(String school) {
      this.school = school;
    }
    public String getAuthDate() {
      return authDate;
    }
    public void setAuthDate(String authDate) {
      this.authDate = authDate;
    }
    public int getTeachAge() {
      return teachAge;
    }
    public void setTeachAge(int teachAge) {
      this.teachAge = teachAge;
    }
    public String getDescription() {
      return description;
    }
    public void setDescription(String description) {
      this.description = description;
    }
    public int getStatus_st() {
      return status_st;
    }
    public void setStatus_st(int status_st) {
      this.status_st = status_st;
    }
    public int getState() {
      return state;
    }
    public void setState(int state) {
      this.state = state;
    }
    public String getPhone() {
      return phone;
    }
    public void setPhone(String phone) {
      this.phone = phone;
    }
    public String getPic() {
      return pic;
    }
    public void setPic(String pic) {
      this.pic = pic;
    }
    public String getScoreGrade() {
      return scoreGrade;
    }
    public void setScoreGrade(String scoreGrade) {
      this.scoreGrade = scoreGrade;
    }
    public String getRegistDate() {
      return registDate;
    }
    public void setRegistDate(String registDate) {
      this.registDate = registDate;
    }
    public String getLastDate() {
      return lastDate;
    }
    public void setLastDate(String lastDate) {
      this.lastDate = lastDate;
    }
    public List<GradeModel> getGradeModel() {
      return gradeModel;
    }
    public void setGradeModel(List<GradeModel> gradeModel) {
      this.gradeModel = gradeModel;
    }
    public String getProvinceName() {
      return provinceName;
    }
    public void setProvinceName(String provinceName) {
      this.provinceName = provinceName;
    }
    public int getVideo_permission() {
      return video_permission;
    }
    public void setVideo_permission(int video_permission) {
      this.video_permission = video_permission;
    }
    public int getFacingTeachPermission() {
      return facingTeachPermission;
    }
    public void setFacingTeachPermission(int facingTeachPermission) {
      this.facingTeachPermission = facingTeachPermission;
    }
    public String getCheckDescription() {
      return checkDescription;
    }
    public void setCheckDescription(String checkDescription) {
      this.checkDescription = checkDescription;
    }
    public String getStageCode() {
      return stageCode;
    }
    public void setStageCode(String stageCode) {
      this.stageCode = stageCode;
    }
    public String getStageName() {
      return stageName;
    }
    public void setStageName(String stageName) {
      this.stageName = stageName;
    }
    public String getIdCard() {
      return idCard;
    }
    public void setIdCard(String idCard) {
      this.idCard = idCard;
    }
    public String getFrontPic() {
      return frontPic;
    }
    public void setFrontPic(String frontPic) {
      this.frontPic = frontPic;
    }
    public String getBackPic() {
      return backPic;
    }
    public void setBackPic(String backPic) {
      this.backPic = backPic;
    }
    public String getQualifyPic() {
      return qualifyPic;
    }
    public void setQualifyPic(String qualifyPic) {
      this.qualifyPic = qualifyPic;
    }
    public String getOtherPic() {
      return otherPic;
    }
    public void setOtherPic(String otherPic) {
      this.otherPic = otherPic;
    }
    public String getIdPic() {
      return idPic;
    }
    public void setIdPic(String idPic) {
      this.idPic = idPic;
    }
    public String getSubjectCode() {
      return subjectCode;
    }
    public void setSubjectCode(String subjectCode) {
      this.subjectCode = subjectCode;
    }
    public String getSubjectName() {
      return subjectName;
    }
    public void setSubjectName(String subjectName) {
      this.subjectName = subjectName;
    }
    public String getUserOpenId() {
      return userOpenId;
    }
    public void setUserOpenId(String userOpenId) {
      this.userOpenId = userOpenId;
    }
    public String getCreateTime() {
      return createTime;
    }
    public void setCreateTime(String createTime) {
      this.createTime = createTime;
    }
    public String getStorePic() {
      return storePic;
    }
    public void setMail(String mail) {
      this.mail = mail;
    }
    public void setStorePic(String storePic) {
      this.storePic = storePic;
    }
    public String getStoreOpenId() {
      return storeOpenId;
    }
    public void setStoreOpenId(String storeOpenId) {
      this.storeOpenId = storeOpenId;
    }
    public double getStoreScore() {
      return storeScore;
    }
    public void setStoreScore(double storeScore) {
      this.storeScore = storeScore;
    }
    public Integer getCount() {
      return count;
    }
    public void setCount(Integer count) {
      this.count = count;
    }
    public String getProvinceCode() {
      return provinceCode;
    }
    public void setProvinceCode(String provinceCode) {
      this.provinceCode = provinceCode;
    }
    public String getCityCode() {
      return cityCode;
    }
    public void setCityCode(String cityCode) {
      this.cityCode = cityCode;
    }
    public String getAreaCode() {
      return areaCode;
    }
    public void setAreaCode(String areaCode) {
      this.areaCode = areaCode;
    }
    public String getCityName() {
      return cityName;
    }
    public void setCityName(String cityName) {
      this.cityName = cityName;
    }
    public String getAreaName() {
      return areaName;
    }
    public void setAreaName(String areaName) {
      this.areaName = areaName;
    }
    public String getStoreName() {
      return storeName;
    }
    public void setStoreName(String storeName) {
      this.storeName = storeName;
    }
    public String getIntroduction() {
      return introduction;
    }
    public void setIntroduction(String introduction) {
      this.introduction = introduction;
    }
    public String getBrief() {
      return brief;
    }
    public void setBrief(String brief) {
      this.brief = brief;
    }
    public String getLabel() {
      return label;
    }
    public void setLabel(String label) {
      this.label = label;
    }
    public String getCustomLabel() {
      return customLabel;
    }
    public void setCustomLabel(String customLabel) {
      this.customLabel = customLabel;
    }
    public String getStoreBackPic() {
      return storeBackPic;
    }
    public void setStoreBackPic(String storeBackPic) {
      this.storeBackPic = storeBackPic;
    }
    public String getParentName() {
      return parentName;
    }
    public void setParentName(String parentName) {
      this.parentName = parentName;
    }
    public String getParentPhone() {
      return parentPhone;
    }
    public void setParentPhone(String parentPhone) {
      this.parentPhone = parentPhone;
    }
    public String getAddress() {
      return address;
    }
    public void setAddress(String address) {
      this.address = address;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getImToken() {
        return imToken;
    }

    public void setImToken(String imToken) {
        this.imToken = imToken;
    }

    public String getImName() {
        return imName;
    }

    public void setImName(String imName) {
        this.imName = imName;
    }
}