package com.intkr.saas.domain.bo.order;

import java.util.HashMap;
import java.util.Map;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.item.ItemSkuBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.util.JsonUtil;

/**
 * 
 * @author Beiden
 * @date 2011-4-18 上午10:34:43
 * @version 1.0
 */
public class OrderDetailBO extends BaseBO {

	private Long saasId;

	// 相关对象ID
	private Long relatedId;

	// 类型
	private String type;

	// 店铺ID
	private Long shopId;

	// 订单ID
	private Long orderId;

	// 商品ID
	private Long itemId;

	// SKU ID
	private Long skuId;

	// 单价
	private Long unitPrice;

	// 价格
	private Long price;

	// 数量
	private Integer count;

	// 拓展字段
	private String feature;

	// 是否已评价
	private Integer isEvaluate;

	private UserBO merchant;

	private OrderBO order;

	private ItemBO item;

	private ItemSkuBO itemSku;

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

	public Long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Long unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public UserBO getMerchant() {
		return merchant;
	}

	public void setMerchant(UserBO merchant) {
		this.merchant = merchant;
	}

	public Integer getIsEvaluate() {
		return isEvaluate;
	}

	public void setIsEvaluate(Integer isEvaluate) {
		this.isEvaluate = isEvaluate;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public OrderBO getOrder() {
		return order;
	}

	public void setOrder(OrderBO order) {
		this.order = order;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public Long getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(Long relatedId) {
		this.relatedId = relatedId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public ItemBO getItem() {
		return item;
	}

	public void setItem(ItemBO item) {
		this.item = item;
	}

	public ItemSkuBO getItemSku() {
		return itemSku;
	}

	public void setItemSku(ItemSkuBO itemSku) {
		this.itemSku = itemSku;
	}

}
