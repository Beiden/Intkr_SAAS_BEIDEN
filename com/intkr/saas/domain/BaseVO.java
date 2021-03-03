package com.intkr.saas.domain;

import java.util.Date;

/**
 * 
 * 
 * @author Beiden
 * 
 * @date 2010-11-28 上午11:10:31
 * @version 1.0
 */
public class BaseVO<T> extends Pager<T> {

	private static final long serialVersionUID = 1L;

	private Long id;

	private int isDeleted;

	private Date gmtCreated;

	private Date gmtModified;

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
