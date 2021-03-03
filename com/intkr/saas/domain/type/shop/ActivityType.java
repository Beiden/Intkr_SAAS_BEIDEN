package com.intkr.saas.domain.type.shop;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:04:00
 * @version 1.0
 */
public enum ActivityType {

	Common("common", "一般活动"), //
	Other("other", "页面"), //
	Error("error", "异常"); //

	private ActivityType(String code, String name) {
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
	
	public static ActivityType getByCode(String code) {
		ActivityType[] values = values();
		for (ActivityType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
