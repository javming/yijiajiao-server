package com.yijiajiao.server.service;

import com.yijiajiao.server.bean.IdBean;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.post.SetMsgBean;


public interface MsgService {

    /**
     * 根据日期获取某个教师的课程表
     */
    ResultBean myMsgList(int msgGroup, int msgType, int isTeacher, int pageNo, int pageSize, String teacherId);

    /**
     *  获取个人未读信息数量，按msgType
     */
    ResultBean count(String openId, int isTeacher);

    /**
     * 获取个人未读信息数量，按msgGroup
     */
    ResultBean countByType(int msgType, String openId, int isTeacher);

    /**
     * 标记已读
     */
    ResultBean setRead(IdBean ids);

    /**
     * 根据Id删除消息
     */
    ResultBean setDel(String openId, int id, int msgType);

    /**
     * 移动端消息模块最新一条
     */
    ResultBean myNewMsg(String openId, int isTeacher);

    /**
     * 移动端获取个人消息设置
     */
    ResultBean getMsgSet(String openId, int isTeacher);

    /**
     *	获取未读站内信数量
     */
    ResultBean getMsgStation(String userOpenId, int isTeacher);

    ResultBean setMsg(String tag, SetMsgBean setMsgBean);
}
