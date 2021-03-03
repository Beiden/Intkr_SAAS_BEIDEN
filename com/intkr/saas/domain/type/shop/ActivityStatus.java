package com.intkr.saas.domain.type.shop;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:04:45
 * @version 1.0
 */
public enum ActivityStatus {

	WaitAudit("waitAudit", "待审核"), //
	unapprove("unapprove", "驳回"), //
	Approve("approve", "批准"), //
	Error("error", "异常"); //

	private ActivityStatus(String code, String name) {
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
	
	public static ActivityStatus getByCode(String code) {
		ActivityStatus[] values = values();
		for (ActivityStatus value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
