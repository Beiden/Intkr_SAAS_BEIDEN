package com.intkr.saas.domain.type.item;

/**
 * 
 * @author Beiden
 * @date 2016-4-15 上午11:23:09
 * @version 1.0
 */
public enum DemandType {

	Normal("normal", "普通需求"), //
	Logistics("logistics", "订单物流需求"), //
	Error("error", "异常"); //

	private DemandType(String code, String name) {
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

	public static DemandType getByCode(String code) {
		DemandType[] values = values();
		for (DemandType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
