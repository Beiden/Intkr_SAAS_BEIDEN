package com.intkr.saas.domain.bo.item;

import java.util.List;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 16:24:59
 * @version 1.0
 */
public class ItemSpuTemplateBO extends BaseBO {

	private Long saasId;

	// 类目ID
	private Long categoryId;

	// 属性ID
	private Long propertyId;

	// 权重
	private Double sort;

	// 备注
	private String note;

	// 拓展字段
	private String feature;

	private ItemPropertyBO property;

	private List<ItemSpuTemplateValueBO> spuTemplateValueList;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

	public List<ItemSpuTemplateValueBO> getSpuTemplateValueList() {
		return spuTemplateValueList;
	}

	public void setSpuTemplateValueList(List<ItemSpuTemplateValueBO> spuTemplateValueList) {
		this.spuTemplateValueList = spuTemplateValueList;
	}

	public ItemPropertyBO getProperty() {
		return property;
	}

	public void setProperty(ItemPropertyBO property) {
		this.property = property;
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
