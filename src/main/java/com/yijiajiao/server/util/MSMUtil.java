package com.yijiajiao.server.util;

import com.alibaba.fastjson.JSON;
import com.yijiajiao.server.bean.ResultBean;
import com.yijiajiao.server.bean.ResultBean2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class MSMUtil {

	public static MSMUtil msmUtil;
	private static final String  tool_server = Config.uuimsString("tool_server");
	private static final String  REGISTER  = "register";                       //1 注册
	private static final String  EDITPASS  = "edit_password";                  // 2修改密码
	private static final String  TEACHERCERTIFICATE = "teacher_certificate";   // 3老师资格认证
	private static final String  RESETPASS  = "edit_password";                 // 4找回密码
	private static final String  PLANREGISTER ="keepMark_resetPassword"; // 22保分计划用户注册{恭喜你已成功注册【亿家教】账号，并确认参与VIP保分计划，您的临时密码为{code},请妥善保管，为了您的账户安全，请首次登陆【亿家教】之后修改密码。{productType}}
	private static final String  ALIPAYBINDING ="keepMark_zhiFuBao";     //23绑定支付宝

	static {
		if (msmUtil == null) {
			msmUtil = new MSMUtil();
		}
  	}
  	private MSMUtil(){}

	// 新版短信接口
	public  ResultBean send1(String telephone, int type) {
		ResultBean resultBean = new ResultBean();
		String path = Config.uuimsString("verifycodeurl");
		ResultBean2 r = null;
		String sendType = "";
		StringBuilder sb = new StringBuilder();
		switch (type) {
			case 1:sendType = REGISTER;break;
			case 2:sendType = EDITPASS;break;
			case 3:sendType = TEACHERCERTIFICATE;break;
			case 4:sendType = RESETPASS;break;
			case 22:sendType = PLANREGISTER;break;
			case 23:sendType= ALIPAYBINDING;break;
			default:sendType = REGISTER;break;
		}
		sb.append("phone="+telephone);
		sb.append("&password="+Config.uuimsString("password"));
		sb.append("&openId="+Config.uuimsString("openId"));
		sb.append("&sendType="+sendType);
		try {
			String response = ServerUtil.httpRestForm(tool_server, path, null, sb.toString());
			r = JSON.parseObject(response, ResultBean2.class);
			if ("200".equals(r.getHttpCode())) {
				resultBean.setSucResult(r.getResult().getCode());
			} else {
				resultBean.setFailMsg(40009, "获取验证码失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setFailMsg(40010, "获取验证码失败");
		}
		return resultBean;
	}


   /**
	* 向指定 URL 发送POST方法的请求
	*
	* @param url
	*            发送请求的 URL
	* @param paramter
	*            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	* @return 所代表远程资源的响应结果
	*/
	public static String postRequest(String url, String paramter) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(paramter);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) {
		msmUtil.send1("13520351893",1);
	}
}
