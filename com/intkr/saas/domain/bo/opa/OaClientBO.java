package com.intkr.saas.domain.bo.opa;

import com.intkr.saas.util.JsonUtil;
import java.util.HashMap;
import java.util.Map;

import com.intkr.saas.domain.BaseBO;

/**
 * 客户端
 * 
 * @table oa_client
 * 
 * @author Beiden
 * @date 2020-11-04 20:18:06
 * @version 1.0
 */
public class OaClientBO extends BaseBO<OaClientBO> {

	private static final long serialVersionUID = 1L;

	//
	private Long saasId;

	private String name;

	private String status;

	// app_key
	private String appKey;

	// app_secret
	private String appSecret;

	// note
	private String note;

	//
	private String feature;

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
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

}
