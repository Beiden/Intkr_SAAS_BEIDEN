package com.intkr.saas.domain.dbo.shopping;

import com.intkr.saas.domain.BaseDO;

/**
 * 店铺收藏
 * 
 * @table shop_collect
 * 
 * @author Beiden
 * @date 2020-11-14 17:59:24
 * @version 1.0
 */
public class ShopCollectDO extends BaseDO<ShopCollectDO> {

	private static final long serialVersionUID = 1L;

	private Long saasId;

	// 帐号
	private Long userId;

	// 店铺ID
	private Long shopId;

	// 拓展字段
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

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

}
