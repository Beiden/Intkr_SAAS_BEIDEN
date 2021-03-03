package com.intkr.saas.domain.dbo.sms;

import com.intkr.saas.domain.BaseDO;

/**
 * 品牌推荐
 * 
 * @table item_brand_recommend
 * 
 * @author Beiden
 * @date 2020-11-11 22:44:12
 * @version 1.0
 */
public class ItemBrandRecommendDO extends BaseDO<ItemBrandRecommendDO> {

	private static final long serialVersionUID = 1L;

	private Long saasId;

	// 品牌ID
	private Long brandId;

	// 品牌名称
	private String name;

	// note
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

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
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
