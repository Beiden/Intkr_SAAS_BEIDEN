package com.intkr.saas.domain.bo.fs;

import java.util.ArrayList;
import java.util.List;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2017-07-20 15:38:37
 * @version 1.0
 */
public class MediaCategoryBO extends BaseBO {

	private Long saasId;

	// 父类目
	private Long pid;

	// 名称
	private String name;

	// 排序
	private Double sort;

	// 描述
	private String description;

	//
	private String feature;

	private MediaCategoryBO parent;

	private List<MediaCategoryBO> childs = new ArrayList<MediaCategoryBO>();

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public List<MediaCategoryBO> getChilds() {
		return childs;
	}

	public void setChilds(List<MediaCategoryBO> childs) {
		this.childs = childs;
	}

	public void addChild(MediaCategoryBO categoryBO) {
		childs.add(categoryBO);
	}

	public MediaCategoryBO getParent() {
		return parent;
	}

	public void setParent(MediaCategoryBO parent) {
		this.parent = parent;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}
