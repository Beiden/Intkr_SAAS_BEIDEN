package com.intkr.saas.domain.type.user;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:04:45
 * @version 1.0
 */
public enum UserStatus {

	Normal("normal", "正常"), //
	WaitVerified("waitVerified", "待认证"), //
	Prohibit("prohibit", "禁用"), //
	Error("error", "异常"); //

	private UserStatus(String code, String name) {
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

	public static UserStatus getByCode(String code) {
		UserStatus[] values = values();
		for (UserStatus value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
