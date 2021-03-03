package com.intkr.saas.domain.dbo.order;

import java.util.Date;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2011-4-18 上午10:03:31
 * @version 1.0
 */
public class OrderDO extends BaseDO {

	private Long saasId;

	// 店铺ID
	private Long shopId;

	// 类型
	private String type;

	// 来源
	private String fromType;

	// 相关对象ID
	private Long relatedId;

	// 父ID
	private Long pid;

	// 名称
	private String name;

	// 用户ID
	private Long userId;

	// 状态
	private String status;

	// 子状态
	private String subStatus;

	// 运费
	private Long deliveryFee;

	// 价格
	private Long price;

	// 支付时间
	private Date payTime;

	// 支付类型
	private String payment;

	// 发货时间
	private Date sendOutTime;

	// 收货时间
	private Date confirmReceiptTime;

	// 评价时间
	private Date commentTime;

	// 收货地址
	private Long deliAddrId;

	// 备注
	private String note;

	// 拓展字段
	private String feature;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getSendOutTime() {
		return sendOutTime;
	}

	public void setSendOutTime(Date sendOutTime) {
		this.sendOutTime = sendOutTime;
	}

	public Date getConfirmReceiptTime() {
		return confirmReceiptTime;
	}

	public void setConfirmReceiptTime(Date confirmReceiptTime) {
		this.confirmReceiptTime = confirmReceiptTime;
	}

	public Long getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(Long deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDeliAddrId() {
		return deliAddrId;
	}

	public void setDeliAddrId(Long deliAddrId) {
		this.deliAddrId = deliAddrId;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public String getFromType() {
		return fromType;
	}

	public void setFromType(String fromType) {
		this.fromType = fromType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(Long relatedId) {
		this.relatedId = relatedId;
	}

	public String getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}

}
