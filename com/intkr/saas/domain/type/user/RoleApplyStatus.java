package com.intkr.saas.domain.type.user;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:04:45
 * @version 1.0
 */
public enum RoleApplyStatus {

	WaitSubmit("waitSubmit", "待提交资料"), //
	WaitAudit("waitAudit", "待审核"), //
	WaitFreezeDeposit("waitFreezeDeposit", "待冻结资金"), //
	unapprove("unapprove", "驳回"), //
	Success("success", "成功"), //
	Error("error", "异常"); //

	private RoleApplyStatus(String code, String name) {
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
	
	public static RoleApplyStatus getByCode(String code) {
		RoleApplyStatus[] values = values();
		for (RoleApplyStatus value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
