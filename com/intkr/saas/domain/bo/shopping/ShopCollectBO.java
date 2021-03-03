package com.intkr.saas.domain.bo.shopping;

import java.util.HashMap;
import java.util.Map;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.util.JsonUtil;

/**
 * 店铺收藏
 * 
 * @table shop_collect
 * 
 * @author Beiden
 * @date 2020-11-14 17:59:24
 * @version 1.0
 */
public class ShopCollectBO extends BaseBO<ShopCollectBO> {

	private static final long serialVersionUID = 1L;

	private Long saasId;

	// 帐号
	private Long userId;

	// 店铺ID
	private Long shopId;

	// 拓展字段
	private String feature;

	private ShopBO shop;

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

	public Object getFeature(String key) {
		Map<String, Object> map = JsonUtil.toObject(feature, Map.class);
		if (map == null) {
			return null;
		}
		return map.get(key);
	}

	public void setFeature(String key, Object value) {
		Map<String, Object> map = JsonUtil.toObject(feature, Map.class);
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		map.put(key, value);
		this.feature = JsonUtil.toJson(map);
	}

	public ShopBO getShop() {
		return shop;
	}

	public void setShop(ShopBO shop) {
		this.shop = shop;
	}

}
