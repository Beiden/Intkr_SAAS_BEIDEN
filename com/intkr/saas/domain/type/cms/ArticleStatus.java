package com.intkr.saas.domain.type.cms;

/**
 * 
 * @author Beiden
 * @date 2011-10-18 下午2:30:44
 * @version 1.0
 */
public enum ArticleStatus {

	Draft("draft", "草稿"), //
	WaitAudit("waitAudit", "等待审核"), //
	AuditFail("auditFail", "审核失败"), //
	WaitReaudit("waitReaudit", "等待复审"), //
	ReauditPass("reauditFail", "复审失败"), //
	Timing("timing", "定时发布"), //
	Public("public", "已发布"), //
	Error("error", "异常"); //

	private ArticleStatus(String code, String name) {
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
	
	public static ArticleStatus getByCode(String code) {
		ArticleStatus[] values = values();
		for (ArticleStatus value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
