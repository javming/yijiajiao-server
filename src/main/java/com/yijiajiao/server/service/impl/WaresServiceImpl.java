package com.yijiajiao.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.SystemStatus;
import com.yijiajiao.server.bean.post.*;
import com.yijiajiao.server.bean.sale.CheckTimeBean;
import com.yijiajiao.server.bean.user.EasyUserListBean;
import com.yijiajiao.server.bean.user.IdsBean;
import com.yijiajiao.server.bean.wares.*;
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

import static com.yijiajiao.server.util.ServerUtil.*;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-12-15:06
 */
@Service("waresService")
public class WaresServiceImpl implements WaresService {

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
                IdsBean ids = new IdsBean(stb.toString().substring(0, stb.toString().length()-1));
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

    @Override
    public ResultBean teacherExamTest(String openId, String code) {
        String path = Config.getString("ware.teacherExamTest") + "code=" + code;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean countWaresByteacherId(String teacherId) {
        String path = Config.getString("wares.countWaresByteacherId") + "teacherId=" + teacherId;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean waresListByType(String teacherId, int curriculumType, Integer status, int pageNo, int pageSize, String isYjj) {
        String path = Config.getString("wares.waresListByType")+"teacherId="+teacherId+"&curriculumType="+curriculumType
                + "&status=" + status+"&pageNo="+pageNo+"&pageSize="+pageSize+ (StringUtil.isEmpty(isYjj)?"":("&isYjj="+isYjj));
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getStage() {
        String path = Config.getString("wares.getStage");
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getGradeByStage(String stageCode) {
        String path = Config.getString("wares.getGradeByStage") + stageCode;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getSubjectByGrade(String gradeCode) {
        String path = Config.getString("wares.getSubjectByGrade") + gradeCode;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean mobilWaresListByTeacherIdAndTime(String openId, int pageNo, int pageSize, String cover, String sales, String maxNumber) {
        String path = Config.getString("wares.mobilWaresListByTeacherIdAndTime") + "teacherId=" + openId + "&pageNo=" + pageNo + "&pageSize=" + pageSize+
                "&cover="+cover+"&sales="+sales+"&maxNumber="+maxNumber;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean mobilWaresListByTeacherId(String openId, int pageNo, int pageSize, int curriculumType) {
        String path = Config.getString("wares.mobilWaresListByTeacherId")+"teacherId="+openId+"&pageNo="+pageNo
                                + "&pageSize=" + pageSize + "&curriculumType=" + curriculumType;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean mobilWaresInfoByWaresId(String openId, int waresId, String subjectCode, String gradeCode, String bookTypeCode) {
        String path = Config.getString("wares.mobilWaresInfoByWaresId") + "waresId=" + waresId
                            +(StringUtil.isEmpty(subjectCode)?"":("&subjectCode="+subjectCode))
                            +(StringUtil.isEmpty(gradeCode)?"":("&gradeCode=")+gradeCode)
                            +(StringUtil.isEmpty(bookTypeCode)?"":("&bookTypeCode=")+bookTypeCode);
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean releaseStatus(String openId, int waresId, int status) {
        String path = Config.getString("wares.releaseStatus") + "waresId=" + waresId+ "&status=" + status;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "PUT");
        return dealResult(log,response);
    }

    @Override
    public ResultBean delVideo(int videoId) {
        String path = Config.getString("wares.delViedoById")+ videoId;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "DELETE");
        return dealResult(log,response);
    }

    @Override
    public ResultBean videoCountByStatus(String openId, int status) {
        String path = Config.getString("ware.videoCountByStatus")+"author=" + openId +"&examination="+status;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean examList(String subjectCode, String gradeCode, String bookTypeCode) {
        String path = Config.getString("ware.ExamList")+"subjectCode="+(StringUtil.isEmpty(subjectCode)?"":subjectCode)
                                +(StringUtil.isEmpty(gradeCode)?"":("&gradeCode="+gradeCode))
                                +(StringUtil.isEmpty(bookTypeCode)?"":("&bookTypeCode=" + bookTypeCode));
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean answerInfoById(int id) {
        String path = Config.getString("ware.answerInfoById") + id;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean examStatistic(int id) {
        String path = Config.getString("ware.examStatistic") + id;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean popularity(String waresId, String popType) {
        String path = Config.getString("ware.popularity");
        PopularityBean popularityBean = new PopularityBean(popType,waresId);
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, popularityBean, "PUT");
        return dealResult(log,response);
    }

    @Override
    public ResultBean liveVideoPlayback(int waresId, int slaveId) {
        String path = Config.getString("ware.liveVideoPlayback")+"waresId=" + waresId +"&slaveId="+slaveId;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getMyPapers(String openId, int pageNo, int pageSize, Integer useType, String gradeCode, String subjectCode) {
        String path = Config.getString("wares.getMyPapers")+"teacherId="+openId +"&pageNo="+pageNo+"&pageSize="+pageSize
                +(useType==null?"":("&useType="+useType)) +(StringUtil.isEmpty(gradeCode)?"":("&gradeCode="+gradeCode))
                +(StringUtil.isEmpty(subjectCode)?"":("&subjectCode="+subjectCode));
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean getPaperInfo(String paperId) {
        String path = Config.getString("wares.getPaperInfo")+"paperId=" + paperId;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean todayCourse() {
        String path = Config.getString("wares.todayCourse");
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean paperCommitInfoByStudent(String openId, Integer waresId, Integer waresSlaveId, String paperId) {
        String path = Config.getString("wares.paperCommitInfoByStudent")+"student="+openId+"&waresId="+waresId+
                (waresSlaveId==null?"":("&waresSlaveId="+waresSlaveId))+(StringUtil.isEmpty(paperId)?"":("&paperId="+paperId));
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean paperCommitInfoByTeacher(String waresId, String waresSlaveId, String paperId) {
        String path = Config.getString("wares.paperCommitInfoByTeacher")+"waresId="+waresId
                        +(StringUtil.isEmpty(waresSlaveId)?"":("&waresSlaveId="+waresSlaveId))
                        +(StringUtil.isEmpty(paperId)?"":("&paperId="+paperId));
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean checkPaperCommitByStudent(String waresId, String waresSlaveId, String paperId, String openId) {
        String path = Config.getString("wares.checkPaperCommitByStudent")+"waresId="+waresId+"&openId="+openId+
                (StringUtil.isEmpty(waresSlaveId)?"":("&waresSlaveId="+waresSlaveId))+"&paperId="+paperId;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "GET");
        return dealResult(log,response);
    }

    @Override
    public ResultBean deleteWaresById(int waresId) {
        String path = Config.getString("wares.deleteWaresById")+waresId;
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, null, "DELETE");
        return dealResult(log,response);
    }

    @Override
    public ResultBean liveWebUrl(M3JoinMtgParam m3JoinMtgParam) {
        String path = Config.getString("wares.liveWebUrl");
        String response = ServerUtil.httpRest(DEPENDENT_SERVER, path, null, m3JoinMtgParam, "POST");
        return dealResult(log,response);
    }

    @Override
    public ResultBean backUrlList(BackUrlListParamBean backUrlListParamBean) {
        String path = Config.getString("wares.backUrlList");
        String response = ServerUtil.httpRest(DEPENDENT_SERVER, path, null, backUrlListParamBean, "POST");
        return dealResult(log,response);
    }

    @Override
    public ResultBean backUrlInfo(BackUrlInfoParamBean backUrlInfoParamBean) {
        String path = Config.getString("wares.backUrlInfo");
        String response = ServerUtil.httpRest(DEPENDENT_SERVER, path, null, backUrlInfoParamBean, "POST");
        return dealResult(log,response);
    }

    @Override
    public ResultBean appMtgInfo(M3JoinMtgParam m3JoinMtgParam) {
        String path = Config.getString("wares.appMtgInfo");
        String response = ServerUtil.httpRest(DEPENDENT_SERVER, path, null, m3JoinMtgParam, "POST");
        return dealResult(log,response);
    }

    @Override
    public ResultBean homework(int commodityId, String type, String homeWorkCode) {
        String path = Config.getString("ware.homework");
        HomeworkParamBean hpb = new HomeworkParamBean(commodityId,type,homeWorkCode);
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, hpb, "PUT");
        return dealResult(log,response);
    }

    @Override
    public ResultBean uploadVideo(String tag, UploadVideoParamBean uploadVideoParamBean) {
        String path = Config.getString("wares.uploadvideo");
        String response = ServerUtil.httpRest(WARES_SERVER, path, null,uploadVideoParamBean , "POST");
        if (IF_MEM==1) setMemcached(tag,response,log);
        return dealResult(log,response);
    }

    @Override
    public ResultBean wareLive(String tag, WareLiveBean wareLiveBean) {
        String path = Config.getString("wares.warelive");
        String response = ServerUtil.httpRest(WARES_SERVER, path, null,wareLiveBean , "POST");
        if (IF_MEM==1) setMemcached(tag,response,log);
        return dealResult(log,response);
    }

    @Override
    public ResultBean wareVideo(String tag, WareVideoBean wareVideoBean) {
        String path = Config.getString("wares.warevideo");
        String response = ServerUtil.httpRest(WARES_SERVER, path, null,wareVideoBean , "POST");
        if (IF_MEM==1) setMemcached(tag,response,log);
        return dealResult(log,response);
    }

    @Override
    public ResultBean wareOne2One(String tag, WareOne2OneBean wareOne2OneBean) {
        String path = Config.getString("wares.wareOne2One");
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, wareOne2OneBean, "POST");
        if (IF_MEM==1) setMemcached(tag,response,log);
        return dealResult(log,response);
    }

    @Override
    public ResultBean commitExam(String tag, CommitExamBean commitExamBean) {
        String path = Config.getString("wares.commitExam");
        String response = ServerUtil.httpRest(WARES_SERVER, path, null, commitExamBean, "POST");
        if (IF_MEM==1) setMemcached(tag,response,log);
        return dealResult(log,response);
    }

    @Override
    public ResultBean updateWaresLive(String tag, WareLiveBean wareLiveBean) {
        String path = Config.getString("wares.updateWaresLive");
        String response = ServerUtil.httpRest(WARES_SERVER, path, null,wareLiveBean , "PUT");
        if (IF_MEM==1) setMemcached(tag,response,log);
        return dealResult(log,response);
    }

}
