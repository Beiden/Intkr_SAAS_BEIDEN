package com.intkr.saas.domain.dbo.order;

import java.util.Date;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2016-6-10 上午8:01:54
 * @version 1.0
 */
public class OrderTimeLineDO extends BaseDO {

	private Long saasId;

	// 订单ID
	private Long orderId;

	// 用户ID
	private Long userId;

	// 类型
	private String type;

	// 备注
	private String note;

	// 时间
	private Date time;

	private Long long1;

	private Long long2;

	private Long long3;

	// 拓展字段
	private String feature;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public Long getLong1() {
		return long1;
	}

	public void setLong1(Long long1) {
		this.long1 = long1;
	}

	public Long getLong2() {
		return long2;
	}

	public void setLong2(Long long2) {
		this.long2 = long2;
	}

	public Long getLong3() {
		return long3;
	}

	public void setLong3(Long long3) {
		this.long3 = long3;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}
