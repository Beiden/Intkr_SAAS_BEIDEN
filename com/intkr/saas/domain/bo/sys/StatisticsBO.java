package com.intkr.saas.domain.bo.sys;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.intkr.saas.domain.BaseBO;

/**
 * 
 */
public class StatisticsBO extends BaseBO {

	private Long id;

	private String time;

	private String type;

	private Long num;

	private String feature;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	static Gson gson = new Gson();

	public Object getFeature(String key) {
		Map<String, Object> map = gson.fromJson(feature, Map.class);
		if (map == null) {
			return null;
		}
		return map.get(key);
	}

	public void setFeature(String key, Object value) {
		Map<String, Object> map = gson.fromJson(feature, Map.class);
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		map.put(key, value);
		this.feature = gson.toJson(map);
	}

}
