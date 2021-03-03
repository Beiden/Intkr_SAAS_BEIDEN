package com.intkr.saas.domain.type.sys;

/**
 * 
 * @author Beiden
 * @date 2011-10-16 下午6:03:54
 * @version 1.0
 */
public enum TagType {

	Article("article", "文章"), //
	Item("item", "商品"), //
	Error("error", "异常"); //

	private TagType(String code, String name) {
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
	
	public static TagType getByCode(String code) {
		TagType[] values = values();
		for (TagType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
