package com.intkr.saas.domain.type.sys;

/**
 * 
 * @author Beiden
 * @date 2011-10-16 下午5:58:34
 * @version 1.0
 */
public enum FrontMenuDetailType {

	Link("link", "自定义链接"), //
	Tag("tag", "标签"), //
	Page("page", "页面"), //
	Category("category", "分类目录"), //
	Error("error", "异常"); //

	private FrontMenuDetailType(String code, String name) {
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
	
	public static FrontMenuDetailType getByCode(String code) {
		FrontMenuDetailType[] values = values();
		for (FrontMenuDetailType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
