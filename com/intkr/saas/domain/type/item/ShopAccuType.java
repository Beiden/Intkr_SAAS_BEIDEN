package com.intkr.saas.domain.type.item;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:04:00
 * @version 1.0
 */
public enum ShopAccuType {

	Item("item", "商品咨询"), //
	Pay("pay", "支付问题"), //
	Invoice("invoice", "发票及保修"), //
	Promotion("promotion", "促销及赠品"), //
	Error("error", "异常"); //

	private ShopAccuType(String code, String name) {
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
	
	public static ShopAccuType getByCode(String code) {
		ShopAccuType[] values = values();
		for (ShopAccuType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
