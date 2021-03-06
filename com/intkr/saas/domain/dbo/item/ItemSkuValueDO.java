package com.intkr.saas.domain.dbo.item;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2017-08-03 16:44:23
 * @version 1.0
 */
public class ItemSkuValueDO extends BaseDO {

	private Long saasId;

	// SKU ID
	private Long skuId;

	// 属性选项ID
	private Long valueId;

	// 权重
	private Double sort;

	// 备注
	private String note;

	// 拓展字段
	private String feature;

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long getValueId() {
		return valueId;
	}

	public void setValueId(Long valueId) {
		this.valueId = valueId;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
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

}