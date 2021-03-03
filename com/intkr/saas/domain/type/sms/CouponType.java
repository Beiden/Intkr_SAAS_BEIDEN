package com.intkr.saas.domain.type.sms;

/**
 * 优惠券类型
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:04:45
 * @version 1.0
 */
public enum CouponType {

	Coupon("coupon", "优惠券"), //
	CashCoupon("cashCoupon", "现金券"), //
	ResistCoupon("resistCoupon", "抵用券"), //
	VoucherCoupon("voucherCoupon", "代金券"), //
	TryCoupon("tryCoupon", "体验券"), //
	GiftCoupon("giftCoupon", "礼品券"), //
	DiscountCoupon("discountCoupon", "折扣券"), //
	SpecialCoupon("specialCoupon", "特价券"), //
	RedeemCoupon("redeemCoupon", "换购券"), //
	RedPaper("redPaper", "优惠红包"), //
	DiscountRedPaper("discountRedPaper", "折扣红包"), //
	CashRedPaper("cashRedPaper", "现金红包"), //
	Error("error", "异常"); //

	private CouponType(String code, String name) {
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

	public static CouponType getByCode(String code) {
		CouponType[] values = values();
		for (CouponType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
