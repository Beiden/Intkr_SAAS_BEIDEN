package com.intkr.saas.domain.type.user;

/**
 * 
 * @author Beiden
 * @date 2016-5-24 下午9:31:32
 * @version 1.0
 */
public enum RightSystemType {

	Frontend("frontend", "前台"), //
	Backend("backend", "管理员后台"), //
	SuperBackend("superBackend", "超级管理员后台"), //
	Error("error", "异常"); //

	private RightSystemType(String code, String name) {
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

	public static RightSystemType getByCode(String code) {
		RightSystemType[] values = values();
		for (RightSystemType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
