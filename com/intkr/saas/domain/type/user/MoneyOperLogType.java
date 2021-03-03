package com.intkr.saas.domain.type.user;

/**
 * 
 * @author Beiden
 * @date 2016-4-15 下午10:13:19
 * @version 1.0
 */
public enum MoneyOperLogType {

	RechargeApply("rechargeApply", "充值申请"), //
	WithdrawApply("withdrawApply", "提现申请"), //
	ApproveWithdrawApply("approveWithdrawApply", "通过提现申请"), //
	Error("error", "异常"); //

	private MoneyOperLogType(String code, String name) {
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
	
	public static MoneyOperLogType getByCode(String code) {
		MoneyOperLogType[] values = values();
		for (MoneyOperLogType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
