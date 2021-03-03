package com.intkr.saas.domain.dbo.lang;

import com.intkr.saas.domain.BaseDO;

/**
 * 语言
 * 
 * @table language
 * 
 * @author Beiden
 * @date 2020-11-03 12:56:02
 * @version 1.0
 */
public class LanguageDO extends BaseDO<LanguageDO> {

	private static final long serialVersionUID = 1L;

	// 语言缩写
	private String langId;

	// 名称
	private String name;

	// 备注
	private String note;

	// 权重
	private Double sort;

	// 拓展字段
	private String feature;

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
