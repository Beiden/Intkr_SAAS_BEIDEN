package com.intkr.saas.domain.bo.page;

import java.util.ArrayList;
import java.util.List;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 17:15:12
 * @version 1.0
 */
public class PageCategoryBO extends BaseBO {

	private Long saasId;

	// 父类目
	private Long pid;

	// 名称
	private String name;

	// 排序
	private Double sort;

	// 描述
	private String note;

	// 拓展字段
	private String feature;

	private PageCategoryBO parent;

	private List<PageCategoryBO> childs = new ArrayList<PageCategoryBO>();

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

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

	public void addChild(PageCategoryBO categoryBO) {
		childs.add(categoryBO);
	}

	public List<PageCategoryBO> getChilds() {
		return childs;
	}

	public void setChilds(List<PageCategoryBO> childs) {
		this.childs = childs;
	}

	public PageCategoryBO getParent() {
		return parent;
	}

	public void setParent(PageCategoryBO parent) {
		this.parent = parent;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}
