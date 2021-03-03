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
 * @date 2017-07-21 15:12:16
 * @version 1.0
 */
public class ItemFcategoryBO extends BaseBO {

	private Long saasId;

	// 状态
	private String status;

	// 父类目
	private Long pid;

	// URL
	private String url;

	// 后台类目ID
	private String ibcIds;

	// 名称
	private String name;

	// 权重
	private Double sort;

	// 描述
	private String note;

	//
	private String feature;

	private ItemFcategoryBO parent;

	private List<ItemFcategoryBO> childs;

	private List<ItemFcategoryBO> sbilings;

	private List<ItemCategoryBO> ibcs;

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

	public List<ItemFcategoryBO> getChilds() {
		return childs;
	}

	public void setChilds(List<ItemFcategoryBO> childs) {
		this.childs = childs;
	}

	public void addChild(ItemFcategoryBO categoryBO) {
		if (childs == null) {
			childs = new ArrayList<ItemFcategoryBO>();
		}
		childs.add(categoryBO);
	}

	public ItemFcategoryBO getParent() {
		return parent;
	}

	public void setParent(ItemFcategoryBO parent) {
		this.parent = parent;
	}

	public void addIbcId(Long ibcId) {
		if (ibcId == null) {
			return;
		}
		if (ibcIds == null || "".equals(ibcIds)) {
			ibcIds = ibcId.toString();
		}
		String[] ibcIdsArray = ibcIds.split(";");
		for (String ibcIdString : ibcIdsArray) {
			if (ibcIdString.equals(ibcId.toString())) {
				return;
			}
		}
		ibcIds += ";" + ibcId.toString();
	}

	public void addIbc(ItemCategoryBO category) {
		if (category == null) {
			return;
		}
		if (ibcs == null) {
			ibcs = new ArrayList<ItemCategoryBO>();
		}
		ibcs.add(category);
	}

	public void removeIbcId(Long ibcId) {
		if (ibcId == null) {
			return;
		}
		if (ibcIds == null || "".equals(ibcIds)) {
			return;
		}
		String[] ibcIdsArray = ibcIds.split(";");
		ibcIds = "";
		for (String ibcIdString : ibcIdsArray) {
			if (!ibcIdString.equals(ibcId.toString())) {
				ibcIds += ";" + ibcIdString;
			}
		}
		if (ibcIds.length() > 0) {
			ibcIds = ibcIds.substring(1, ibcIds.length());
		}
	}

	public String getIbcIds() {
		return ibcIds;
	}

	public List<Long> getIbcIdsList() {
		if (ibcIds == null || ibcIds.equals("")) {
			return new ArrayList<Long>();
		}
		List<Long> idList = new ArrayList<Long>();
		String[] idStrings = ibcIds.split(";");
		for (String id : idStrings) {
			try {
				idList.add(Long.valueOf(id));
			} catch (NumberFormatException e) {
			}
		}
		return idList;
	}

	public void setIbcIds(String ibcIds) {
		this.ibcIds = ibcIds;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ItemCategoryBO> getIbcs() {
		return ibcs;
	}

	public void setIbcs(List<ItemCategoryBO> ibcs) {
		this.ibcs = ibcs;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<ItemFcategoryBO> getSbilings() {
		return sbilings;
	}

	public void setSbilings(List<ItemFcategoryBO> sbilings) {
		this.sbilings = sbilings;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}
