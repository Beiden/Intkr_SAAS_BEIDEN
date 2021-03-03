package com.intkr.saas.domain.type.sms;

/**
 * 优惠活动状态
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:04:45
 * @version 1.0
 */
public enum CouponActivityStatus {

	Wait("wait", "未开始"), //
	Opening("opening", "进行中"), //
	Finished("finished", "已过期 "), //
	Error("error", "异常"); //

	private CouponActivityStatus(String code, String name) {
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

	public static CouponActivityStatus getByCode(String code) {
		CouponActivityStatus[] values = values();
		for (CouponActivityStatus value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
