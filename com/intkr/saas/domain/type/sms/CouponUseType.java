package com.intkr.saas.domain.type.sms;

/**
 * 优惠券使用方式
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:04:45
 * @version 1.0
 */
public enum CouponUseType {

	All("all", "全场通用"), //
	Category("category", "指定分类"), //
	Item("item", "指定商品"), //
	Error("error", "异常"); //

	private CouponUseType(String code, String name) {
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

	public static CouponUseType getByCode(String code) {
		CouponUseType[] values = values();
		for (CouponUseType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
