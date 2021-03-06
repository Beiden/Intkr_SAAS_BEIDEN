package com.intkr.saas.domain.type.item;

/**
 * 
 * @author Beiden
 * @date 2016-4-16 下午3:13:20
 * @version 1.0
 */
public enum DemandTradeMethod {

	FixedPrice("fixedPrice", "一口价"), //
	Bids("bids", "招标"), //
	Error("error", "异常"); //

	private DemandTradeMethod(String code, String name) {
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

	public static DemandTradeMethod getByCode(String code) {
		DemandTradeMethod[] values = values();
		for (DemandTradeMethod value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
