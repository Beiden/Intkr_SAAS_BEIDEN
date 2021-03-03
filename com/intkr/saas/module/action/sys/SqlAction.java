package com.intkr.saas.module.action.sys;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.intkr.saas.engine.db.MyBatisEngine;
import com.intkr.saas.util.JsonUtil;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

/**
 * 
 * @author Beiden
 * @date 2017-8-5 下午4:25:39
 * @version 1.0
 */
public class SqlAction {

	public static Cache<String, String> getTableCache = CacheBuilder.newBuilder()//
			.expireAfterAccess(30, TimeUnit.MINUTES).//
			maximumSize(10000).//
			build();

	public void getTable(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		try {
			final String sql = request.getParameter("sql");
			String jsonString = null;
			if ("No".equalsIgnoreCase(request.getParameter("cache"))) {
				jsonString = getTable(sql);
			} else {
				jsonString = getTableCache.get(sql, new Callable<String>() {
					public String call() throws Exception {
						return getTable(sql);
					}
				});
			}
			request.setAttribute("data", jsonString);
			request.setAttribute("result", true);
			request.setAttribute("msg", "获取成功！");
		} catch (ExecutionException e) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常！");
		}
	}

	private String getTable(String sql) throws SQLException {
		SqlSession sqlSession = MyBatisEngine.getSession(null);
		Connection conn = sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
		List<String> columnNames = new ArrayList<String>();
		for (int i = 0; i < rsmd.getColumnCount(); i++) {
			columnNames.add(rsmd.getColumnLabel(i + 1));
		}
		List<Map<String, Object>> table = new ArrayList<Map<String, Object>>();
		while (rs.next()) {
			Map<String, Object> row = new HashMap<String, Object>();
			for (String key : columnNames) {
				row.put(key, rs.getObject(key));
			}
			table.add(row);
		}
		String jsonString = JsonUtil.toJson(table);
		return jsonString;
	}

	public static Cache<String, String> getOneCache = CacheBuilder.newBuilder()//
			.expireAfterAccess(30, TimeUnit.MINUTES).//
			maximumSize(10000).//
			build();

	public void getOne(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		try {
			final String sql = request.getParameter("sql");
			String jsonString;
			if ("No".equalsIgnoreCase(request.getParameter("cache"))) {
				jsonString = getOne(sql);
			} else {
				jsonString = getOneCache.get(sql, new Callable<String>() {
					public String call() throws Exception {
						return getOne(sql);
					}
				});
			}
			request.setAttribute("data", jsonString);
			request.setAttribute("result", true);
			request.setAttribute("msg", "获取成功！");
		} catch (ExecutionException e) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常！");
		}
	}

	private String getOne(String sql) throws SQLException {
		SqlSession sqlSession = MyBatisEngine.getSession(null);
		Connection conn = sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
		List<String> columnNames = new ArrayList<String>();
		for (int i = 0; i < rsmd.getColumnCount(); i++) {
			columnNames.add(rsmd.getColumnLabel(i + 1));
		}
		Map<String, Object> row = new HashMap<String, Object>();
		if (rs.next()) {
			for (String key : columnNames) {
				row.put(key, rs.getObject(key));
			}
		}
		String jsonString = JsonUtil.toJson(row);
		return jsonString;
	}

}
