package com.intkr.saas.domain.type.user;

/**
 * 
 * @author Beiden
 * @date 2016-4-22 上午8:46:45
 * @version 1.0
 */
public enum AuthType {

	RightRole("rightRole", "授予权限给角色"), //
	RightUser("rightUser", "授予权限给用户色"), //
	RoleUser("roleUser", "授予角色给用户色"), //
	Error("error", "异常"); //

	private AuthType(String code, String name) {
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

	public static AuthType getByCode(String code) {
		AuthType[] values = values();
		for (AuthType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
