package com.yijiajiao.server.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.w3c.dom.NodeList;

public class Dom4JUtil {

  /**
   * @description 解析xml字符串
   */

  public void readStringXml(String xml) {
    Document doc = null;
    try {
      // 读取并解析XML文档
      // SAXReader就是一个管道，用一个流的方式，把xml文件读出来
      // SAXReader reader = new SAXReader(); //User.hbm.xml表示你要解析的xml文档
      // Document document = reader.read(new File("User.hbm.xml"));
      // 下面的是通过解析xml字符串的
      doc = DocumentHelper.parseText(xml); // 将字符串转为XML
      Element rootElt = doc.getRootElement(); // 获取根节点
      System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
      Iterator iter = rootElt.elementIterator("head"); // 获取根节点下的子节点head
      // 遍历head节点

      while (iter.hasNext()) {
        Element recordEle = (Element) iter.next();
        String title = recordEle.elementTextTrim("title"); // 拿到head节点下的子节点title值
        System.out.println("title:" + title);
        Iterator iters = recordEle.elementIterator("script"); // 获取子节点head下的子节点script

        // 遍历Header节点下的Response节点
        while (iters.hasNext()) {
          Element itemEle = (Element) iters.next();
          String username = itemEle.elementTextTrim("username"); // 拿到head下的子节点script下的字节点username的值
          String password = itemEle.elementTextTrim("password");
          System.out.println("username:" + username);
          System.out.println("password:" + password);
        }
      }
      Iterator iterss = rootElt.elementIterator("body"); // /获取根节点下的子节点body

      // 遍历body节点

      while (iterss.hasNext()) {
        Element recordEless = (Element) iterss.next();
        String result = recordEless.elementTextTrim("result"); // 拿到body节点下的子节点result值
        System.out.println("result:" + result);
        Iterator itersElIterator = recordEless.elementIterator("form"); // 获取子节点body下的子节点form

        // 遍历Header节点下的Response节点

        while (itersElIterator.hasNext()) {
          Element itemEle = (Element) itersElIterator.next();
          String banlce = itemEle.elementTextTrim("banlce"); // 拿到body下的子节点form下的字节点banlce的值
          String subID = itemEle.elementTextTrim("subID");
          System.out.println("banlce:" + banlce);
          System.out.println("subID:" + subID);
        }
      }
    } catch (DocumentException e) {
      e.printStackTrace();

    } catch (Exception e) {
      e.printStackTrace();

    }
  }

  /**
   * @description 将xml字符串转换成map
   * @param xml
   * @return Map
   */
  public static Map readStringXmlOut(String xml) {
    Map map = new HashMap();
    Document doc = null;
    try {
      doc = DocumentHelper.parseText(xml);
      Element rootElt = doc.getRootElement();
      System.out.println("根节点：" + rootElt.getName());
      Iterator iter = rootElt.elementIterator("notify");
      // 遍历
      String trade_status = rootElt.elementTextTrim("trade_status");
      map.put("trade_status", trade_status);
      String total_fee = rootElt.elementTextTrim("total_fee");
      map.put("total_fee", total_fee);
      String subject = rootElt.elementTextTrim("subject");
      map.put("subject", subject);
      String out_trade_no = rootElt.elementTextTrim("out_trade_no");
      map.put("out_trade_no", out_trade_no);
      // String notify_reg_time = rootElt.elementTextTrim("notify_reg_time");
      // map.put("notify_reg_time", notify_reg_time);
      String trade_no = rootElt.elementTextTrim("trade_no");
      map.put("trade_no", trade_no);
    } catch (DocumentException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
  }

  public static void main(String[] args) {
    // 下面是需要解析的xml字符串例子
    String xmlString = " <notify><seller_email>newchinese123@126.com</seller_email><partner>2088011815055529</partner><payment_type>1</payment_type><buyer_email>guorenzhicheng@sina.cn</buyer_email><trade_no>2013100679734793</trade_no><buyer_id>2088112002293930</buyer_id><quantity>1</quantity><total_fee>0.01</total_fee><use_coupon>N</use_coupon><is_total_fee_adjust>Y</is_total_fee_adjust><price>0.01</price><out_trade_no>131006154129554384</out_trade_no><gmt_create>2013-10-06 15:41:36</gmt_create><seller_id>2088011815055529</seller_id><subject>初中一年级物理卡</subject><trade_status>WAIT_BUYER_PAY</trade_status><discount>0.00</discount></notify>";
    /*
     * Test2 test = new Test2(); test.readStringXml(xmlString);
     */


      /* Map map = readStringXmlOut(xmlString);
    Iterator iters = map.keySet().iterator();
    while (iters.hasNext()) {
      String key = iters.next().toString(); // 拿到键
      String val = map.get(key).toString(); // 拿到值
      System.out.println(key + "=" + val);
    }*/
    
    
  }

}
