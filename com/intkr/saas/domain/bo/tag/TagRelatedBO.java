package com.intkr.saas.domain.bo.tag;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2011-9-30 下午6:05:02
 * @version 1.0
 */
public class TagRelatedBO extends BaseBO {

	private Long saasId;

	private Long tagId;

	private String type;

	private Long relatedId;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
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
