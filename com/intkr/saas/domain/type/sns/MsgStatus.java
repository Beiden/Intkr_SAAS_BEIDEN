package com.intkr.saas.domain.type.sns;

/**
 * 
 * @author Beiden
 * @date 2016-4-24 上午10:02:37
 * @version 1.0
 */
public enum MsgStatus {

	Sended("sended", "发送成功"), //
	Pending("pending", "阻塞"), //

	Error("error", "异常"); //

	private MsgStatus(String code, String name) {
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

	public static MsgStatus getByCode(String code) {
		MsgStatus[] values = values();
		for (MsgStatus value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
