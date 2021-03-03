package com.intkr.saas.domain.dbo.sys;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Administrator
 * 
 */
public class StatisticsDO extends BaseDO {

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

}
