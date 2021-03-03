package com.intkr.saas.domain.type.order;

/**
 * 订单明细类型
 * 
 * @author Beiden
 * @date 2011-8-22 上午8:09:57
 * @version 1.0
 */
public enum OrderStatus {

	WaitConfirm("waitConfirm", "待确认"), //
	WaitPay("waitPay", "待付款"), //
	WaitSendOut("waitSendOut", "待发货"), //
	WaitReceipt("waitReceipt", "待收货"), //
	WaitComment("waitComment", "待评价"), //
	Finished("finished", "已完成"), //
	Cancel("cancel", "已取消"), //
	Error("error", "异常");

	public String code;

	public String name;

	private OrderStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static OrderStatus getByNameEn(String nameEn) {
		if (nameEn == null || "".equals(nameEn)) {
			return OrderStatus.Error;
		}
		return OrderStatus.Error;
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

	public static OrderStatus getByCode(String code) {
		OrderStatus[] values = values();
		for (OrderStatus value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
