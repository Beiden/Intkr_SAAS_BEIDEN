package com.intkr.saas.domain.bo.shop;

import java.util.HashMap;
import java.util.Map;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.util.JsonUtil;

/**
 * 
 * @author Beiden
 * @date 2017-09-07 18:09:37
 * @version 1.0
 */
public class ShopComplaintBO extends BaseBO {

	private Long saasId;

	// 类型
	private String type;

	// 店铺ID
	private Long shopId;

	// 商品ID
	private Long itemId;

	// 帐号
	private Long userId;

	// 状态
	private String status;

	// 标题
	private String title;

	// 正文
	private String content;

	// 拓展字段
	private String feature;

	private ShopBO shop;

	private ItemBO item;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
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

	public ItemBO getItem() {
		return item;
	}

	public void setItem(ItemBO item) {
		this.item = item;
	}

}
