package com.intkr.saas.domain.dbo.sys;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Administrator
 * 
 */
public class MonitorDO extends BaseDO {

	private Long id;

	private String type;

	private String result;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
