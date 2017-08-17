package com.yijiajiao.server.bean.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplyTeacherBean {

	private String userOpenId;
	private String name;
	private String sex;
	private String school;
	private String idCard;
	private String frontPic;
	private String backPic;
	private String idPic;
	private String phone;
	private String invite_code;
	private String subjectName;
	private String subjectCode;
	private String teachAge;
	private String teacher_grade;
	private String provinceCode;
	private String cityCode;
	private String areaCode;
	private int type;// 类型
	private List<GradeBean> grades;
	//享受国务院特殊津贴的专家
	private String allowancePic;
	// 持特级教师证的各学段专家
	private String specialTeachPic;
	//常年从事高考命题的专家
	private String propositionPic;
	//持高级教师资格证的教师
	private String seniorTeachPic;
	//持市、区级学科带头人或兼职教研员证的教师
	private String subjectLeaderPic;
	//市级（含）以上的教学技能大赛获奖的教师
	private String teachSkillPic;
	//有个人已出版的专著或个人特色课程的教师
	private String featureCoursePic;
	//教师资格证
	private String teachQualifyPic;
	//具有三年以上教育教学或培训相关经验者
	private String trainExperiencePic;
	//属于211、985类高校的本科（含）以上学历并有累计1年以上教育实习经验者
	private String famousEducationAndExperiencePic;
	//师范类院校中二等奖学金（含）以上获得者并有累计1年以上教育实习经验者
	private String normalCollegeAndExperiencePic;
	//在当年高考中至少一科是单科状元者
	private String subjectChampionPic;
	//属于211、985类高校的优秀大学生
	private String famousEducationPic;
	//师范类院校中二等奖学金（含）以上获得者并有累计1年以上教育实习经验者
	private String normalCollegePic;


}
