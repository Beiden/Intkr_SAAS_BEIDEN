package com.intkr.saas.domain.type.sys;

/**
 * 
 * @author Beiden
 * @date 2016-5-26 下午2:30:54
 * @version 1.0
 */
public enum SysImgType {

	Avatar("avatar", "用户头像"), //
	Error("error", "异常"); //

	private SysImgType(String code, String name) {
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

	public static SysImgType getByCode(String code) {
		SysImgType[] values = values();
		for (SysImgType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
