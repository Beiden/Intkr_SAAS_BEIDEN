package com.intkr.saas.domain.dbo.shop;

import java.util.Date;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2016-4-22 上午10:09:58
 * @version 1.0
 */
public class ShopDO extends BaseDO {

	private Long saasId;

	// 用户ID
	private Long userId;

	// 店铺名称
	private String name;

	// 状态
	private String status;

	// 拓展字段
	private String feature;

	// 开店时间
	private Date openTime;

	// 综合评分
	private Double comprehensiveGrade;

	// 商品描述相符评分
	private Double describeMatchGrade;

	// 服务态度评分
	private Double serveAttitudeGrade;

	// 物流服务评分
	private Double deliverySpeedGrade;

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

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Double getComprehensiveGrade() {
		return comprehensiveGrade;
	}

	public void setComprehensiveGrade(Double comprehensiveGrade) {
		this.comprehensiveGrade = comprehensiveGrade;
	}

	public Double getDescribeMatchGrade() {
		return describeMatchGrade;
	}

	public void setDescribeMatchGrade(Double describeMatchGrade) {
		this.describeMatchGrade = describeMatchGrade;
	}

	public Double getServeAttitudeGrade() {
		return serveAttitudeGrade;
	}

	public void setServeAttitudeGrade(Double serveAttitudeGrade) {
		this.serveAttitudeGrade = serveAttitudeGrade;
	}

	public Double getDeliverySpeedGrade() {
		return deliverySpeedGrade;
	}

	public void setDeliverySpeedGrade(Double deliverySpeedGrade) {
		this.deliverySpeedGrade = deliverySpeedGrade;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}
