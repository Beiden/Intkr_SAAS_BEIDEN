package com.intkr.saas.domain.dbo.order;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2016-4-14 上午8:19:16
 * @version 1.0
 */
public class PaticipateDO extends BaseDO {

	private Long id;

	private String type;

	private Long userId;

	private Long relatedId;

	private String feature;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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

	public Long getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(Long relatedId) {
		this.relatedId = relatedId;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

}
