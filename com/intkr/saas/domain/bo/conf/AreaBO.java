package com.intkr.saas.domain.bo.conf;

import java.util.ArrayList;
import java.util.List;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2016-5-15 上午9:37:34
 * @version 1.0
 */
public class AreaBO extends BaseBO {

	// 父ID
	private Long pid;

	// 名称
	private String name;

	// 权重
	private Double sort;

	// 备注
	private String note;

	// 拓展字段
	private String feature;

	private List<AreaBO> childs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public List<AreaBO> getChilds() {
		return childs;
	}

	public void setChilds(List<AreaBO> childs) {
		this.childs = childs;
	}

	public void addChild(AreaBO areaData) {
		if (this.childs == null) {
			this.childs = new ArrayList<AreaBO>();
		}
		childs.add(areaData);
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

}
