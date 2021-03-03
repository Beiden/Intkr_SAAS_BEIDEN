package com.intkr.saas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.domain.BaseDO;
import com.intkr.saas.engine.db.MyBatisEngine;
import com.intkr.saas.util.MapUtil;
import com.intkr.saas.util.TypeUtil;
import com.intkr.saas.util.db.DBUtil;
import com.intkr.saas.util.db.SqlResultDO;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-10-20 下午04:07:33
 * @version 1.0
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class BaseDAOImpl<DO extends BaseDO> implements BaseDAO<DO> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public abstract String getNameSpace();

	private String dataSourceName = "default";

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public long getId() {
		return IdEngine.get(getNameSpace());
	}

	public long insert(DO dto) {
		boolean hasIdPre = dto.getId() != null;// 是否预先分配了ID
		int result;
		SqlSession session = MyBatisEngine.getSession(getDataSourceName());
		try {
			result = session.insert(getNameSpace() + ".insert", dto);
			session.commit();
		} finally {
			session.close();
		}
		boolean hasIdNext = dto.getId() != null;
		if (!hasIdPre && hasIdNext) {// 是否数据库自增的ID
			return dto.getId();
		}
		return result;
	}

	public long insert(List<DO> datas) {
		int result = 0;
		SqlSession session = MyBatisEngine.getSession(getDataSourceName());
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("datas", datas);
			result = session.insert(getNameSpace() + ".inserts", map);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	public int delete(Object object) {
		int result;
		SqlSession session = MyBatisEngine.getSession(getDataSourceName());
		try {
			if (TypeUtil.isPrimitiveType(object)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("primary", object);
				result = session.delete(getNameSpace() + ".delete", map);
			} else {
				result = session.delete(getNameSpace() + ".delete", object);
			}
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	public void deleteAll() {
		List<DO> list = select();
		while (!list.isEmpty()) {
			List<Object> ids = new ArrayList<Object>();
			for (DO bo : list) {
				ids.add(bo.getId());
			}
			deleteAll(ids);
			list = select();
		}
	}

	public int deleteAll(List<Object> ids) {
		int result;
		SqlSession session = MyBatisEngine.getSession(getDataSourceName());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("primarys", ids);
		try {
			result = session.delete(getNameSpace() + ".deleteAll", map);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	public int update(DO dto) {
		int result;
		SqlSession session = MyBatisEngine.getSession(getDataSourceName());
		try {
			result = session.update(getNameSpace() + ".update", dto);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	public DO get(Object object) {
		if (object == null) {
			return null;
		}
		List<DO> dtoList = null;
		if (TypeUtil.isPrimitiveType(object)) {
			Map<String, Object> query = new HashMap<String, Object>();
			query.put("id", object);
			query.put("_offset", 0);
			query.put("_pageSize", 1);
			dtoList = this.selectByObject(query);
		} else {
			dtoList = this.selectByObject(object);
		}
		if (dtoList.isEmpty()) {
			return null;
		} else {
			return dtoList.get(0);
		}
	}

	public List<DO> selectByMap(Map<String, Object> map) {
		SqlSession session = MyBatisEngine.getSession(getDataSourceName());
		try {
			List<DO> dtoList = session.selectList(getNameSpace() + ".select", map);
			session.commit();
			if (dtoList != null) {
				return dtoList;
			} else {
				return new ArrayList<DO>();
			}
		} finally {
			session.close();
		}
	}

	public List<DO> selectByObject(Object dto) {
		if (dto instanceof Map) {
			return this.selectByMap((Map) dto);
		} else {
			Map<String, Object> map = MapUtil.toMap(dto);
			return this.selectByMap(map);
		}
	}

	public List<DO> select() {
		return this.selectByMap(new HashMap<String, Object>());
	}

	public List<DO> select(DO dto) {
		return this.selectByObject(dto);
	}

	public DO selectOne(DO dto) {
		dto.set_pageSize(1);
		List<DO> list = this.selectByObject(dto);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public long count(DO dto) {
		if (dto instanceof Map) {
			return this.countByMap((Map) dto);
		} else {
			Map<String, Object> map = MapUtil.toMap(dto);
			return this.countByMap(map);
		}
	}

	public boolean exist(DO dto) {
		return count(dto) > 0;
	}

	public Long countByMap(Map<String, Object> map) {
		SqlSession session = MyBatisEngine.getSession(getDataSourceName());
		try {
			Long count = session.selectOne(getNameSpace() + ".count", map);
			session.commit();
			return count;
		} finally {
			session.close();
		}
	}

	public void foreachByOffset(ForeachCallback<DO> callback) {
		Map<String, Object> query = new HashMap<String, Object>();
		Long pageSize = 500l;
		Long offset = 0l;
		query.put("_pageSize", pageSize);
		query.put("_offset", offset);
		List<DO> list = selectByMap(query);
		while (!list.isEmpty()) {
			try {
				callback.process(list);
			} catch (Exception e) {
				logger.error("", e);
			}
			offset += pageSize;
			query.put("_offset", offset);
			list = selectByMap(query);
		}
	}

	public void foreachById(ForeachCallback<DO> callback) {
		Map<String, Object> query = new HashMap<String, Object>();
		Long minId = 0l;
		query.put("_pageSize", 500);
		query.put("_offset", 0);
		query.put("minId", minId);
		List<DO> list = selectByMap(query);
		while (list != null && !list.isEmpty()) {
			try {
				callback.process(list);
			} catch (Exception e) {
				logger.error("", e);
			}
			for (DO dbo : list) {
				if (dbo.getId() > minId) {
					minId = dbo.getId();
				}
			}
			query.put("minId", minId);
			list = selectByMap(query);
		}
	}

	public SqlResultDO executeSql(String sql) {
		return executeSql(sql, new ArrayList<Object>());
	}

	public SqlResultDO executeSql(String sql, List<Object> datas) {
		SqlSession session = MyBatisEngine.getSession(getDataSourceName());
		Connection conn = session.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			if (datas != null) {
				for (int i = 0; i < datas.size(); i++) {
					stmt.setObject(i + 1, datas.get(i));
				}
			}
			SqlResultDO sqlResult = new SqlResultDO();
			if (sql.contains("insert")) {
				boolean result = stmt.execute();
			} else if (sql.contains("delete")) {
				boolean result = stmt.execute();
			} else if (sql.contains("update")) {
				boolean result = stmt.execute();
			} else if (sql.contains("select")) {
				if (!sql.contains("limit")) {
					sql += " limit 20";
				}
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int count = rsmd.getColumnCount();
				for (int i = 0; i < count; i++) {
					String header = rsmd.getColumnName(i + 1);
					sqlResult.addHeader(header);
				}
				while (rs.next()) {
					List<Object> data = new ArrayList<Object>();
					for (String header : sqlResult.getHeaders()) {
						Object value = rs.getObject(header);
						data.add(value);
					}
					sqlResult.addRow(data);
				}
				rs.close();
			}
			stmt.close();
			sqlResult.setSql(sql);
			sqlResult.setResult(true);
			return sqlResult;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn);
		}
	}

}
