package com.intkr.saas.domain.type.item;

/**
 * 
 * @author Beiden
 * @date 2011-10-16 上午11:19:08
 * @version 1.0
 */
public enum CategoryRelatedType {

	Article("article", "文章"), //
	Item("item", "商品"), //
	Error("error", "异常"); //

	private CategoryRelatedType(String code, String name) {
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

	public static CategoryRelatedType getByCode(String code) {
		CategoryRelatedType[] values = values();
		for (CategoryRelatedType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
