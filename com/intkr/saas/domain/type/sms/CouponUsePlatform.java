package com.intkr.saas.domain.type.sms;

/**
 * 优惠券适用平台
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:04:45
 * @version 1.0
 */
public enum CouponUsePlatform {

	ALL("all", "所有"), //
	Mobile("mobile", "移动"), //
	PC("pc", "PC"), //
	Error("error", "异常"); //

	private CouponUsePlatform(String code, String name) {
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

	public static CouponUsePlatform getByCode(String code) {
		CouponUsePlatform[] values = values();
		for (CouponUsePlatform value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
