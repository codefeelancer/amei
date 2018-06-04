package abr.service;

import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineUtils;

import lombok.Data;

@Data
public class MailSenderService {
	private JavaMailSenderImpl mailSender;// spring配置中定义
	private VelocityEngine velocityEngine;// spring配置中定义
	private SimpleMailMessage simpleMailMessage;// spring配置中定义
	private String subject;
	private String templateName;
	// 是否需要身份验证
	private boolean validate = false;

	/**
	 * 发送模板邮件
	 * 
	 */
	public void sendWithTemplate(Map fields, String receiver) throws Exception {
		// 1.创建sesssion
		Session session = mailSender.getSession();
		// 开启session的调试模式，可以查看当前邮件发送状态
		session.setDebug(true);

		Transport ts;
		// 2.通过session获取Transport对象（发送邮件的核心API）
		ts = session.getTransport();
		// 3.通过邮件用户名密码链接
		ts.connect(mailSender.getUsername(), mailSender.getPassword());
		// 4.创建邮件
		String result = VelocityEngineUtils.mergeTemplateIntoString(this.getVelocityEngine(), this.getTemplateName(), "UTF-8", fields);
		Message msg = createSimpleMail(fields,session, result);

		// 5.发送电子邮件
		ts.sendMessage(msg, msg.getAllRecipients());

	}

	private MimeMessage createSimpleMail(Map fields, Session session, String text) throws AddressException, MessagingException {
		// 创建邮件对象
		MimeMessage mm = new MimeMessage(session);
		// 设置发件人
		mm.setFrom(new InternetAddress(mailSender.getUsername()));
		// 设置收件人
		mm.setRecipient(Message.RecipientType.TO, new InternetAddress("schenker-technology@protonmail.com"));
		mm.setSubject(fields.get("email")+" wants to contact us. From schenker-technology.com");
		mm.setText(text);
		return mm;
	}

}
