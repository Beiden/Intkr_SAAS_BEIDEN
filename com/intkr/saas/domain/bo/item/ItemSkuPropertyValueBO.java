package com.intkr.saas.domain.bo.item;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 16:24:48
 * @version 1.0
 */
public class ItemSkuPropertyValueBO extends BaseBO {

	private Long saasId;

	// spu ID
	private Long skuPropertyId;

	// 属性选项ID
	private Long valueId;

	// 名称
	private String name;

	// 图片ID
	private Long imgId;

	// 颜色
	private String color;

	// 权重
	private Double sort;

	// 备注
	private String note;

	// 拓展字段
	private String feature;

	private ItemPropertyValueBO propertyValue;

	private ItemSkuPropertyBO skuProperty;

	public Long getSkuPropertyId() {
		return skuPropertyId;
	}

	public void setSkuPropertyId(Long skuPropertyId) {
		this.skuPropertyId = skuPropertyId;
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

	public ItemSkuPropertyBO getSkuProperty() {
		return skuProperty;
	}

	public void setSkuProperty(ItemSkuPropertyBO spu) {
		this.skuProperty = spu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getImgId() {
		return imgId;
	}

	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

}
