package com.yijiajiao.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.user.EasyUserListBean;
import com.yijiajiao.server.bean.user.IdsBean;
import com.yijiajiao.server.bean.wares.WaresBean;
import com.yijiajiao.server.bean.wares.WaresListBean;
import com.yijiajiao.server.service.BaseDataService;
import com.yijiajiao.server.service.BaseService;
import com.yijiajiao.server.service.UserService;
import com.yijiajiao.server.util.Config;
import com.yijiajiao.server.util.ServerUtil;
import com.yijiajiao.server.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-05-10:21
 */

@Service("baseDataService")
public class BaseDataServiceImpl extends BaseService implements BaseDataService {

    private static final Logger log = LoggerFactory.getLogger(BaseDataServiceImpl.class);
    @Autowired
    private UserService userService;

    @Override
    public ResultBean knowledges(String subjectCode, String gradeCode, String bookTypeCode) {

        String path = Config.getString("wares.knowledges") + "subjectCode=" + subjectCode + "&gradeCode=" + gradeCode
                                    + "&bookTypeCode=" + bookTypeCode;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean questionByKnowledge(String subject, String grade, String knowledge, int pageNo, int pageSize) {
        String path = Config.getString("wares.questionByKnowledge") + "subject=" + subject + "&grade=" + grade
                                    + "&knowledge=" + knowledge+ "&pageNo=" + pageNo+ "&pageSize=" + pageSize;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean examList(String openId,String gradeName,String subjectName,String bookTypeName,int examType,
                               int pageNo,int pageSize) {
        String path = Config.getString("ware.ExamList")+"teacherId="+openId+"&examType="+examType+"&pageNo="+pageNo
                                +"&pageSize="+pageSize+(StringUtil.isEmpty(gradeName)?"":("&gradeName="+gradeName))
                                +(StringUtil.isEmpty(subjectName)?"":("&subjectName="+subjectName))
                                +(StringUtil.isEmpty(bookTypeName)?"":("&bookTypeName="+bookTypeName));
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean searchTeacher(int pageNo, int pageSize, String order, String orderType, String keyword) {
        String path = Config.getString("user.searchteacherbyname")+"pageNo="+pageNo+"&pageSize="+pageSize
                                + "&orderType="+ (StringUtil.isEmpty(order)?"popularity":order)
                                +(StringUtil.isEmpty(keyword)?"":("&name=" +keyword))
                                +(StringUtil.isEmpty(orderType)?"":("&orders="+orderType));
        String response = ServerUtil.httpRest(USER_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean searchWares(int pageNo, int pageSize, String subjectCode, String gradeCode, String bookTypeCode,
                                  Integer curriculumType, String keyword, String order, String orderType, String reservePrice,
                                  String peakPrice) {
        ResultBean result = new ResultBean();
        String path = Config.getString("wares.wareslist") + "pageNo=" + pageNo + "&pageSize=" + pageSize
                                    + (curriculumType==null?"":("&curriculumType="+curriculumType) )
                                    + (StringUtil.isEmpty(subjectCode)?"":("&subjectCode=" + subjectCode))
                                    + (StringUtil.isEmpty(gradeCode)?"":("&gradeCode=" + gradeCode))
                                    + (StringUtil.isEmpty(bookTypeCode)?"":("&bookTypeCode=" + bookTypeCode))
                                    + (StringUtil.isEmpty(keyword)?"":("&curriculumName="+keyword))
                                    + (StringUtil.isEmpty(order)?"":("&order=" + order))
                                    + (StringUtil.isEmpty(orderType)?"":("&orderType=" + orderType))
                                    + (StringUtil.isEmpty(reservePrice)?"":("&reservePrice="+reservePrice))
                                    + (StringUtil.isEmpty(peakPrice)?"":("&peakPrice="+peakPrice));
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
                EasyUserListBean list_user = (EasyUserListBean) ur.getResult();
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
    public ResultBean nowledgeByGradeSubjectBooktype(String gradeCode, String subjectCode, String bookTypeCode) {
        String path = Config.getString("wares.nowledgeTree") + "gradeCode="+(gradeCode==null?"":gradeCode)
                +(StringUtil.isEmpty(subjectCode)?"":("&subjectCode="+subjectCode))
                +(StringUtil.isEmpty(bookTypeCode)?"":("&bookTypeCode="+bookTypeCode));
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }


}
