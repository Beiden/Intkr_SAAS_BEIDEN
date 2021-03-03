package com.intkr.saas.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Beiden
 * @date 2011-11-14 下午7:42:47
 * @version 1.0
 */
public class DbExecutor {

	public static SqlResultDO execute(Connection con, String sql, List<Object> datas) {
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
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
			} else if (sql.contains("update") || sql.contains("drop table")) {
				boolean result = stmt.execute();
			} else if (sql.contains("select") || sql.contains("show create table")) {
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
			}else{
				boolean result = stmt.execute();
			}
			stmt.close();
			sqlResult.setSql(sql);
			sqlResult.setResult(true);
			return sqlResult;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static SqlResultDO execute(Connection con, String sql) {
		return execute(con, sql, null);
	}

	public static Long count(Connection con, String sql) {
		SqlResultDO result = execute(con, sql);
		Object obj = result.getDatas().get(0).get(0);
		return ((Number) obj).longValue();
	}

	public static void main(String[] args) {
		Connection conn = DBUtil.getConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/saas_cms?useUnicode=true&amp;characterEncoding=utf8", "root", "IntKr!@34");
		Long count = count(conn, "select count(*) from cms_article limit 1");
		System.out.println(count);
	}

}
