package com.intkr.saas.domain.dbo.link;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2011-10-2 下午9:42:35
 * @version 1.0
 */
public class LinkDO extends BaseDO {

	private Long saasId;

	// 类型
	private String type;

	// 名称
	private String name;

	// 链接
	private String url;

	// 权重
	private Double sort;

	// 备注
	private String note;

	// 拓展字段
	private String feature;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
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

}
