package com.intkr.saas.domain.type.item;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:04:00
 * @version 1.0
 */
public enum BidsType {

	Normal("normal", "普通"), //
	Item("item", "商品"), //
	Demand("demand", "需求"), //
	Logistics("logistics", "订单物流需求"), //
	Error("error", "异常"); //

	private BidsType(String code, String name) {
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

	public static BidsType getByCode(String code) {
		BidsType[] values = values();
		for (BidsType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
