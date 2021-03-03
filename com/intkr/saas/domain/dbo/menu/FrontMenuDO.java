package com.intkr.saas.domain.dbo.menu;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2011-9-30 下午7:41:33
 * @version 1.0
 */
public class FrontMenuDO extends BaseDO {

	private Long saasId;

	private String name;

	private String feature;

	private String note;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}
