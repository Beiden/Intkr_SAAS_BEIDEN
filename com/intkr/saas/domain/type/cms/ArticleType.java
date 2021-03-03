package com.intkr.saas.domain.type.cms;

/**
 * 
 * @author Beiden
 * @date 2011-10-16 上午10:38:45
 * @version 1.0
 */
public enum ArticleType {

	Article("article", "文章"), // 文章
	Page("page", "页面"), // 页面
	Error("error", "异常"); // 页面

	private ArticleType(String code, String name) {
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
	
	public static ArticleType getByCode(String code) {
		ArticleType[] values = values();
		for (ArticleType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
