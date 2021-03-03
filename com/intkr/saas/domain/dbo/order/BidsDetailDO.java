package com.intkr.saas.domain.dbo.order;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2016-4-13 下午1:43:17
 * @version 1.0
 */
public class BidsDetailDO extends BaseDO {

	private Long id;

	private Long userId;

	private Long bidsId;

	private String title;

	private Long price;

	private String content;

	private String feature;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBidsId() {
		return bidsId;
	}

	public void setBidsId(Long bidsId) {
		this.bidsId = bidsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

}
