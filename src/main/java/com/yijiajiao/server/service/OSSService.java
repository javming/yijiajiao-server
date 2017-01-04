package com.yijiajiao.server.service;

import com.yijiajiao.server.bean.BaofenUserBean;
import com.yijiajiao.server.bean.ResultBean;

public interface OSSService {

    /**
     * 前端首页展示焦点图
     */
    ResultBean frontfocus(int belongs, int area);

    /**
     * 前端首页展示排行榜
     */
    ResultBean showbill(int area);

    /**
     * 前端首页展示商品和名师推荐
     */
    ResultBean showgoods(int belongs, int area);

    /**
     * 学生端首页展示名师推荐
     */
    ResultBean recommendTeacher(int belongs, int area);

    /**
     *	查询积分规则
     */
    ResultBean integralRuleList(int pageNo, int pageSize);

    /**
     *	保分计划用户登记
     */
    ResultBean baofenUserAdd(BaofenUserBean baoUserBean);

    /**
     *  增加热搜词
     */
    void hotSearch(String searchName,int type);
}
