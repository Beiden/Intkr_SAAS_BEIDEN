package com.intkr.saas.domain.bo.shop;

import java.util.Date;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.user.UserBO;

/**
 * 
 * @author Beiden
 * @date 2016-5-20 下午11:07:02
 * @version 1.0
 */
public class ItemSellRecordBO extends BaseBO {

	private Long saasId;

	// 用户ID
	private Long userId;

	// 商品ID
	private Long itemId;

	// 价格
	private Long price;

	// 数量
	private Integer count;

	// 购买时间
	private Date buyTime;

	// 订单ID
	private Long orderId;

	// 订单明细ID
	private Long orderDetailId;

	private UserBO user;

	private ItemBO item;

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public UserBO getUser() {
		return user;
	}

	public void setUser(UserBO user) {
		this.user = user;
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
