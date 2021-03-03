package com.intkr.saas.domain.dbo.opa;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * 
 * @table api_param_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
public class ApiParamDO extends BaseDO<ApiParamDO> {

	private static final long serialVersionUID = 1L;

	// api_id
	private Long apiId;

	// type
	private String type;

	// index
	private Long sort;

	// key
	private String key;

	// value
	private String value;

	//
	private String note;

	//
	private String required;

	//
	private String remark;

	//
	private String name;

	public Long getApiId() {
		return apiId;
	}

	public void setApiId(Long apiId) {
		this.apiId = apiId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
