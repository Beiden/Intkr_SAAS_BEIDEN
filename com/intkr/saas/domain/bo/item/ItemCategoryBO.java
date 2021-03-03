package com.intkr.saas.domain.bo.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.util.JsonUtil;

/**
 * 
 * @author Beiden
 * @date 2017-07-20 18:10:41
 * @version 1.0
 */
public class ItemCategoryBO extends BaseBO {

	private Long saasId;

	// 状态
	private String status;

	// 级别
	private Integer level;

	// 父类目
	private Long pid;

	// 名称
	private String name;

	// 描述
	private String note;

	// 权重
	private Double sort;

	// 拓展字段
	private String feature;

	private ItemCategoryBO parent;

	private List<ItemCategoryBO> childs = new ArrayList<ItemCategoryBO>();

	private List<ItemSpuTemplateBO> spuTemplateList;

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

	public Object getFeature(String key) {
		Map<String, Object> map = JsonUtil.toObject(feature, Map.class);
		if (map == null) {
			return null;
		}
		return map.get(key);
	}

	public void setFeature(String key, Object value) {
		Map<String, Object> map = JsonUtil.toObject(feature, Map.class);
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		map.put(key, value);
		this.feature = JsonUtil.toJson(map);
	}

	public List<ItemCategoryBO> getChilds() {
		return childs;
	}

	public void setChilds(List<ItemCategoryBO> childs) {
		this.childs = childs;
	}

	public void addChild(ItemCategoryBO categoryBO) {
		childs.add(categoryBO);
	}

	public List<ItemSpuTemplateBO> getSpuTemplateList() {
		return spuTemplateList;
	}

	public void setSpuTemplateList(List<ItemSpuTemplateBO> spuTemplateList) {
		this.spuTemplateList = spuTemplateList;
	}

	public ItemCategoryBO getParent() {
		return parent;
	}

	public void setParent(ItemCategoryBO parent) {
		this.parent = parent;
	}

	public Integer getLevel() {
		return level;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
