package com.intkr.saas.domain.bo.opa;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * 
 * @table app_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:33
 * @version 1.0
 */
public class AppBO extends BaseBO<AppBO> {

	private static final long serialVersionUID = 1L;

	// name
	private String name;

	// domain
	private String domain;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

}
