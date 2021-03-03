package com.intkr.saas.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-10-27 下午05:04:17
 * @version 1.0
 */
public class BaseDO<T> extends Pager<T> {

	private static final long serialVersionUID = 1L;

	private Long id;

	private int isDeleted;

	private Date gmtCreated;

	private Date gmtModified;

	// REPLACE(unix_timestamp(NOW(3)),'.','')
	private Long ctime;

	// REPLACE(unix_timestamp(NOW(3)),'.','')
	private Long mtime;

	// 1：删除;2：未删除
	private Integer deleted;

	private Map<String, Object> properties;

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

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public Object containsProperty(String key) {
		if (this.properties == null) {
			return null;
		}
		return this.properties.containsKey(key) && this.properties.get(key) != null;
	}

	public Object getProperty(String key) {
		if (this.properties == null) {
			return null;
		}
		return this.properties.get(key);
	}

	public void setProperty(String key, Object value) {
		if (this.properties == null) {
			this.properties = new HashMap<String, Object>();
		}
		properties.put(key, value);
	}

	public Long getCtime() {
		return ctime;
	}

	public void setCtime(Long ctime) {
		this.ctime = ctime;
	}

	public Long getMtime() {
		return mtime;
	}

	public void setMtime(Long mtime) {
		this.mtime = mtime;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

}
