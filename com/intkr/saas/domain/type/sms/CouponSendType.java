package com.intkr.saas.domain.type.sms;

/**
 * 优惠券发放方式
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:04:45
 * @version 1.0
 */
public enum CouponSendType {

	All("all", "全场赠券"), //
	Buyer("buyer", "会员赠送"), //
	Buy("buy", "购物赠送"), //
	Register("register", "注册赠送"), //
	Person("person", "定员赠券"), //
	Error("error", "异常"); //

	private CouponSendType(String code, String name) {
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

	public static CouponSendType getByCode(String code) {
		CouponSendType[] values = values();
		for (CouponSendType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
