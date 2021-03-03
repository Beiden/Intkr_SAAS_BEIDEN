package com.intkr.saas.domain.type.shop;

/**
 * 运费模版：计价方式
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:04:00
 * @version 1.0
 */
public enum ShippingValuateType {

	Count("count", "按件数"), //
	Weight("weight", "按重量"), //
	Volumn("volumn", "按体积"), //
	Error("error", "异常"); //

	private ShippingValuateType(String code, String name) {
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

	public static ShippingValuateType getByCode(String code) {
		ShippingValuateType[] values = values();
		for (ShippingValuateType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
