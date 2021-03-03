package com.intkr.saas.domain.bo.log;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 上午10:19:57
 * @version 1.0
 */
public class SysLogBO extends BaseBO {

	private Long saasId;

	// IP地址
	private String ip;

	// 用户ID
	private Long userId;

	// 来源地址
	private String referer;

	// 访问地址
	private String goUrl;

	// 访问参数
	private String queryString;

	// 访问Action
	private String goAction;

	// 请求URL
	private String getUrl;

	// 服务器耗时
	private Long serverCostTime;

	// 客户端耗时
	private Long clientCostTime;

	// 参数
	private String params;

	// 拓展字段
	private String feature;

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

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
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

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}
