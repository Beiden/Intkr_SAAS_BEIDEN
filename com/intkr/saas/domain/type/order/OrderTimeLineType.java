package com.intkr.saas.domain.type.order;

/**
 * 
 * @author Beiden
 * @date 2016-6-10 上午10:01:28
 * @version 1.0
 */
public enum OrderTimeLineType {

	Order("order", "下单"), //
	Pay("pay", "支付"), //
	Cancel("cancel", "取消"), //
	SendOut("sendOut", "发货"), //
	Adjust("adjust", "结算调整"), //
	Voice("voice", "开票"), //
	Receipt("receipt", "收货"), //
	Comment("comment", "评价"), //
	CustServ("custServ", "售后"), //
	Returned("returned", "退换"), //
	Common("common", "关注"), //
	Delete("delete", "删除"), //
	Error("error", "异常"); //

	private OrderTimeLineType(String code, String name) {
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
	
	public static OrderTimeLineType getByCode(String code) {
		OrderTimeLineType[] values = values();
		for (OrderTimeLineType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
