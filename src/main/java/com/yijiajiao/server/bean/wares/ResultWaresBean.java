package com.yijiajiao.server.bean.wares;

/**
 * Created by ruyage on 2015/12/5.
 */
public class ResultWaresBean {
    private String id;
    private String teacherId;
    private String teacherName;
    private String curriculumName;
    private int curriculumType;
    private int popularity;
    private String curriculumInfo;
    private String subjectCode;
    private String gradeCode;
    private String bookTypeCode;
    private String upTime;
    private double price;
    private int isDiscount;
    private double priceDiscount;
    private String cover;
    private String downTime;
    private String status;
    private String sales;
    private String startTime;
    private String endTime;
    private String isYjj;
    private String curriculumInfoAudio;
    private String curriculumInfoVideo;
    private String curriculumInfoPic;

    public String getCurriculumInfoAudio() {
        return curriculumInfoAudio;
    }

    public void setCurriculumInfoAudio(String curriculumInfoAudio) {
        this.curriculumInfoAudio = curriculumInfoAudio;
    }

    public String getCurriculumInfoVideo() {
        return curriculumInfoVideo;
    }

    public void setCurriculumInfoVideo(String curriculumInfoVideo) {
        this.curriculumInfoVideo = curriculumInfoVideo;
    }

    public String getCurriculumInfoPic() {
        return curriculumInfoPic;
    }

    public void setCurriculumInfoPic(String curriculumInfoPic) {
        this.curriculumInfoPic = curriculumInfoPic;
    }

    public String getIsYjj() {
		return isYjj;
	}

	public void setIsYjj(String isYjj) {
		this.isYjj = isYjj;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDownTime() {
		return downTime;
	}

	public void setDownTime(String downTime) {
		this.downTime = downTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public String getUpTime() {
		return upTime;
	}

	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }


    public String getTeacherName() {
      return teacherName;
    }

    public void setTeacherName(String teacherName) {
      this.teacherName = teacherName;
    }

    public String getCurriculumName() {
        return curriculumName;
    }

    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }

    public int getCurriculumType() {
        return curriculumType;
    }

    public void setCurriculumType(int curriculumType) {
        this.curriculumType = curriculumType;
    }

    public String getCurriculumInfo() {
        return curriculumInfo;
    }

    public void setCurriculumInfo(String curriculumInfo) {
        this.curriculumInfo = curriculumInfo;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getBookTypeCode() {
        return bookTypeCode;
    }

    public void setBookTypeCode(String bookTypeCode) {
        this.bookTypeCode = bookTypeCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

  

    public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public int getIsDiscount() {
      return isDiscount;
    }

    public void setIsDiscount(int isDiscount) {
      this.isDiscount = isDiscount;
    }

    public double getPriceDiscount() {
      return priceDiscount;
    }

    public void setPriceDiscount(double priceDiscount) {
      this.priceDiscount = priceDiscount;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
