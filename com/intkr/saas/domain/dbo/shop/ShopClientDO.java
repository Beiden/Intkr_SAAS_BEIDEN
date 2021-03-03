package com.intkr.saas.domain.dbo.shop;

import com.intkr.saas.domain.BaseDO;

/**
 * 店铺客户
 * 
 * @table shop_client
 * 
 * @author Beiden
 * @date 2020-11-02 10:01:02
 * @version 1.0
 */
public class ShopClientDO extends BaseDO<ShopClientDO> {

	private static final long serialVersionUID = 1L;

	private Long saasId;

	// 店铺ID
	private Long shopId;

	// 用户ID
	private Long userId;

	// 类型
	private String type;

	// 标签
	private String tags;

	// 备注
	private String note;

	// 拓展字段
	private String feature;

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
