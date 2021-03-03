package com.intkr.saas.domain.dbo.item;

import com.intkr.saas.domain.BaseDO;

/**
 * 品牌
 * 
 * @table item_brand
 * 
 * @author Beiden
 * @date 2020-11-11 22:33:08
 * @version 1.0
 */
public class ItemBrandDO extends BaseDO<ItemBrandDO> {

	private static final long serialVersionUID = 1L;

	private Long saasId;

	// 商品类目
	private Long categoryId;

	// 品牌名称
	private String name;

	// 备注
	private String note;

	// 权重
	private Double sort;

	// 拓展字段
	private String feature;

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
