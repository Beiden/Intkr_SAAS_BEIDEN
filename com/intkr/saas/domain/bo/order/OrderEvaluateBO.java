package com.intkr.saas.domain.bo.order;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.domain.bo.user.UserBO;

/**
 * 
 * @author Beiden
 * @date 2016-5-20 下午7:25:13
 * @version 1.0
 */
public class OrderEvaluateBO extends BaseBO {

	private Long saasId;

	// 用户ID
	private Long userId;

	// 订单ID
	private Long orderId;

	// 订单明细ID
	private Long orderDetailId;

	// 店铺ID
	private Long shopId;

	// 商品ID
	private Long itemId;

	// 评分
	private Integer evaluate;

	// 商品描述相符评分
	private Double describeMatchGrade;

	// 服务态度评分
	private Double serveAttitudeGrade;

	// 物流服务评分
	private Double deliverySpeedGrade;

	// 内容
	private String content;

	// 拓展字段
	private String feature;

	private UserBO account;

	private OrderBO order;

	private OrderDetailBO orderDetail;

	private ShopBO shop;

	private ItemBO item;

	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
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

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Integer evaluate) {
		this.evaluate = evaluate;
	}

	public UserBO getAccount() {
		return account;
	}

	public void setAccount(UserBO account) {
		this.account = account;
	}

	public OrderDetailBO getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetailBO orderDetail) {
		this.orderDetail = orderDetail;
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

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public OrderBO getOrder() {
		return order;
	}

	public void setOrder(OrderBO order) {
		this.order = order;
	}

	public ShopBO getShop() {
		return shop;
	}

	public void setShop(ShopBO shop) {
		this.shop = shop;
	}

	public ItemBO getItem() {
		return item;
	}

	public void setItem(ItemBO item) {
		this.item = item;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}
