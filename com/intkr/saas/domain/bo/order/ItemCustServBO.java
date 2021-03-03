package com.intkr.saas.domain.bo.order;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.domain.bo.user.UserBO;

/**
 * 
 * @author Beiden
 * @date 2017-09-07 16:24:32
 * @version 1.0
 */
public class ItemCustServBO extends BaseBO {

	private Long saasId;

	// 用户ID
	private Long userId;

	// 订单
	private Long orderId;

	// 订单明细ID
	private Long orderDetailId;

	// 店铺
	private Long shopId;

	// 商品ID
	private Long itemId;

	// 状态
	private String status;

	// 内容
	private String content;

	// 拓展字段
	private String feature;

	private UserBO account;

	private OrderBO order;

	private OrderDetailBO orderDetail;

	private ShopBO shop;

	private ItemBO item;

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

	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public UserBO getAccount() {
		return account;
	}

	public void setAccount(UserBO account) {
		this.account = account;
	}

	public OrderBO getOrder() {
		return order;
	}

	public void setOrder(OrderBO order) {
		this.order = order;
	}

	public OrderDetailBO getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetailBO orderDetail) {
		this.orderDetail = orderDetail;
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
