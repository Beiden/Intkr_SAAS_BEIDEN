package com.intkr.saas.domain.type.sys;

/**
 * 
 * @author Beiden
 * @date 2016-4-24 上午11:59:15
 * @version 1.0
 */
public enum SignLogType {

	Login("login", "登录"), //
	Logout("logout", "注销"), //
	Error("error", "异常"); //

	private SignLogType(String code, String name) {
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
	
	public static SignLogType getByCode(String code) {
		SignLogType[] values = values();
		for (SignLogType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
