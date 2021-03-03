package com.intkr.saas.domain.dbo.db;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * 
 * @table datasource_database_tab
 * 
 * @author Beiden
 * @date 2020-04-02 20:51:57
 * @version 1.0
 */
public class DatasourceDatabaseDO extends BaseDO<DatasourceDatabaseDO> {

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

}
