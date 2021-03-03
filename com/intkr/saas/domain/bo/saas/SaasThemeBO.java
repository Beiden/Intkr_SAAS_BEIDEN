package com.intkr.saas.domain.bo.saas;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2017-03-19 10:45:08
 * @version 1.0
 */
public class SaasThemeBO extends BaseBO {

	// 封面
	private String cover;

	// 名称
	private String name;

	// 状态
	private String status;

	// 备注
	private String note;

	// 拓展字段
	private String feature;

	// 排序
	private Double sort;

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

}
