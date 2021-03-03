package com.intkr.saas.domain.bo.db;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.util.JsonUtil;
import com.intkr.saas.util.db.DBUtil;

/**
 * 
 * 
 * @table datasource_tab
 * 
 * @author Beiden
 * @date 2020-04-02 18:59:02
 * @version 1.0
 */
public class DatasourceBO extends BaseBO<DatasourceBO> {

	private static final long serialVersionUID = 1L;

	private Long saasId;

	// name
	private String name;

	// host
	private String host;

	// port
	private String port;

	// user
	private String user;

	// pwd
	private String pwd;

	// 字符集
	private String characterEncoding;

	// 拓展字段
	private String feature;

	// 默认连接的数据库
	private String dbName;

	// 权重
	private Double sort;

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getCharacterEncoding() {
		return characterEncoding;
	}

	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
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

	public Connection getConnection() {
		return getConnection(null);
	}

	public Connection getConnection(String dbName) {
		if (dbName == null) {
			if (getDbName() != null && !"".equals(getDbName())) {
				dbName = getDbName();
			} else {
				dbName = "mysql";
			}
		}
		String jdbcDriver = "com.mysql.jdbc.Driver";
		String jdbcUrl = "jdbc:mysql://" + getHost() + ":" + getPort() + "/" + dbName + "?useUnicode=true&characterEncoding=" + getCharacterEncoding();
		String jdbcUser = getUser();
		String jdbcPasswd = getPwd();
		Connection con = DBUtil.getConnection(jdbcDriver, jdbcUrl, jdbcUser, jdbcPasswd);
		// Connection con = DbPool.getConnection(jdbcDriver, jdbcUrl, jdbcUser,
		// jdbcPasswd);
		return con;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

}
