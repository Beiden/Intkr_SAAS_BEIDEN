package com.intkr.saas.domain.type.user;

/**
 * 
 * @author Beiden
 * @date 2016-4-15 下午1:06:43
 * @version 1.0
 */
public enum MoneyFlowStatus {

	WaitAudit("waitAudit", "待审核"), //
	WaitPay("waitPay", "待付款"), //
	WaitArrive("waitArrive", "待到账"), //
	Finished("finished", "已完成"), //
	Canceled("canceled", "已取消"), //
	Error("error", "异常");

	private MoneyFlowStatus(String code, String name) {
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

	public static MoneyFlowStatus getByCode(String code) {
		MoneyFlowStatus[] values = values();
		for (MoneyFlowStatus value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
