package com.intkr.saas.domain.dbo.shop;

import com.intkr.saas.domain.BaseDO;

/**
 * 店铺申请
 * 
 * @table shop_apply
 * 
 * @author Beiden
 * @date 2020-11-10 22:09:23
 * @version 1.0
 */
public class ShopApplyDO extends BaseDO<ShopApplyDO> {

	private static final long serialVersionUID = 1L;

	private Long saasId;

	// 用户ID
	private Long userId;

	// 店铺类型
	private String shopType;

	// 店铺名称
	private String shopName;

	// 状态
	private String status;

	// 备注
	private String note;

	// 拓展
	private String feature;

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
