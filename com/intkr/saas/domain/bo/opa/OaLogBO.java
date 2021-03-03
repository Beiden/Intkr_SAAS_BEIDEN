package com.intkr.saas.domain.bo.opa;

import com.intkr.saas.util.JsonUtil;
import java.util.HashMap;
import java.util.Map;

import com.intkr.saas.domain.BaseBO;

/**
 * 接口日志
 * 
 * @table oa_log
 * 
 * @author Beiden
 * @date 2020-11-04 20:43:19
 * @version 1.0
 */
public class OaLogBO extends BaseBO<OaLogBO> {

	private static final long serialVersionUID = 1L;

	// 
	private Long saasId;

	// 
	private String ip;

	// 
	private Long userId;

	// 
	private String referer;

	// 
	private String goUrl;

	// 
	private String queryString;

	// 
	private String goAction;

	// 
	private String getUrl;

	// 
	private Long serverCostTime;

	// 
	private Long clientCostTime;

	// 参数
	private String params;

	// 
	private String feature;

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getGoUrl() {
		return goUrl;
	}

	public void setGoUrl(String goUrl) {
		this.goUrl = goUrl;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getGoAction() {
		return goAction;
	}

	public void setGoAction(String goAction) {
		this.goAction = goAction;
	}

	public String getGetUrl() {
		return getUrl;
	}

	public void setGetUrl(String getUrl) {
		this.getUrl = getUrl;
	}

	public Long getServerCostTime() {
		return serverCostTime;
	}

	public void setServerCostTime(Long serverCostTime) {
		this.serverCostTime = serverCostTime;
	}

	public Long getClientCostTime() {
		return clientCostTime;
	}

	public void setClientCostTime(Long clientCostTime) {
		this.clientCostTime = clientCostTime;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
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

	public void setFeature(String key, Object value) {
		Map<String, Object> map = JsonUtil.toObject(feature, Map.class);
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		map.put(key, value);
		this.feature = JsonUtil.toJson(map);
	}

}
