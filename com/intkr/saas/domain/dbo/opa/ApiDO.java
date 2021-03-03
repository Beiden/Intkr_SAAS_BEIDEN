package com.intkr.saas.domain.dbo.opa;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * 
 * @table api_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
public class ApiDO extends BaseDO<ApiDO> {

	private static final long serialVersionUID = 1L;

	// name
	private String name;

	// title
	private String title;

	// pid
	private Long pid;

	// app_id
	private Long appId;

	// type
	private String type;

	//
	private String uri;

	//
	private String formMethod;

	//
	private Double sort;

	//
	private String feature;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getFormMethod() {
		return formMethod;
	}

	public void setFormMethod(String formMethod) {
		this.formMethod = formMethod;
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
