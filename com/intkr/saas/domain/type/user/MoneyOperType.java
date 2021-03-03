package com.intkr.saas.domain.type.user;

/**
 * 
 * @author Beiden
 * @date 2011-10-16 上午11:19:08
 * @version 1.0
 */
public enum MoneyOperType {

	Freeze("freeze", "资金冻结"), //
	Unfreeze("unfreeze", "资金解冻"), //
	Income("income", "收入"), //
	Expend("expend", "支出"), //
	In("in", "入金"), //
	Out("out", "出金"), //
	TransferIn("transferIn", "转入"), //
	TransferOut("transferOut", "转出"), //
	Error("error", "异常"); //

	private MoneyOperType(String code, String name) {
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
	
	public static MoneyOperType getByCode(String code) {
		MoneyOperType[] values = values();
		for (MoneyOperType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
