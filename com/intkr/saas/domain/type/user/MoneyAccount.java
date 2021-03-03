package com.intkr.saas.domain.type.user;

/**
 * 
 * @author Beiden
 * @date 2011-10-17 下午4:17:26
 * @version 1.0
 */
public enum MoneyAccount {

	CBC("CBC", "中国建设银行"), //
	BC("BC", "中国银行"), //
	ABC("ABC", "中国农业银行"), //
	ICBC("ICBC", "工商银行"), //
	PBC("PBC", "中国人民银行"), //
	CEB("CEB", "中国光大银行"), //
	CMB("CMB", "中国招商银行"), //
	CITIC("CITIC", "中信实业银行"), //
	BCCB("BCCB", "北京市商业银行"), //
	CTB("CTB", "中国交通银行"), //
	CUB("CUB", "中国农村信用社"), //
	HXB("HXB", "华夏银行"), //
	CMBC("CMBC", "中国民生银行"), //
	CIB("CIB", "兴业银行"), //

	Yuepay("yuepay", "余额"), //
	Jdpay("jdpay", "京东钱包"), //
	Alipay("alipay", "支付宝"), //
	Tenpay("tenpay", "财付通"), //
	Wxpay("wxpay", "微信钱包"), //
	OffLinePay("offLinePay", "线下支付"), //
	BankPay("bankPay", "银行卡支付"), //
	Error("error", "异常"); //

	private MoneyAccount(String code, String name) {
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
	
	public static MoneyAccount getByCode(String code) {
		MoneyAccount[] values = values();
		for (MoneyAccount value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
