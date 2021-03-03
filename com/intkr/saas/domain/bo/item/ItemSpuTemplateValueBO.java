package com.intkr.saas.domain.bo.item;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 16:25:07
 * @version 1.0
 */
public class ItemSpuTemplateValueBO extends BaseBO {

	private Long saasId;

	// 类目属性ID
	private Long spuTemplateId;

	// 属性选项ID
	private Long valueId;

	// 权重
	private Double sort;

	// 备注
	private String note;

	// 拓展字段
	private String feature;

	private ItemPropertyValueBO propertyValue;

	public Long getSpuTemplateId() {
		return spuTemplateId;
	}

	public void setSpuTemplateId(Long spuTemplateId) {
		this.spuTemplateId = spuTemplateId;
	}

	public Long getValueId() {
		return valueId;
	}

	public void setValueId(Long valueId) {
		this.valueId = valueId;
	}

	public ItemPropertyValueBO getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(ItemPropertyValueBO propertyValue) {
		this.propertyValue = propertyValue;
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
