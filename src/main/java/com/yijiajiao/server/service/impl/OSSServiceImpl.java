package com.yijiajiao.server.service.impl;

import com.yijiajiao.server.bean.BaofenUserBean;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.post.FeedBackBean;
import com.yijiajiao.server.service.OSSService;
import com.yijiajiao.server.util.Config;
import com.yijiajiao.server.util.ServerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.yijiajiao.server.util.ServerUtil.*;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-04-15:01
 */

@Service("ossService")
public class OSSServiceImpl implements OSSService {

    private static final Logger log = LoggerFactory.getLogger(OSSServiceImpl.class);

    @Override
    public ResultBean frontfocus(int belongs, int area) {
        String path = Config.getString("oss.showfocus")+belongs+"/"+area;
        String response = ServerUtil.httpRest(OSS_SERVER,path,null,null,"GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean showbill(int area) {
        String path =Config.getString("oss.showbill")+area;
        String response = ServerUtil.httpRest(OSS_SERVER,path,null,null,"GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean showgoods(int belongs, int area) {
        String path =Config.getString("oss.showgoods")+belongs+"/"+area;
        String response = ServerUtil.httpRest(OSS_SERVER,path,null,null,"GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean recommendTeacher(int belongs, int area) {
        String path =Config.getString("oss.recommendTeacher")+belongs+"/"+area;
        String response = ServerUtil.httpRest(OSS_SERVER,path,null,null,"GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean integralRuleList(int pageNo, int pageSize) {
        String path =Config.getString("oss.integralRuleList")+"pageNo="+pageNo+"&pageSize="+pageSize;
        String response = ServerUtil.httpRest(OSS_SERVER,path,null,null,"GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean baofenUserAdd(BaofenUserBean baoUserBean) {
        String path =Config.getString("customer.baofenUserAdd");
        String response = ServerUtil.httpRest(CUSTOMER_SERVER,path,null,baoUserBean,"POST");
        return dealResult(log,response);
    }

    @Override
    public void hotSearch(String searchName, int type) {
        String path =Config.getString("oss.hotSearch")+"searchName="+searchName+"&type="+type;
        String response = ServerUtil.httpRest(OSS_SERVER,path,null,null,"GET");
        dealResult(log,response);
    }

    @Override
    public ResultBean feedBack(String tag, FeedBackBean feedBackBean) {
        String path =Config.getString("oss.feedBack");
        String response = ServerUtil.httpRest(OSS_SERVER,path,null,feedBackBean,"POST");
        return dealResult(log,response);
    }
}
