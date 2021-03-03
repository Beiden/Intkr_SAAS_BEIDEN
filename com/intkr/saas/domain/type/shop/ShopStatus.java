package com.intkr.saas.domain.type.shop;

/**
 * 
 * @author Beiden
 * @date 2016-5-10 下午9:34:40
 * @version 1.0
 */
public enum ShopStatus {

	WaitAudit("waitAudit", "待审核"), //
	unapprove("unapprove", "驳回"), //
	Approve("approve", "批准"), //
	Disable("disable", "禁用"), //
	Normal("normal", "正常"), //
	Error("error", "异常"); //

	private ShopStatus(String code, String name) {
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
	
	public static ShopStatus getByCode(String code) {
		ShopStatus[] values = values();
		for (ShopStatus value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
