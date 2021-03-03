package com.intkr.saas.domain.bo.article;

import java.util.ArrayList;
import java.util.List;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 09:32:27
 * @version 1.0
 */
public class ArticleCategoryBO extends BaseBO {

	private Long saasId;

	//
	private Long pid;

	//
	private String name;

	//
	private Double sort;

	//
	private String note;

	//
	private String feature;

	private ArticleCategoryBO parent;

	private List<ArticleCategoryBO> childs = new ArrayList<ArticleCategoryBO>();

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

	public void setChilds(List<ArticleCategoryBO> childs) {
		this.childs = childs;
	}

	public void addChild(ArticleCategoryBO categoryBO) {
		childs.add(categoryBO);
	}

	public List<ArticleCategoryBO> getChilds() {
		return childs;
	}

	public ArticleCategoryBO getParent() {
		return parent;
	}

	public void setParent(ArticleCategoryBO parent) {
		this.parent = parent;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}
