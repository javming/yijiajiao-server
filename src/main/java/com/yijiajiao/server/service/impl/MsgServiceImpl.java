package com.yijiajiao.server.service.impl;

import com.yijiajiao.server.bean.IdBean;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.post.SetMsgBean;
import com.yijiajiao.server.service.BaseService;
import com.yijiajiao.server.service.MsgService;
import com.yijiajiao.server.util.Config;
import com.yijiajiao.server.util.ServerUtil;
import net.rubyeye.xmemcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-04-13:48
 */
@Service("msgService")
public class MsgServiceImpl extends BaseService implements MsgService{

    private static final Logger log = LoggerFactory.getLogger(MsgServiceImpl.class);
    @Autowired
    private MemcachedClient memcachedClient;


    @Override
    public ResultBean myMsgList(int msgGroup, int msgType, int isTeacher, int pageNo, int pageSize, String teacherId) {
        String path = Config.getString("msg.mymsglist") + "pageNo=" + pageNo + "&pageSize=" + pageSize +"&isTeacher="+isTeacher+ "&msgType=" + msgType+ "&msgGroup=" + msgGroup + "&openId=" + teacherId ;
        String response = ServerUtil.httpRest(MSG_SERVER,path,null,null,"GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean count(String openId, int isTeacher) {
        String path =Config.getString("msg.count") + "openId=" + openId +"&isTeacher="+isTeacher;
        String response = ServerUtil.httpRest(MSG_SERVER,path,null,null,"GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean countByType(int msgType, String openId, int isTeacher) {
        String path =Config.getString("msg.countByType") + "msgType=" + msgType+  "&openId=" + openId +"&isTeacher="+isTeacher;
        String response = ServerUtil.httpRest(MSG_SERVER,path,null,null,"GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean setRead(IdBean ids) {
        String path =Config.getString("msg.setRead");
        String response = ServerUtil.httpRest(MSG_SERVER,path,null,ids,"PUT");
        return dealResult(log,response);
    }

    @Override
    public ResultBean setDel(String openId, int id, int msgType) {
        String path =Config.getString("msg.setDel")+ "id=" + id+  "&openId=" + openId +"&msgType="+msgType;
        String response = ServerUtil.httpRest(MSG_SERVER,path,null,null,"GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean myNewMsg(String openId, int isTeacher) {
        String path =Config.getString("msg.myNewMsg")+ "openId=" + openId +"&isTeacher="+isTeacher;
        String response = ServerUtil.httpRest(MSG_SERVER,path,null,null,"GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getMsgSet(String openId, int isTeacher) {
        String path =Config.getString("msg.getMsgSet")+ "openId=" + openId + "&isTeacher=" + isTeacher ;
        String response = ServerUtil.httpRest(MSG_SERVER,path,null,null,"GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getMsgStation(String openId, int isTeacher) {
        String path =Config.getString("msg.getMsgStation")+ "openId=" + openId + "&isTeacher=" + isTeacher ;
        String response = ServerUtil.httpRest(MSG_SERVER,path,null,null,"GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean setMsg(String tag, SetMsgBean setMsgBean) {
        String path =Config.getString("msg.setMsg");
        String response = ServerUtil.httpRest(MSG_SERVER,path,null,setMsgBean,"POST");
        if (IF_MEM==1) setMemcached(tag,response,this.memcachedClient,log);
        return dealResult(log,response);
    }
}
