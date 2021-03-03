package com.intkr.saas.domain.bo.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.util.JsonUtil;

/**
 * 
 * 
 * @table field_tab
 * 
 * @author Beiden
 * @date 2020-04-02 21:01:47
 * @version 1.0
 */
public class FieldBO extends BaseBO<FieldBO> {

	private static final long serialVersionUID = 1L;

	private Long saasId;

	// database
	private Long databaseId;

	// table
	private Long tableId;

	// db_name
	private String dbName;

	// name
	private String name;

	// db_type
	private String dbType;

	// type
	private String type;

	// db_length
	private Integer dbLength;

	// allow_null
	private Integer allowNull;

	// links
	private String links;

	// searchType
	private String searchType;

	// showType
	private String showType;

	// 备注
	private String note;

	// 拓展字段
	private String feature;

	// 权重
	private Double sort;

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public Long getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(Long databaseId) {
		this.databaseId = databaseId;
	}

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getDbLength() {
		return dbLength;
	}

	public void setDbLength(Integer dbLength) {
		this.dbLength = dbLength;
	}

	public Integer getAllowNull() {
		return allowNull;
	}

	public void setAllowNull(Integer allowNull) {
		this.allowNull = allowNull;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
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

	public String getLinks() {
		return links;
	}

	public void setLinks(String links) {
		this.links = links;
	}

	public void addLink(String value, String url, String title) {
		List<Map<String, Object>> list = JsonUtil.toObject(links, List.class);
		if (list == null) {
			list = new ArrayList<Map<String, Object>>();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", url);
		map.put("value", value);
		map.put("title", title);
		list.add(map);
		this.links = JsonUtil.toJson(list);
	}

	public void updateLink(String value, String url, String title) {
		List<Map<String, Object>> list = JsonUtil.toObject(links, List.class);
		if (list == null) {
			list = new ArrayList<Map<String, Object>>();
		}
		for (Map<String, Object> map : list) {
			if (value.equals(map.get("value"))) {
				map.put("url", url);
				map.put("title", title);
				this.links = JsonUtil.toJson(list);
				break;
			}
		}
	}

	public void moveLeftLink(String value) {
		List<Map<String, Object>> list = JsonUtil.toObject(links, List.class);
		if (list == null) {
			list = new ArrayList<Map<String, Object>>();
		}
		int i = 0;
		Map<String, Object> map = null;
		for (; i < list.size(); i++) {
			map = list.get(i);
			if (value.equals(map.get("value"))) {
				break;
			}
		}
		if (i < list.size() && i > 1) {
			list.remove(i);
			list.add(i - 1, map);
			this.links = JsonUtil.toJson(list);
		}
	}

	public void removeLink(String value) {
		List<Map<String, Object>> list = JsonUtil.toObject(links, List.class);
		if (list == null) {
			list = new ArrayList<Map<String, Object>>();
		}
		int i = 0;
		Map<String, Object> map = null;
		for (; i < list.size(); i++) {
			map = list.get(i);
			if (value.equals(map.get("value"))) {
				break;
			}
		}
		if (i < list.size()) {
			list.remove(i);
			this.links = JsonUtil.toJson(list);
		}
	}

	public void moveRightLink(String value) {
		List<Map<String, Object>> list = JsonUtil.toObject(links, List.class);
		if (list == null) {
			list = new ArrayList<Map<String, Object>>();
		}
		int i = 0;
		Map<String, Object> map = null;
		for (; i < list.size(); i++) {
			map = list.get(i);
			if (value.equals(map.get("value"))) {
				break;
			}
		}
		if (i < list.size() && i > 1) {
			list.remove(i);
			list.add(i + 1, map);
			this.links = JsonUtil.toJson(list);
		}
	}

	public List<Map<String, Object>> getLinkList(Map<String, Object> dataMap) {
		List<Map<String, Object>> list = JsonUtil.toObject(links, List.class);
		if (list == null) {
			return new ArrayList<Map<String, Object>>();
		}
		for (Map<String, Object> map : list) {
			String url = (String) map.get("url");
			for (String key : dataMap.keySet()) {
				if (url.contains("${" + key + "}")) {
					url = url.replace("${" + key + "}", dataMap.get(key) + "");
				}
			}
			if (!url.contains("?")) {
				url += "?";
			}
			map.put("realUrl", url);
		}
		return list;
	}

	public boolean isKeySearch() {
		if ("keySearch".equalsIgnoreCase(getSearchType())//
				|| getSearchType() == null //
				|| "".equals(getSearchType())//
		) {
			return true;
		}
		return false;
	}

	public boolean isOptionSearch() {
		if ("optionSearch".equalsIgnoreCase(getSearchType())) {
			return true;
		}
		return false;
	}

	public boolean isShowField() {
		if ("show".equalsIgnoreCase(getShowType())//
				|| getShowType() == null //
				|| "".equals(getShowType())//
		) {
			return true;
		}
		return false;
	}

}
