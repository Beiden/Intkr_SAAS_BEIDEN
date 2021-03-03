package com.intkr.saas.domain.dbo.item;

import com.intkr.saas.domain.BaseDO;

/**
 * 商品标签
 * 
 * @table item_tag
 * 
 * @author Beiden
 * @date 2020-11-02 09:54:32
 * @version 1.0
 */
public class ItemTagDO extends BaseDO<ItemTagDO> {

	private static final long serialVersionUID = 1L;

	//
	private Long saasId;

	// 名称
	private String name;

	// 图像描述
	private String imgDesc;

	// 属性
	private String feature;

	// 描述
	private String note;

	private Double sort;

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgDesc() {
		return imgDesc;
	}

	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
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

}
