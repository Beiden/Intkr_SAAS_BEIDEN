package com.intkr.saas.domain.bo.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.util.JsonUtil;

/**
 * 
 * @author Beiden
 * @date 2011-4-18 上午10:03:31
 * @version 1.0
 */
public class OrderBO extends BaseBO {

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

	private List<OrderDetailBO> orderDetails;

	private DeliveryAddressBO deliveryAddress;

	private UserBO user;

	private UserBO merchant;

	private ShopBO shop;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OrderDetailBO> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailBO> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public void addOrderDetail(OrderDetailBO orderDetail) {
		if (this.orderDetails == null) {
			this.orderDetails = new ArrayList<OrderDetailBO>();
		}
		this.orderDetails.add(orderDetail);

		// 初始化订单的名称和店铺
		if (this.orderDetails.size() == 1) {
			ItemBO item = (ItemBO) orderDetail.getProperty("item");
			if (item != null) {
				setName(item.getName());
				setShopId(item.getShopId());
			}
		}
	}

	public DeliveryAddressBO getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(DeliveryAddressBO deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public UserBO getUser() {
		return user;
	}

	public void setUser(UserBO user) {
		this.user = user;
	}

	public UserBO getMerchant() {
		return merchant;
	}

	public void setMerchant(UserBO merchant) {
		this.merchant = merchant;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
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

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public ShopBO getShop() {
		return shop;
	}

	public void setShop(ShopBO shop) {
		this.shop = shop;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
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

	public String getFromType() {
		return fromType;
	}

	public void setFromType(String fromType) {
		this.fromType = fromType;
	}

	public String getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}

}
