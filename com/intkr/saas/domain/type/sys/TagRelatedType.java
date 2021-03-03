package com.intkr.saas.domain.type.sys;

/**
 * 
 * @author Beiden
 * @date 2016-6-21 下午6:46:58
 * @version 1.0
 */
public enum TagRelatedType {

	Article("article", "文章"), //
	Page("page", "页面"), //
	Item("item", "商品"), //
	Error("error", "异常"); //

	private TagRelatedType(String code, String name) {
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

	public static TagRelatedType getByCode(String code) {
		TagRelatedType[] values = values();
		for (TagRelatedType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
