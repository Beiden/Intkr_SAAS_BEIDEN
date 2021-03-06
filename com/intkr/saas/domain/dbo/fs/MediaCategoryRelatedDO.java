package com.intkr.saas.domain.dbo.fs;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2017-08-03 16:33:44
 * @version 1.0
 */
public class MediaCategoryRelatedDO extends BaseDO {

	private Long saasId;

	//
	private String type;

	//
	private Long categoryId;

	//
	private Long relatedId;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(Long relatedId) {
		this.relatedId = relatedId;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}