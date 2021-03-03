package com.intkr.saas.domain.bo.order;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2016-4-14 上午8:18:38
 * @version 1.0
 */
public class PaticipateBO extends BaseBO {

	private Long id;

	private String type;

	private Long userId;

	private Long relatedId;

	private String feature;

	private Object relatedObject;

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

	public Object getRelatedObject() {
		return relatedObject;
	}

	public void setRelatedObject(Object relatedObject) {
		this.relatedObject = relatedObject;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

}
