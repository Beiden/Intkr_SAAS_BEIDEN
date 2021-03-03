package com.intkr.saas.domain.bo.order;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.domain.bo.user.UserBO;

/**
 * 
 * @author Beiden
 * @date 2017-09-07 16:24:10
 * @version 1.0
 */
public class OrderReturnedBO extends BaseBO {

	private Long saasId;

	// 帐号
	private Long userId;

	// 订单
	private Long orderId;

	// 店铺
	private Long shopId;

	// 类型
	private String type;

	// 状态
	private String status;

	// 描述
	private String content;

	// 拓展字段
	private String feature;

	private UserBO account;

	private ShopBO shop;

	private OrderBO order;

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

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public ShopBO getShop() {
		return shop;
	}

	public void setShop(ShopBO shop) {
		this.shop = shop;
	}

	public OrderBO getOrder() {
		return order;
	}

	public void setOrder(OrderBO order) {
		this.order = order;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}
