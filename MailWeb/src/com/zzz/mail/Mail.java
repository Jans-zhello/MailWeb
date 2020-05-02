package com.zzz.mail;
/**
 * 邮箱发送流程：
 * 1 设置字符编码集
 * 2 获取前台内容
 * 3 设置一个发送者的用户名和授权码(必须)
 * 4 设置邮箱相关协议和主机
 * 5 创建一个session会话(将4的对象放入)
 * 6 创建一个邮箱对象(将5的对象放入)
 * 7 设置一个发送者的地址Address
 * 8 设置邮箱的邮件内容来源(6的对象调用setXXX方法)
 * 9 创建一个发送工具Transport依次调用connect方法、sendMessage方法、close方法
 * 10验证是否发送成功：PrintWriter pw = response.getWrite(); pw.print("发送成功");
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Mail extends HttpServlet{
   @Override
protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	   //设置编码字符集
	  request.setCharacterEncoding("utf-8");
	  response.setContentType("text/html;charset=utf-8");
      //获取前台字段内容
	  String name = request.getParameter("m_name");
	  String topic = request.getParameter("m_topic");
	  String content = request.getParameter("m_content");
	  System.out.println(name+""+topic+""+content);
	  //设置一个用户名
	  String username = "18337398326@163.com";
	  //设置一个授权码
	  String password = "ZZZKANGZHAN1314";
	  //设置邮箱协议smtp和主机
      Properties properties = new Properties();//读取邮箱的配置
      properties.put("mail.transport.protocol","smtp");//邮件传输协议
      properties.put("mail.host","smtp.163.com");//设置主机
      properties.put("mail.smtp.auth", true);
      //创建一个会话
      Session session = Session.getInstance(properties);
      //设置一个邮箱(供用户名和授权码登录的环境)
      MimeMessage ms = new MimeMessage(session);
      //设置发送者
      Address toAddress;
      try {
		toAddress = new InternetAddress(username);
		//设置邮件来源
		ms.setFrom(toAddress);
		ms.setRecipients(javax.mail.Message.RecipientType.TO,name);
		ms.setSubject(topic);
		ms.setText(content);
		ms.saveChanges();
		//设置一个发送工具
		Transport ts = session.getTransport();
		ts.connect(username, password);
		ts.sendMessage(ms, ms.getAllRecipients());
		ts.close();
		//验证是否发送成功
		PrintWriter pw =response.getWriter();
		pw.print("发送成功");
		pw.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}
