package com.intkr.saas.domain.dbo.user;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2011-11-16 下午4:33:35
 * @version 1.0
 */
public class RoleDO extends BaseDO {

	private static final long serialVersionUID = 1L;

	private String type;

	private Long saasId;

	private Long pid;

	private String code;

	private String name;

	private String note;

	private Double sort;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}
