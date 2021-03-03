package com.intkr.saas.domain.type.order;

/**
 * 订单明细类型
 * 
 * @author Beiden
 * @date 2011-8-22 上午8:09:57
 * @version 1.0
 */
public enum OrderSubStatus {

	AlipayAsynConfirmPay("alipayAsynConfirmPay", "支付宝异步确定已付款"), //
	AlipayConfirmPay("alipayConfirmPay", "支付宝同步确定已付款"), //
	JdpayAsynConfirmPay("jdpayAsynConfirmPay", "京东钱包异步确定已付款"), //
	JdpayConfirmPay("jdpayConfirmPay", "京东钱包同步确定已付款"), //
	WeiXinAsynConfirmPay("weiXinAsynConfirmPay", "微信异步确定已付款"), //
	WeiXinConfirmPay("weiXinConfirmPay", "微信同步确定已付款"), //
	MoneyConfirmPay("moneyConfirmPay", "余额已付款"), //
	Error("error", "异常");

	public String code;

	public String name;

	private OrderSubStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static OrderSubStatus getByNameEn(String nameEn) {
		if (nameEn == null || "".equals(nameEn)) {
			return OrderSubStatus.Error;
		}
		return OrderSubStatus.Error;
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
	
	public static OrderSubStatus getByCode(String code) {
		OrderSubStatus[] values = values();
		for (OrderSubStatus value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
