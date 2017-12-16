package com.ljb.leeBookShop.util;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendJMail {

		public static boolean sendMail(String email, String emailMsg) {
		
		String from = "li15712691251@163.com"; 				// 发送人
		String to = email; 										// 收件地址
		final String username = "li15712691251@163.com";  	//发件人邮箱
		final String password = "li15712691251";   					//密码


		//定义Properties对象，设置环境信息
		Properties props = System.getProperties();

		//设置邮箱服务器地址
		props.setProperty("mail.smtp.host", "smtp.163.com"); // ָ指定smtp服务器
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");//设置发送邮件使用的协议

		MyAuthenticator myAuthenticator = new MyAuthenticator("li15712691251@163.com", "li15712691251");
			//创建session对象，session对象表示整个邮件的环境信息
		Session session = Session.getDefaultInstance(props,myAuthenticator);
		//设置输出调戏信息
		session.setDebug(true);
		try {
			//Message的实例对象表示一份邮件
			MimeMessage message = new MimeMessage(session);
			//设置发件人地址
			message.setFrom(new InternetAddress(from));
			//设置主题
			message.setSubject("用户激活");
			//设置邮件的文本内容
			//message.setText("Welcome to JavaMail World!");
			message.setContent((emailMsg),"text/html;charset=utf-8");
			//从session的环境中获取发送邮件的对象
			Transport transport=session.getTransport();
			//连接邮箱服务器
			transport.connect("smtp.163.com",25, username, password);
			//设置收件人地址，并发送消息
			transport.sendMessage(message,new Address[]{new InternetAddress(to)});
			transport.close();
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static class MyAuthenticator extends Authenticator {

		private String passwordStr;
		private String userStr;

		public MyAuthenticator(String user, String password) {
			this.userStr = user;
			this.passwordStr = password;
		}

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userStr,passwordStr);
		}
	}
}
