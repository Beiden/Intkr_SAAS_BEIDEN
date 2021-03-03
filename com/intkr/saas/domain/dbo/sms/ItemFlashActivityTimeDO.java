package com.intkr.saas.domain.dbo.sms;

import java.util.Date;

import com.intkr.saas.domain.BaseDO;

/**
 * 秒杀活动
 * 
 * @table item_flash_activity_time
 * 
 * @author Beiden
 * @date 2020-11-11 20:30:39
 * @version 1.0
 */
public class ItemFlashActivityTimeDO extends BaseDO<ItemFlashActivityTimeDO> {

	private static final long serialVersionUID = 1L;

	private Long saasId;

	// 店铺ID
	private Long shopId;

	// 活动ID
	private Long activityId;

	// 名称
	private String name;

	// 状态
	private String status;

	// 开始时间
	private Date startTime;

	// 结束时间
	private Date endTime;

	// 商品数量
	private Long itemCount;

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

	public Long getItemCount() {
		return itemCount;
	}

	public void setItemCount(Long itemCount) {
		this.itemCount = itemCount;
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

}
