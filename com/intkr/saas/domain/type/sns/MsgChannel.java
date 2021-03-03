package com.intkr.saas.domain.type.sns;

/**
 * 
 * @author Beiden
 * @date 2016-4-24 下午5:12:48
 * @version 1.0
 */
public enum MsgChannel {

	Saas("saas", "Saas系统消息"), //
	System("system", "系统消息"), //
	Email("email", "邮件消息"), //
	App("app", "app消息"), //
	Phone("phone", "手机消息"), //
	WeiXin("weiXin", "微信消息"), //
	Error("error", "异常"); //

	private MsgChannel(String code, String name) {
		this.code = code;
		this.name = name;
	}

	private String code;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public static MsgChannel getByCode(String code) {
		MsgChannel[] values = values();
		for (MsgChannel value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
