package com.yijiajiao.server.service.impl;

import com.yijiajiao.server.bean.IdBean;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.post.SetMsgBean;
import com.yijiajiao.server.service.MsgService;
import com.yijiajiao.server.util.Config;
import com.yijiajiao.server.util.ServerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import static com.yijiajiao.server.util.ServerUtil.MSG_SERVER;
import static com.yijiajiao.server.util.ServerUtil.dealResult;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2017-01-04-13:48
 */
@Service("msgService")
public class MsgServiceImpl implements MsgService{

    private static final Logger log = LoggerFactory.getLogger(MsgServiceImpl.class);

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
    public ResultBean setMsg(SetMsgBean setMsgBean) {
        String path =Config.getString("msg.setMsg");
        String response = ServerUtil.httpRest(MSG_SERVER,path,null,setMsgBean,"POST");
        return dealResult(log,response);
    }

    // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    // PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
    // 对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
    private static final String myEmailAccount =  Config.getString("myEmailAccount");
    private static final String myEmailPassword = Config.getString("myEmailPassword");
    private static final String myEmailSMTPHost = Config.getString("myEmailSMTPHost");
    private static final String myEmailName = Config.getString("myEmailName");
    // 收件人邮箱（替换为自己知道的有效邮箱）
    private static final String receiveMailAccount = Config.getString("receiveMailAccount");
    private static final String smtpPort = "465";

    @Override
    public ResultBean sendMail(Map<String, Object> mail) throws Exception {

        String name = (String) mail.get("name");
        String phone = (String) mail.get("phone");
        String area = (String) mail.get("area");

        String content = "<p>姓名：" + name + "</p><p>手机号：" + phone + "</p><p>地区：" + area + "</p>";
        String subject = "我要升级-" + name;

        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);// 设置为debug模式, 可以查看详细的发送 log

        // 3. 创建一封邮件
        MimeMessage message = createMimeMessage(session, subject, content,
                myEmailAccount, myEmailName, receiveMailAccount);

        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
        transport.connect(myEmailAccount, myEmailPassword);
        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        // 7. 关闭连接
        transport.close();

        return ResultBean.getSucResult("发送成功！");
    }

    public static void main(String[] args) {
        String content = "姓名： \n手机号： \n地区：";
        System.out.println(content);
    }

    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
     *
     */
    private MimeMessage createMimeMessage(Session session,
                                          String subject,String content,
                                          String sendMail, String sendName,
                                          String receiveMail) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(sendMail, sendName, "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO,
                new InternetAddress(receiveMail));

        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject(subject, "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        message.setContent(content, "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }
}
