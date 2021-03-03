package com.intkr.saas.facade.email;

public class SendObj {

	private String username;// smtp认证用户名和密码
	private String password;// smtp认证用户名和密码

	private String smtp;// smtp邮件服务器

	private String from; // 发件人
	private String to;// 收货人
	private String copyto; // 抄送人

	private String subject; // 邮件主题
	private String content; // 邮件内容

	private String filename;// 附件

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSmtp() {
		if (smtp == null && username.endsWith("@qq.com")) {
			smtp = "smtp.qq.com";
		} else if (smtp == null && username.endsWith("@163.com")) {
			smtp = "smtp.163.com";
		} else if (smtp == null && username.endsWith("@intkr.com")) {
			smtp = "smtp.mxhichina.com";
		} else if (smtp == null) {
			smtp = "smtp.exmail.qq.com";
		}
		if (smtp == null || "".equals(smtp)) {
			throw new RuntimeException("SMTP Server error");
		}
		return smtp;
	}

	public String getFrom() {
		if (from == null) {
			from = username;
		}
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCopyto() {
		return copyto;
	}

	public void setCopyto(String copyto) {
		this.copyto = copyto;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
