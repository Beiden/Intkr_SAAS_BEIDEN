package com.intkr.saas.domain.bo.sys;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午10:08:49
 * @version 1.0
 */
public class StatisticsCodeBO extends BaseBO {

	private Long id;

	private String type;

	private String name;

	private String code;

	private String note;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
