package com.intkr.saas.domain.type.user;

/**
 * 
 * @author Beiden
 * @date 2011-10-16 上午11:19:08
 * @version 1.0
 */
public enum MoneyFlowType {

	Freeze("freeze", "冻结"), //
	Unfreeze("unfreeze", "解冻"), //
	Pay("pay", "支付"), //
	Refund("refund", "退款"), //
	Transfer("transfer", "转帐"), //
	Withdraw("withdraw", "提现"), //
	Recharge("recharge", "充值"), //
	Error("error", "异常"); //

	private MoneyFlowType(String code, String name) {
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

	public static MoneyFlowType getByCode(String code) {
		MoneyFlowType[] values = values();
		for (MoneyFlowType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
