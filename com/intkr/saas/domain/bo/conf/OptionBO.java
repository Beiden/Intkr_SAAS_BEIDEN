package com.intkr.saas.domain.bo.conf;

import java.util.List;
import java.util.Map;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.util.JsonUtil;

/**
 * 
 * @author Beiden
 * @date 2011-5-7 下午12:44:45
 * @version 1.0
 */
public class OptionBO extends BaseBO {

	private Long saasId;

	// 类型
	private String type;

	// 名称
	private String name;

	// 值
	private String value;

	// 是否自动加载
	private Integer autoload;

	// 备注
	private String note;

	// 权重
	private Double sort;

	// 拓展字段
	private String feature;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getAutoload() {
		return autoload;
	}

	public void setAutoload(Integer autoload) {
		this.autoload = autoload;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getJsonValue() {
		try {
			if (getType().equals("value")) {
				return getValue();
			}
			if (getValue().startsWith("{")) {
				Map value = JsonUtil.parse(getValue(), Map.class);
				return value;
			} else {
				List value = JsonUtil.parse(getValue(), List.class);
				return value;
			}
		} catch (Exception e) {
			return getValue();
		}
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

}
