package com.zzz.mail;
/**
 * ���䷢�����̣�
 * 1 �����ַ����뼯
 * 2 ��ȡǰ̨����
 * 3 ����һ�������ߵ��û�������Ȩ��(����)
 * 4 �����������Э�������
 * 5 ����һ��session�Ự(��4�Ķ������)
 * 6 ����һ���������(��5�Ķ������)
 * 7 ����һ�������ߵĵ�ַAddress
 * 8 ����������ʼ�������Դ(6�Ķ������setXXX����)
 * 9 ����һ�����͹���Transport���ε���connect������sendMessage������close����
 * 10��֤�Ƿ��ͳɹ���PrintWriter pw = response.getWrite(); pw.print("���ͳɹ�");
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
	   //���ñ����ַ���
	  request.setCharacterEncoding("utf-8");
	  response.setContentType("text/html;charset=utf-8");
      //��ȡǰ̨�ֶ�����
	  String name = request.getParameter("m_name");
	  String topic = request.getParameter("m_topic");
	  String content = request.getParameter("m_content");
	  System.out.println(name+""+topic+""+content);
	  //����һ���û���
	  String username = "18337398326@163.com";
	  //����һ����Ȩ��
	  String password = "ZZZKANGZHAN1314";
	  //��������Э��smtp������
      Properties properties = new Properties();//��ȡ���������
      properties.put("mail.transport.protocol","smtp");//�ʼ�����Э��
      properties.put("mail.host","smtp.163.com");//��������
      properties.put("mail.smtp.auth", true);
      //����һ���Ự
      Session session = Session.getInstance(properties);
      //����һ������(���û�������Ȩ���¼�Ļ���)
      MimeMessage ms = new MimeMessage(session);
      //���÷�����
      Address toAddress;
      try {
		toAddress = new InternetAddress(username);
		//�����ʼ���Դ
		ms.setFrom(toAddress);
		ms.setRecipients(javax.mail.Message.RecipientType.TO,name);
		ms.setSubject(topic);
		ms.setText(content);
		ms.saveChanges();
		//����һ�����͹���
		Transport ts = session.getTransport();
		ts.connect(username, password);
		ts.sendMessage(ms, ms.getAllRecipients());
		ts.close();
		//��֤�Ƿ��ͳɹ�
		PrintWriter pw =response.getWriter();
		pw.print("���ͳɹ�");
		pw.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}
