package com.intkr.saas.domain.bo.sms;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.item.ItemCategoryBO;
import com.intkr.saas.util.JsonUtil;

/**
 * 优惠券
 * 
 * @table item_coupon_activity
 * 
 * @author Beiden
 * @date 2020-11-11 21:30:03
 * @version 1.0
 */
public class ItemCouponActivityBO extends BaseBO<ItemCouponActivityBO> {

	private static final long serialVersionUID = 1L;

	private Long saasId;

	// 店铺ID
	private Long shopId;

	// 活动ID
	private Long activityId;

	// 名称
	private String name;

	// 类型
	private String type;

	// 发放方式
	private String sendType;

	// 使用方式
	private String useType;

	// 使用平台
	private String usePlatform;

	// 使用门槛：满*可用
	private Long useMoney;

	// 面额
	private Long money;

	// 状态
	private String status;

	// 开始时间
	private Date startTime;

	// 结束时间
	private Date endTime;

	// 备注
	private String note;

	// 总数
	private Integer count;

	// 领取数量
	private Integer receiveCount;

	// 已使用数量
	private Integer useCount;

	// 权重
	private Double sort;

	// 拓展字段
	private String feature;
	
	public List<ItemBO> items;
	
	private List<ItemCategoryBO> categorys;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getUsePlatform() {
		return usePlatform;
	}

	public void setUsePlatform(String usePlatform) {
		this.usePlatform = usePlatform;
	}

	public Long getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(Long useMoney) {
		this.useMoney = useMoney;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getReceiveCount() {
		return receiveCount;
	}

	public void setReceiveCount(Integer receiveCount) {
		this.receiveCount = receiveCount;
	}

	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
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

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public List<ItemBO> getItems() {
		return items;
	}

	public void setItems(List<ItemBO> items) {
		this.items = items;
	}

	public List<ItemCategoryBO> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<ItemCategoryBO> categorys) {
		this.categorys = categorys;
	}

}
