package com.intkr.saas.domain.type.order;

/**
 * 
 * @author Beiden
 * @date 2016-6-4 上午10:49:50
 * @version 1.0
 */
public enum OrderFromType {

	BuyNow("buyNow", "立即购买"), //
	ShoppingCart("shoppingCart", "购物车结算"), //
	Error("error", "异常");

	public String code;

	public String name;

	private OrderFromType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static OrderFromType getByNameEn(String nameEn) {
		if (nameEn == null || "".equals(nameEn)) {
			return OrderFromType.Error;
		}
		return OrderFromType.Error;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static OrderFromType getByCode(String code) {
		OrderFromType[] values = values();
		for (OrderFromType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
