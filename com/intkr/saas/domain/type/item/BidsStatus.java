package com.intkr.saas.domain.type.item;

/**
 * 
 * @author Beiden
 * @date 2016-3-5 上午10:32:38
 * @version 1.0
 */
public enum BidsStatus {

	WaitAudit("waitAudit", "待审核"), //
	unapprove("unapprove", "驳回"), //
	Approve("approve", "批准"), //

	PreHeat("preheat", "预热中"), //
	InProgress("inProgress", "进行中"), //
	WaitResult("waitResult", "待出结束"), //
	Result("result", "已出结束"), //
	Over("over", "结束"), //

	Error("error", "异常"); //

	private BidsStatus(String code, String name) {
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

	public static BidsStatus getByCode(String code) {
		BidsStatus[] statuss = values();
		for (BidsStatus status : statuss) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return Error;
	}

}
