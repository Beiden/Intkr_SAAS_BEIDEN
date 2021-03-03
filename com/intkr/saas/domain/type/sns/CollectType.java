package com.intkr.saas.domain.type.sns;

/**
 * 
 * @author Beiden
 * @date 2016-3-6 下午5:48:06
 * @version 1.0
 */
public enum CollectType {

	Article("article", "文章"), //
	Item("item", "商品"), //
	Shop("shop", "店铺"), //
	Bids("bids", "招标"), //
	Auction("auction", "竞拍"), //
	Demand("demand", "需求"), //
	Page("page", "页面"), //
	Error("error", "异常"); //

	private CollectType(String code, String name) {
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
	
	public static CollectType getByCode(String code) {
		CollectType[] values = values();
		for (CollectType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
