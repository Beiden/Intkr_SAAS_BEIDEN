package com.intkr.saas.domain.type.user;

/**
 * 
 * @author Beiden
 * @date 2016-4-15 下午1:06:43
 * @version 1.0
 */
public enum MoneyOperStatus {

	WaitAudit("waitAudit", "待审核"), //
	WaitForPay("waitForPay", "待付款"), //
	Finished("finished", "已完成"), //
	Error("error", "异常");

	private MoneyOperStatus(String code, String name) {
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
	
	public static MoneyOperStatus getByCode(String code) {
		MoneyOperStatus[] values = values();
		for (MoneyOperStatus value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
