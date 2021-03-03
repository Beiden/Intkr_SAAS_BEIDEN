package com.intkr.saas.domain.dbo.article;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 09:32:27
 * @version 1.0
 */
public class ArticleCategoryDO extends BaseDO {

	private Long saasId;

	//
	private Long pid;

	//
	private String name;

	//
	private Double sort;

	//
	private String note;

	//
	private String feature;

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}