package com.intkr.saas.domain.bo.article;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 12:21:47
 * @version 1.0
 */
public class ArticleTagRelatedBO extends BaseBO {

	private Long saasId;

	//
	private Long tagId;

	//
	private Long relatedId;

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
