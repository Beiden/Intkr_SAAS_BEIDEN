package com.intkr.saas.domain.dbo.item;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2017-08-03 16:40:03
 * @version 1.0
 */
public class ItemFcategoryDO extends BaseDO {

	private Long saasId;

	// 状态
	private String status;

	// 父类目
	private Long pid;

	// URL
	private String url;

	// 后台类目ID
	private String ibcIds;

	// 名称
	private String name;

	// 排序
	private Double sort;

	// 描述
	private String note;

	//
	private String feature;

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
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

	public String getIbcIds() {
		return ibcIds;
	}

	public void setIbcIds(String ibcIds) {
		this.ibcIds = ibcIds;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}