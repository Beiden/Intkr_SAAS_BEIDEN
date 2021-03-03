package com.intkr.saas.domain.dbo.shop;

import com.intkr.saas.domain.BaseDO;

/**
 * 店铺公告
 * 
 * @table shop_note
 * 
 * @author Beiden
 * @date 2020-10-30 18:05:17
 * @version 1.0
 */
public class ShopNoteDO extends BaseDO<ShopNoteDO> {

	private static final long serialVersionUID = 1L;

	private Long saasId;

	// 店铺ID
	private Long shopId;

	// 类型
	private String type;

	// 标题
	private String title;

	// 状态
	private String status;

	// 内容
	private String content;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

}
