package com.yijiajiao.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.SystemStatus;
import com.yijiajiao.server.bean.sale.CheckTimeBean;
import com.yijiajiao.server.bean.user.EasyUserListBean;
import com.yijiajiao.server.bean.user.IdsBean;
import com.yijiajiao.server.bean.wares.WaresBean;
import com.yijiajiao.server.bean.wares.WaresListBean;
import com.yijiajiao.server.service.BaseService;
import com.yijiajiao.server.service.UserService;
import com.yijiajiao.server.service.WaresService;
import com.yijiajiao.server.util.Config;
import com.yijiajiao.server.util.ServerUtil;
import com.yijiajiao.server.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-12-15:06
 */
@Service("waresService")
public class WaresServiceImpl extends BaseService implements WaresService {

    private static final Logger log = LoggerFactory.getLogger(WaresServiceImpl.class);
    @Autowired
    private UserService userService;

    @Override
    public ResultBean timeTable(String startDate, String endDate, String openId) {
        String path = Config.getString("wares.timeTable") + "startDate=" + startDate + "&endDate=" + endDate
                + "&teacherId=" + openId;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getWaresByDate(String classDate, String waresType, String teacherId) {
        String path = Config.getString("wares.warelist") + "classDate=" + classDate + "&teacherId=" + teacherId
                +(StringUtil.isEmpty(waresType)?"":("&waresType=" + waresType));
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean wareslist(int pageNo, int pageSize, String subjectCode, String gradeCode, String bookTypeCode,
                                Integer curriculumType,String order,String orderType,String stageCode,String reservePrice,
                                String peakPrice,String smallCourseware,String teacherId,String status,String isYjj,String yjjCode) {
        String path = Config.getString("wares.wareslist") + "pageNo=" + pageNo + "&pageSize=" + pageSize
                +(StringUtil.isEmpty(subjectCode)?"":("&subjectCode="+subjectCode))+(StringUtil.isEmpty(gradeCode)?"":("&gradeCode="+gradeCode))
                +(StringUtil.isEmpty(bookTypeCode)?"":("&bookTypeCode="+bookTypeCode))+(StringUtil.isEmpty(order)?"":("&order=" + order))
                +(StringUtil.isEmpty(orderType)?"":("&orderType="+orderType))+(curriculumType!=null?("&curriculumType="+curriculumType):"")
                +(StringUtil.isEmpty(stageCode)?"":("&stageCode="+stageCode))+(StringUtil.isEmpty(reservePrice)?"":("&reservePrice="+reservePrice))
                +(StringUtil.isEmpty(peakPrice)?"":("&peakPrice="+peakPrice))+(StringUtil.isEmpty(status)?"":("&status="+status))
                +(StringUtil.isEmpty(smallCourseware)?"":("&smallCourseware="+smallCourseware))+(StringUtil.isEmpty(isYjj)?"":("&isYjj="+isYjj))
                +(StringUtil.isEmpty(yjjCode)?"":("&yjjCode="+yjjCode))+(StringUtil.isEmpty(teacherId)?"":("&teacherId="+teacherId));
        ResultBean result = new ResultBean();
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        ResultBean resultBean = JSON.parseObject(response, ResultBean.class);
        if (resultBean.getCode() == 200 ) {
            String waresInfo = JSON.toJSONString(resultBean.getResult());
            log.info("正确信息： " + waresInfo);
            WaresListBean waresListBean = JSON.parseObject(waresInfo, WaresListBean.class);
            if (waresListBean.getList().size() > 0) {
                StringBuffer stb = new StringBuffer("");
                for (WaresBean waresBean : waresListBean.getList()) {
                    stb.append(waresBean.getTeacherId() + ",");
                }
                IdsBean ids = new IdsBean(stb.toString().substring(0, stb.toString().length()));
                // 查询到教师的名称
                ResultBean ur = userService.finduserinfobyid(ids);
                EasyUserListBean list_user = JSON.parseObject(JSON.toJSONString(ur.getResult()),EasyUserListBean.class);
                for (int i = 0; i < waresListBean.getList().size(); i++) {
                    waresListBean.getList().get(i).setTeacherName(list_user.getList().get(i).getName());
                }
                result.setSucResult(waresListBean);
            }else{
                result.setSucResult(resultBean.getResult());
            }
        } else {
            log.info("错误信息： " + resultBean.getMessage());
            result.setFailMsg(resultBean.getCode(), resultBean.getMessage());
        }
        return result;

    }

    @Override
    public ResultBean waresInfoById(int id) {
        String path = Config.getString("wares.waresInfoById") + "waresId=" + id;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getViedoById(String openId, String gradeCode, String bookTypeCode, int useType) {
        String path = Config.getString("wares.getViedoById") + "teacherId=" + openId +"&useType="+useType
                            +(StringUtil.isEmpty(gradeCode)?"":("&gradeCode=" + gradeCode))
                            +(StringUtil.isEmpty(bookTypeCode)?"":("&bookTypeCode=" + bookTypeCode));
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getViedoByExamination(String openId, String gradeCode, String bookTypeCode, String examination,
                                            int pageNo, int pageSize) {
        String path = Config.getString("wares.getViedoByExamination")+"teacherId=" + openId+"&pageNo=" + pageNo
                        +"&pageSize=" + pageSize+(StringUtil.isEmpty(gradeCode)?"":("&gradeCode=" + gradeCode))
                        +(StringUtil.isEmpty(examination)?"":("&examination=" + examination))
                        +(StringUtil.isEmpty(bookTypeCode)?"":("&bookTypeCode=" + bookTypeCode));
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean checkTime(String openId, String startTime, String endTime) {
        ResultBean resultBean = new ResultBean();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long delayTime= Config.getInt("delayTime");
        try{
            if((simpleDateFormat.parse(startTime).getTime()-delayTime)<=new Date().getTime()){
                log.info("开始时间必须大于当前时间5分钟");
                return ResultBean.getSucResult(false);
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResultBean.getFailResult(SystemStatus.SERVER_ERROR);
        }
        String warepath = Config.getString("ware.checkTime") + "teacherId=" + openId + "&startTime="
                + startTime.replace(" ", "%20") + "&endTime=" + endTime.replace(" ", "%20");
        // 请求商品系统
        String response = ServerUtil.httpRest(WARES_SERVER, warepath, null, null, "GET");
        ResultBean r = JSON.parseObject(response, ResultBean.class);
        if (r.getCode() == 200) {
            log.info("正确信息： " + r.getResult().toString());
            String warecheck = r.getResult().toString();
            String salepath = Config.getString("sale.checkTime");
            CheckTimeBean checkTimeBean = new CheckTimeBean(openId,startTime,endTime);
            // 请求销售系统
            String sale_response = ServerUtil.httpRest(SALE_SERVER, salepath, null, checkTimeBean, "POST");
            ResultBean r1 = JSON.parseObject(sale_response, ResultBean.class);
            if (r1.getCode() == 200) {
                log.info("正确信息： " + r1.getResult().toString());
                String salecheck = r1.getResult().toString();
                if (salecheck.equals("true") && warecheck.equals("no exist")) {
                    resultBean.setSucResult(r1.getResult());
                } else {
                    resultBean.setSucResult(false);
                }
            } else {
                log.info("错误信息： " + r.getMessage());
                resultBean.setFailMsg(Integer.parseInt(r.getCode() + ""), r.getMessage());
            }
        } else {
            log.info("错误信息： " + r.getMessage());
            resultBean.setFailMsg(Integer.parseInt(r.getCode() + ""), r.getMessage());
        }
        return resultBean;
    }
}
