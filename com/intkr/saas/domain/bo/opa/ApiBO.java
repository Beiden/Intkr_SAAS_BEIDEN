package com.intkr.saas.domain.bo.opa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.util.JsonUtil;

/**
 * 
 * 
 * @table api_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
public class ApiBO extends BaseBO<ApiBO> {

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

	private ApiBO parent;

	private List<ApiBO> childs;

	private List<ApiParamBO> params;

	private ApiExtBO apiExt;

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

	public Object getFeature(String key) {
		Map<String, Object> map = JsonUtil.toObject(feature, Map.class);
		if (map == null) {
			return null;
		}
		return map.get(key);
	}

	public void parseFeature() {
		if (getProperties() == null) {
			setProperties(new HashMap<String, Object>());
		}
		Map<String, Object> map = JsonUtil.toObject(feature, Map.class);
		if (map == null) {
			return;
		}
		for (String key : map.keySet()) {
			this.setProperty(key, map.get(key));
		}
	}

	public void setFeature(String key, Object value) {
		Map<String, Object> map = JsonUtil.toObject(feature, Map.class);
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		map.put(key, value);
		this.feature = JsonUtil.toJson(map);
	}

	public ApiBO getParent() {
		return parent;
	}

	public void setParent(ApiBO parent) {
		this.parent = parent;
	}

	public List<ApiBO> getChilds() {
		return childs;
	}

	public void setChilds(List<ApiBO> childs) {
		this.childs = childs;
	}

	public void addChild(ApiBO child) {
		if (this.childs == null) {
			this.childs = new ArrayList<ApiBO>();
		}
		this.childs.add(child);
	}

	public List<ApiParamBO> getParams() {
		return params;
	}

	public void setParams(List<ApiParamBO> params) {
		this.params = params;
	}

	public ApiExtBO getApiExt() {
		return apiExt;
	}

	public void setApiExt(ApiExtBO apiExt) {
		this.apiExt = apiExt;
	}

}
