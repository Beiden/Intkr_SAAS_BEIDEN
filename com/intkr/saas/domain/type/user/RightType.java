package com.intkr.saas.domain.type.user;

/**
 * 
 * @author Beiden
 * @date 2011-11-22 下午2:21:01
 * @version 1.0
 */
public enum RightType {

	Page("page", "页面"), //
	Action("action", "动作"), //
	Custom("custom", "自定义"), //
	Error("error", "异常"); //

	private RightType(String code, String name) {
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
	
	public static RightType getByCode(String code) {
		RightType[] values = values();
		for (RightType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
