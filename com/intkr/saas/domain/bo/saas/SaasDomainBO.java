package com.intkr.saas.domain.bo.saas;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.type.user.SaasDomainStatus;

/**
 * 
 * @author Beiden
 * @date 2017-07-20 14:55:37
 * @version 1.0
 */
public class SaasDomainBO extends BaseBO {

	private Long saasId;

	//
	private String name;

	// 域名
	private String domain;

	// 状态
	private String status;

	// 备注
	private String note;

	// 主题
	private Long themeId;

	// 主题名
	private String themeName;
	
	private String feature;

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getStatus() {
		return status;
	}

	public String getStatusCn() {
		return SaasDomainStatus.getByCode(getStatus()).getName();
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

	public Long getThemeId() {
		return themeId;
	}

	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

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

}
