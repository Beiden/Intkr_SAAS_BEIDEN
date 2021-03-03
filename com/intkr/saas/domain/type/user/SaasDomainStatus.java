package com.intkr.saas.domain.type.user;

/**
 * 
 * @author Beiden
 * @date 2016-4-17 上午10:50:22
 * @version 1.0
 */
public enum SaasDomainStatus {

	On("on", "启用"), //
	Off("off", "禁用"), //

	Error("error", "异常"); //

	private SaasDomainStatus(String code, String name) {
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

	public static SaasDomainStatus getByCode(String code) {
		SaasDomainStatus[] statuss = values();
		for (SaasDomainStatus status : statuss) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return Error;
	}

}
