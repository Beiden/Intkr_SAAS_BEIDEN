package com.intkr.saas.domain.bo.shopping;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.bo.item.ItemBO;

/**
 * 
 * @author Beiden
 * @date 2017-09-02 16:13:19
 * @version 1.0
 */
public class ItemCollectBO extends BaseBO {

	private Long saasId;

	// 帐号
	private Long userId;

	// 商品ID
	private Long itemId;

	private ItemBO item;

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
