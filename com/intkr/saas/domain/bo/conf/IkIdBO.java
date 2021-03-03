package com.intkr.saas.domain.bo.conf;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2016-07-31 18:04:42
 * @version 1.0
 */
public class IkIdBO extends BaseBO {

	private String tableName;

	// ID已被使用的值
	private Long code;

	// 备注
	private String note;

	// 权重
	private Double sort;

	// 拓展字段
	private String feature;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

}
