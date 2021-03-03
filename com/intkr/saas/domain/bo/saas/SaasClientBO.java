package com.intkr.saas.domain.bo.saas;

import java.util.List;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2017-03-19 22:00:49
 * @version 1.0
 */
public class SaasClientBO extends BaseBO {

	// 名称
	private String name;

	// 备注
	private String note;

	// 是否默认
	private Integer isDefault;

	// 权重
	private Double sort;

	// 拓展字段
	private String feature;

	private SaasConfigBO config;

	private List<SaasDomainBO> domains;

	private SaasThemeBO theme;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public SaasConfigBO getConfig() {
		return config;
	}

	public void setConfig(SaasConfigBO config) {
		this.config = config;
	}

	public List<SaasDomainBO> getDomains() {
		return domains;
	}

	public void setDomains(List<SaasDomainBO> domains) {
		this.domains = domains;
	}

	public SaasThemeBO getTheme() {
		return theme;
	}

	public void setTheme(SaasThemeBO theme) {
		this.theme = theme;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

}
