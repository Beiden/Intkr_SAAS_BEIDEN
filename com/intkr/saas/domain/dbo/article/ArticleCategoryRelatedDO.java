package com.intkr.saas.domain.dbo.article;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 13:24:49
 * @version 1.0
 */
public class ArticleCategoryRelatedDO extends BaseDO {

	private Long saasId;

	//
	private Long categoryId;

	//
	private Long articleId;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}