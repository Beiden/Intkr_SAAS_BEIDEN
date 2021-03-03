package com.intkr.saas.domain.type.item;

/**
 * 
 * @author Beiden
 * @date 2016-3-5 上午10:32:38
 * @version 1.0
 */
public enum ItemType {

	Normal("normal", "普通商品"), //
	Mining("mining", "矿业商品"), //
	Error("error", "异常"); //

	private ItemType(String code, String name) {
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

	public static ItemType getByCode(String code) {
		ItemType[] values = values();
		for (ItemType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
