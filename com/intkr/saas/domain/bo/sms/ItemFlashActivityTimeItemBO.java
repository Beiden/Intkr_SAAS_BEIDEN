package com.intkr.saas.domain.bo.sms;

import java.util.HashMap;
import java.util.Map;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.util.JsonUtil;

/**
 * 秒杀活动
 * 
 * @table item_flash_activity_time_item
 * 
 * @author Beiden
 * @date 2020-11-11 20:30:46
 * @version 1.0
 */
public class ItemFlashActivityTimeItemBO extends BaseBO<ItemFlashActivityTimeItemBO> {

	private static final long serialVersionUID = 1L;

	private Long saasId;

	// 店铺ID
	private Long shopId;

	// 活动ID
	private Long activityId;

	// 时间ID
	private Long timeId;

	// 商品ID
	private Long itemId;

	// 商品名称
	private String name;

	// 价格
	private Long price;

	// 数量
	private Long count;

	// 秒杀价格
	private Long flashPrice;

	// 秒杀数量
	private Long flashCount;

	// 秒杀限制数量
	private Long flashLimitCount;

	// 状态
	private String status;

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

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getTimeId() {
		return timeId;
	}

	public void setTimeId(Long timeId) {
		this.timeId = timeId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Long getFlashPrice() {
		return flashPrice;
	}

	public void setFlashPrice(Long flashPrice) {
		this.flashPrice = flashPrice;
	}

	public Long getFlashCount() {
		return flashCount;
	}

	public void setFlashCount(Long flashCount) {
		this.flashCount = flashCount;
	}

	public Long getFlashLimitCount() {
		return flashLimitCount;
	}

	public void setFlashLimitCount(Long flashLimitCount) {
		this.flashLimitCount = flashLimitCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

}
