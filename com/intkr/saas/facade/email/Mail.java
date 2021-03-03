package com.intkr.saas.facade.email;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Beiden
 * @date 2011-4-20 上午11:44:47
 * @version 1.0
 */
public class Mail {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private MimeMessage mimeMsg; // MIME邮件对象
	private Session session; // 邮件会话对象
	private Properties props; // 系统属性
	private Multipart mp; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象
	private SendObj sendObj;

	public Mail(SendObj sendObj) {
		this.sendObj = sendObj;
	}

	private boolean createMimeMessage() {
		try {
			logger.debug("准备获取邮件会话对象！");
			session = Session.getDefaultInstance(props, null); // 获得邮件会话对象
		} catch (Exception e) {
			System.err.println("获取邮件会话对象时发生错误！" + e);
			return false;
		}
		logger.debug("准备创建MIME邮件对象！");
		try {
			mimeMsg = new MimeMessage(session); // 创建MIME邮件对象
			mp = new MimeMultipart();
			return true;
		} catch (Exception e) {
			System.err.println("创建MIME邮件对象失败！" + e);
			return false;
		}
	}

	private void setNeedAuth(boolean need) {
		logger.debug("设置smtp身份认证：mail.smtp.auth = " + need);
		if (props == null) {
			props = System.getProperties();
		}
		if (need) {
			props.put("mail.smtp.auth", "true");
		} else {
			props.put("mail.smtp.auth", "false");
		}
	}

	private boolean setSubject(String mailSubject) {
		logger.debug("设置邮件主题！");
		try {
			mimeMsg.setSubject(mailSubject);
			return true;
		} catch (Exception e) {
			System.err.println("设置邮件主题发生错误！");
			return false;
		}
	}

	private boolean setBody(String mailBody) {
		try {
			BodyPart bp = new MimeBodyPart();
			bp.setContent("" + mailBody, "text/html;charset=GBK");
			mp.addBodyPart(bp);
			return true;
		} catch (Exception e) {
			System.err.println("设置邮件正文时发生错误！" + e);
			return false;
		}
	}

	private boolean addFileAffix(String filename) {
		logger.debug("增加邮件附件：" + filename);
		if (filename == null || "".equals(filename)) {
			return true;
		}
		try {
			BodyPart bp = new MimeBodyPart();
			FileDataSource fileds = new FileDataSource(filename);
			bp.setDataHandler(new DataHandler(fileds));
			bp.setFileName(fileds.getName());
			mp.addBodyPart(bp);
			return true;
		} catch (Exception e) {
			System.err.println("增加邮件附件：" + filename + "发生错误！" + e);
			return false;
		}
	}

	private boolean setFrom(String from) {
		logger.debug("设置发信人！");
		try {
			mimeMsg.setFrom(new InternetAddress(from)); // 设置发信人
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean setTo(String to) {
		if (to == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean setCopyTo(String copyto) {
		if (copyto == null || "".equals(copyto)) {
			return true;
		}
		try {
			mimeMsg.setRecipients(Message.RecipientType.CC, (Address[]) InternetAddress.parse(copyto));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean sendOut() {
		try {
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			logger.debug("正在发送邮件....");
			Session mailSession = Session.getInstance(props, null);
			Transport transport = mailSession.getTransport("smtp");
			transport.connect((String) props.get("mail.smtp.host"), sendObj.getUsername(), sendObj.getPassword());
			transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
			// transport.sendMessage(mimeMsg,
			// mimeMsg.getRecipients(Message.RecipientType.CC));
			// transport.send(mimeMsg);
			logger.debug("发送邮件成功！");
			transport.close();
			return true;
		} catch (Exception e) {
			System.err.println("邮件发送失败！" + e);
			return false;
		}
	}

	public boolean send() {
		initSMTP();
		createMimeMessage();
		this.setNeedAuth(true); // 需要验证
		if (!this.setSubject(sendObj.getSubject())) {
			return false;
		}
		if (!this.setBody(sendObj.getContent())) {
			return false;
		}
		if (!this.addFileAffix(sendObj.getFilename())) {
			return false;
		}
		if (!this.setTo(sendObj.getTo())) {
			return false;
		}
		if (!this.setCopyTo(sendObj.getCopyto())) {
			return false;
		}
		if (!this.setFrom(sendObj.getFrom())) {
			return false;
		}
		if (!this.sendOut()) {
			return false;
		}
		return true;
	}

	private void initSMTP() {
		logger.debug("设置系统属性：mail.smtp.host = " + sendObj.getSmtp());
		if (props == null) {
			props = System.getProperties(); // 获得系统属性对象
		}
		props.put("mail.smtp.host", sendObj.getSmtp()); // 设置SMTP主机
	}

}
