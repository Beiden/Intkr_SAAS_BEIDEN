package com.intkr.saas.domain.type.shop;

/**
 * 运费模版：计价方式
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:04:00
 * @version 1.0
 */
public enum ShippingFreeShipping {

	Fee("1", "自定义运费"), //
	Free("2", "卖家承担运费"), //
	Error("error", "异常"); //

	private ShippingFreeShipping(String code, String name) {
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

	public static ShippingFreeShipping getByCode(String code) {
		ShippingFreeShipping[] values = values();
		for (ShippingFreeShipping value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
