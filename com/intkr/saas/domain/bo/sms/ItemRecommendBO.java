package com.intkr.saas.domain.bo.sms;

import com.intkr.saas.util.JsonUtil;
import java.util.HashMap;
import java.util.Map;

import com.intkr.saas.domain.BaseBO;

/**
 * 商品推荐
 * 
 * @table item_recommend
 * 
 * @author Beiden
 * @date 2020-11-11 23:10:14
 * @version 1.0
 */
public class ItemRecommendBO extends BaseBO<ItemRecommendBO> {

	private static final long serialVersionUID = 1L;

	private Long saasId;

	// 店铺ID
	private Long shopId;

	// 商品ID
	private Long itemId;

	// 名称
	private String name;

	// 状态
	private String status;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
