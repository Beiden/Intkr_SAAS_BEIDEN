package com.intkr.saas.domain.dbo.mms;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 19:03:07
 * @version 1.0
 */
public class MsgWarningDO extends BaseDO {

	// 名称
	private String name;

	// 类型
	private String type;

	// 状态
	private String status;

	// 备注
	private String note;

	// 属性
	private String feature;

	// 排序
	private Double sort;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
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