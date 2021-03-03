package com.intkr.saas.domain.dbo.article;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 12:19:25
 * @version 1.0
 */
public class ArticleTagDO extends BaseDO {

	private Long saasId;

	// 名称
	private String name;

	// 图像描述
	private String imgDesc;

	// 描述
	private String note;

	// 权重
	private Double sort;

	// 拓展字段
	private String feature;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgDesc() {
		return imgDesc;
	}

	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
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

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

}