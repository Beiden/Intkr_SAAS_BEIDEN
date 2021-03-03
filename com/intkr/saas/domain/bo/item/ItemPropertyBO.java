package com.intkr.saas.domain.bo.item;

import java.util.ArrayList;
import java.util.List;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 15:55:37
 * @version 1.0
 */
public class ItemPropertyBO extends BaseBO {

	private Long saasId;

	// 属性名
	private String name;

	// 备注
	private String note;

	// 权重
	private Double sort;

	// 拓展字段
	private String feature;

	private List<ItemPropertyValueBO> values;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<ItemPropertyValueBO> getValues() {
		return values;
	}

	public void setValues(List<ItemPropertyValueBO> values) {
		this.values = values;
	}

	public void addValue(ItemPropertyValueBO value) {
		if (this.values == null) {
			this.values = new ArrayList<ItemPropertyValueBO>();
		}
		for (ItemPropertyValueBO bo : values) {
			if (bo.getValue().equals(value.getValue())) {
				return;
			}
		}
		this.values.add(value);
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
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
