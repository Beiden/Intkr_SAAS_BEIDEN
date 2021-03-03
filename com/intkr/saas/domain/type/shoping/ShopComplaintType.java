package com.intkr.saas.domain.type.shoping;

/**
 * 
 * @author Beiden
 * @date 2016-3-6 下午5:48:06
 * @version 1.0
 */
public enum ShopComplaintType {

	Shop("shop", "店铺"), //
	Item("item", "商品"), //
	Order("order", "订单"), //
	Error("error", "异常"); //

	private ShopComplaintType(String code, String name) {
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

	public static ShopComplaintType getByCode(String code) {
		ShopComplaintType[] values = values();
		for (ShopComplaintType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
