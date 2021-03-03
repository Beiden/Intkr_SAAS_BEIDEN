package com.intkr.saas.domain.bo.db;

import java.util.HashMap;
import java.util.Map;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.util.JsonUtil;

/**
 * 
 * 
 * @table datasource_database_tab
 * 
 * @author Beiden
 * @date 2020-04-02 20:51:57
 * @version 1.0
 */
public class DatasourceDatabaseBO extends BaseBO<DatasourceDatabaseBO> {

	private static final long serialVersionUID = 1L;

	// Saas
	private Long saasId;

	// datasource
	private Long datasourceId;

	// database
	private Long databaseId;

	// is_default
	private String isDefault;

	//
	private String feature;

	private DatabaseBO database;

	private DatasourceBO datasource;

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public Long getDatasourceId() {
		return datasourceId;
	}

	public void setDatasourceId(Long datasourceId) {
		this.datasourceId = datasourceId;
	}

	public Long getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(Long databaseId) {
		this.databaseId = databaseId;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
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

	public DatabaseBO getDatabase() {
		return database;
	}

	public void setDatabase(DatabaseBO database) {
		this.database = database;
	}

	public DatasourceBO getDatasource() {
		return datasource;
	}

	public void setDatasource(DatasourceBO datasource) {
		this.datasource = datasource;
	}

}
