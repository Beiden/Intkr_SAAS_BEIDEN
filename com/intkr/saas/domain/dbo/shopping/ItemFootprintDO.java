package com.intkr.saas.domain.dbo.shopping;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2017-09-02 15:36:07
 * @version 1.0
 */
public class ItemFootprintDO extends BaseDO {

	private Long saasId;

	// 帐号
	private Long userId;

	// 商品ID
	private Long itemId;

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

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}