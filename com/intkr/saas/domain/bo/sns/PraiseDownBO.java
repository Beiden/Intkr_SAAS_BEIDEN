package com.intkr.saas.domain.bo.sns;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.bo.user.UserBO;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 下午1:34:38
 * @version 1.0
 */
public class PraiseDownBO extends BaseBO {

	private Long id;

	private String type;

	private Long userId;

	private Long relatedId;

	private Object relatedObject;

	private UserBO user;

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

	public UserBO getUser() {
		return user;
	}

	public void setUser(UserBO user) {
		this.user = user;
	}

}
