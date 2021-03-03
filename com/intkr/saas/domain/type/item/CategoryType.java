package com.intkr.saas.domain.type.item;

/**
 * 
 * @author Beiden
 * @date 2011-10-16 上午11:19:08
 * @version 1.0
 */
public enum CategoryType {

	Article("article", "文章分类"), //
	Auction("auction", "竞拍分类"), //
	Bids("bids", "招标分类"), //
	Item("item", "商品分类"), //
	Demand("demand", "需求分类"), //
	Model("model", "模型分类"), //
	Error("error", "异常"); //

	private CategoryType(String code, String name) {
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
	
	public static CategoryType getByCode(String code) {
		CategoryType[] values = values();
		for (CategoryType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
