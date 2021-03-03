package com.intkr.saas.domain.bo.item;

import java.util.List;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 16:24:40
 * @version 1.0
 */
public class ItemSkuPropertyBO extends BaseBO {

	private Long saasId;

	// 商品ID
	private Long itemId;

	// 属性ID
	private Long propertyId;

	// 备注
	private String note;

	// 权重
	private Double sort;

	// 拓展字段
	private String feature;

	private ItemPropertyBO property;

	private List<ItemSkuPropertyValueBO> skuPropertyValues;

	private ItemBO item;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

	public ItemPropertyBO getProperty() {
		return property;
	}

	public void setProperty(ItemPropertyBO property) {
		this.property = property;
	}

	public List<ItemSkuPropertyValueBO> getSkuPropertyValues() {
		return skuPropertyValues;
	}

	public void setSkuPropertyValues(List<ItemSkuPropertyValueBO> spuValues) {
		this.skuPropertyValues = spuValues;
	}

	public ItemBO getItem() {
		return item;
	}

	public void setItem(ItemBO item) {
		this.item = item;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

}
