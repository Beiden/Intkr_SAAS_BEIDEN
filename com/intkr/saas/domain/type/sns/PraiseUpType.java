package com.intkr.saas.domain.type.sns;

/**
 * 
 * @author Beiden
 * @date 2011-10-18 下午2:30:44
 * @version 1.0
 */
public enum PraiseUpType {

	Article("article", "文章"), //
	Item("item", "商品"), //
	Bids("bids", "招标"), //
	Auction("auction", "竞拍"), //
	Demand("demand", "需求"), //
	Page("page", "页面"), //
	Error("error", "异常"); //

	private PraiseUpType(String code, String name) {
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
	
	public static PraiseUpType getByCode(String code) {
		PraiseUpType[] values = values();
		for (PraiseUpType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
