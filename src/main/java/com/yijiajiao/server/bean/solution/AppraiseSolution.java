package com.yijiajiao.server.bean.solution;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-07-14-13:17
 */
public class AppraiseSolution {
    /**
     * 评价id
     */
    private Integer id;

    /**
     * 评价人，学生openId
     */
    private String openId;
    /**
     * 答疑id
     */
    private Integer solutionId;
    /**
     * 星级:
     * 好：4、5
     * 中：3
     * 差：1、2
     */
    private int star;
    /**
     * 评价内容
     */
    private String content;
    /**
     * 老师回复
     */
    private String reply;

    /**
     * false不是匿名，true 是匿名
     */
    private boolean isAnonymity;

    /**
     * 评价创建时间
     */
    private Long createTime;

    public AppraiseSolution() {
    }

    public AppraiseSolution(Integer id, String openId, Integer solutionId, int star, String content, String reply, boolean isAnonymity, Long createTime) {
        this.id = id;
        this.openId = openId;
        this.solutionId = solutionId;
        this.star = star;
        this.content = content;
        this.reply = reply;
        this.isAnonymity = isAnonymity;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public boolean isAnonymity() {
        return isAnonymity;
    }

    public void setAnonymity(boolean anonymity) {
        isAnonymity = anonymity;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

}
