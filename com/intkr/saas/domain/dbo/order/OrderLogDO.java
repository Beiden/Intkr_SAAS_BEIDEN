package com.intkr.saas.domain.dbo.order;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2016-6-4 下午2:21:24
 * @version 1.0
 */
public class OrderLogDO extends BaseDO {

	private Long saasId;

	// 用户ID
	private Long userId;

	// 类型
	private String type;

	// 订单ID
	private Long orderId;

	// 拓展字段
	private String feature;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

}
