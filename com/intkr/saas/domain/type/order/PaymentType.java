package com.intkr.saas.domain.type.order;

/**
 * 付款类型
 * 
 * @author Beiden
 * @date 2011-8-22 上午8:09:57
 * @version 1.0
 */
public enum PaymentType {

	Alipay("alipay", "支付宝"), //
	Wxpay("wxpay", "微信支付"), //
	Jdpay("jdpay", "京东钱包"), //
	Yuepay("yuepay", "余额支付"), //
	OffLinePay("offLinePay", "线下支付"), //
	Bankpay("bankpay", "银行卡支付"), //
	Error("error", "异常");

	public String code;

	public String name;

	private PaymentType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static PaymentType getByNameEn(String nameEn) {
		if (nameEn == null || "".equals(nameEn)) {
			return PaymentType.Error;
		}
		return PaymentType.Error;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static PaymentType getByCode(String code) {
		PaymentType[] values = values();
		for (PaymentType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
