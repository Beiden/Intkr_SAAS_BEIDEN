package com.intkr.saas.domain.dbo.shop;

import java.util.Date;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午10:06:24
 * @version 1.0
 */
public class ShopActivityDO extends BaseDO {

	private Long saasId;

	// 店铺ID
	private Long shopId;

	// 用户ID
	private Long userId;

	// 类型
	private String type;

	// 名称
	private String name;

	// 状态
	private String status;

	// 省
	private String province;

	// 城市
	private String city;

	// 地区
	private String area;

	// 地址明细
	private String addressDetail;

	// 封面图片
	private Long coverImgId;

	// 内容
	private String content;

	// 开始时间
	private Date startTime;

	// 结束时间
	private Date endTime;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCoverImgId() {
		return coverImgId;
	}

	public void setCoverImgId(Long coverImgId) {
		this.coverImgId = coverImgId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

}
