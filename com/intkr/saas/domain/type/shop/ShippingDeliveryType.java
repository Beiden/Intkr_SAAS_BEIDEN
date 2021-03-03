package com.intkr.saas.domain.type.shop;

/**
 * 运费模版：运送方式
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:04:00
 * @version 1.0
 */
public enum ShippingDeliveryType {

	Express("express", "快递"), //
	Ems("ems", "EMS"), //
	Mail("mail", "平邮"), //
	Error("error", "异常"); //

	private ShippingDeliveryType(String code, String name) {
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

	public static ShippingDeliveryType getByCode(String code) {
		ShippingDeliveryType[] values = values();
		for (ShippingDeliveryType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
