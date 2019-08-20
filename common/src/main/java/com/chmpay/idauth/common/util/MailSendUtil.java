package com.chmpay.idauth.common.util;

import org.apache.commons.lang.StringUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.Properties;


public class MailSendUtil {


	/**
	 *
	 * @param Addressee  接收人邮箱地址
	 * @param Content   内容
	 * @param Subject   标题
	 * @throws Exception
	 */
	public static void sendMailText(String Addressee,String Content,String Subject) throws Exception{
		final Properties props = new Properties();
		String absolutePath = System.getProperty("user.home") +File.separator+ "props" + File.separator+ "email_config.properties";
		InputStream input = new FileInputStream(new File(absolutePath));
		props.load(input);
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
        message.setFrom(form);
        // 设置收件人
        InternetAddress to = new InternetAddress(Addressee);
        message.setRecipient(RecipientType.TO, to);
        // 设置邮件标题
        message.setSubject(Subject);
        // 设置邮件的内容体
        message.setText(Content, "UTF-8");
        // 发送邮件
        Transport.send(message);
	}

	public static void sendMailHtml(String Addressee,String Content,String Subject) throws Exception{
		final Properties props = new Properties();
		String absolutePath = System.getProperty("user.home") +File.separator+ "props" + File.separator+ "email_config.properties";
		InputStream input = new FileInputStream(new File(absolutePath));
		props.load(input);
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
        message.setFrom(form);
        // 设置收件人
        InternetAddress to = new InternetAddress(Addressee);
        message.setRecipient(RecipientType.TO, to);
        // 设置邮件标题
        message.setSubject(Subject);
        // 设置邮件的内容体
        message.setContent(Content, "text/html;charset=UTF-8");
        // 发送邮件
        Transport.send(message);
	}

    public static void sendMailWithFile(String Addressee, String Content, String Subject, String filePath) throws Exception {
        final Properties props = new Properties();
        String absolutePath = System.getProperty("user.home") +File.separator+ "props" + File.separator+ "email_config.properties";
        InputStream input = new FileInputStream(new File(absolutePath));
        props.load(input);
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
        message.setFrom(form);
        // 设置收件人
        InternetAddress to = new InternetAddress(Addressee);
        message.setRecipient(RecipientType.TO, to);
        // 设置邮件标题
        message.setSubject(Subject);
        // 设置邮件的内容体
//        message.setContent(Content, "text/html;charset=UTF-8");
        MimeMultipart mimeMultipart = new MimeMultipart();
        MimeBodyPart part = new MimeBodyPart();
        part.setContent(Content, "text/html;charset=UTF-8");
        mimeMultipart.addBodyPart(part);
        // 附件
        if (StringUtils.isNotEmpty(filePath)) {
            part = new MimeBodyPart();
            File file = new File(filePath);
            if (file.exists()) {
                FileDataSource fds = new FileDataSource(file);
                part.setDataHandler(new DataHandler(fds));
                part.setFileName(file.getName());

                mimeMultipart.addBodyPart(part);

            }
        }

        message.setContent(mimeMultipart);

        // 发送邮件
        Transport.send(message);
    }

}
