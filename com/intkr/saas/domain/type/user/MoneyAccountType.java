package com.intkr.saas.domain.type.user;

/**
 * 
 * @author Beiden
 * @date 2011-10-16 上午11:19:08
 * @version 1.0
 */
public enum MoneyAccountType {

	Shop("shop", "店铺"), //
	Account("account", "个人"), //
	Error("error", "异常"); //

	private MoneyAccountType(String code, String name) {
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

	public static MoneyAccountType getByCode(String code) {
		MoneyAccountType[] values = values();
		for (MoneyAccountType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
