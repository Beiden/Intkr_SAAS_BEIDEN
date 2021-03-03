package com.intkr.saas.domain.type.item;

/**
 * 
 * @author Beiden
 * @date 2016-3-5 上午10:32:38
 * @version 1.0
 */
public enum AuctionType {

	Normal("normal", "普通竞拍"), //
	Item("item", "商品竞拍"), //
	Error("error", "异常"); //

	private AuctionType(String code, String name) {
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

	public static AuctionType getByCode(String code) {
		AuctionType[] values = values();
		for (AuctionType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
