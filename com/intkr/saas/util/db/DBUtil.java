package com.intkr.saas.util.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.ScriptRunner;

/**
 * 
 * @author Beiden
 * @date 2011-11-14 下午7:42:47
 * @version 1.0
 */
public class DBUtil {

	public static Connection getConnection(String jdbcDriver, String jdbcUrl, String jdbcUser, String jdbcPasswd) {
		try {
			Class.forName(jdbcDriver);
			return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPasswd);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void run(Connection conn, String sqlFile) {
		try {
			ScriptRunner runner = new ScriptRunner(conn);
			runner.runScript(new InputStreamReader(new FileInputStream(new File(sqlFile)), "UTF-8"));
			conn.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(conn);
		}
	}

	public static void close(Connection conn) {
		if (conn == null) {
			return;
		}
		try {
			if (conn.isClosed()) {
				return;
			}
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void restore(String userName, String password, String dataBase, String sqlFile) {
		String restore = "cmd /c mysql -u" + userName + " -p" + password + " " + dataBase + " < " + sqlFile;
		try {
			Runtime.getRuntime().exec(restore);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void backup(String userName, String password, String serverIp, String dataBase, String sqlFile) {
		String command = "cmd /c mysqldump -u" + userName //
				+ " -p" + password //
				+ " -h" + serverIp //
				+ " " + dataBase //
				+ " >" + sqlFile //
				+ " --lock-all-tables";
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static TableDO getTable(Connection con, String tableName) {
		return getTable(con, null, tableName);
	}

	public static List<TableDO> getTables(Connection con) {
		return getTables(con, null);
	}

	public static List<TableDO> getTables(Connection con, String schemaName) {
		List<String> tableNameList = MysqlMetaUtil.getAllTableList(con, null);
		List<TableDO> tableList = new ArrayList<TableDO>();
		for (String tableName : tableNameList) {
			TableDO table = getTable(con, schemaName, tableName);
			if (table != null) {
				tableList.add(table);
			}
		}
		return tableList;
	}

	public static List<String> getTableNames(Connection con) {
		List<String> tableNameList = MysqlMetaUtil.getAllTableList(con, null);
		return tableNameList;
	}

	/**
	 * 获得表或视图中的所有列信息
	 */
	@SuppressWarnings("unused")
	public static TableDO getTable(Connection con, String schemaName, String tableName) {
		TableDO table = new TableDO();
		table.setName(tableName);
		try {
			DatabaseMetaData dbMetaData = con.getMetaData();
			ResultSet rs = dbMetaData.getColumns(null, schemaName, tableName, "%");
			while (rs.next()) {
				String tableCat = rs.getString("TABLE_CAT");// 表目录（可能为空）
				String tableSchemaName = rs.getString("TABLE_SCHEM");// 表的架构（可能为空）
				String tableName_ = rs.getString("TABLE_NAME");// 表名
				String columnName = rs.getString("COLUMN_NAME");// 列名
				int dataType = rs.getInt("DATA_TYPE"); // 对应的java.sql.Types类型
				String dataTypeName = rs.getString("TYPE_NAME");// java.sql.Types类型
																// 名称
				int columnSize = rs.getInt("COLUMN_SIZE");// 列大小
				int decimalDigits = rs.getInt("DECIMAL_DIGITS");// 小数位数
				int numPrecRadix = rs.getInt("NUM_PREC_RADIX");// 基数（通常是10或2）
				int nullAble = rs.getInt("NULLABLE");// 是否允许为null
				String remarks = rs.getString("REMARKS");// 列描述
				String columnDef = rs.getString("COLUMN_DEF");// 默认值
				int sqlDataType = rs.getInt("SQL_DATA_TYPE");// sql数据类型
				int sqlDatetimeSub = rs.getInt("SQL_DATETIME_SUB"); // SQL日期时间分?
				int charOctetLength = rs.getInt("CHAR_OCTET_LENGTH"); // char类型的列中的最大字节数
				int ordinalPosition = rs.getInt("ORDINAL_POSITION"); // 表中列的索引（从1开始）
				String isNullAble = rs.getString("IS_NULLABLE");
				String isAutoincrement = rs.getString("IS_AUTOINCREMENT");
				ColumnDO column = new ColumnDO();
				column.setName(columnName);
				column.setType(dataTypeName);
				column.setDesc(remarks);
				column.setColumnDef(columnDef);
				column.setNullAble(nullAble);
				column.setColumnSize(columnSize);
				table.getColumns().add(column);
			}
			String indexDesc = "";

			String primaryKeys = "";
			rs = dbMetaData.getPrimaryKeys(null, schemaName, tableName);
			while (rs.next()) {
				String columnName = rs.getString(4);
				primaryKeys += "`" + columnName + "`,";
			}
			if (primaryKeys.length() > 1) {
				primaryKeys = primaryKeys.substring(0, primaryKeys.length() - 1);
			}

			rs = dbMetaData.getIndexInfo(null, schemaName, tableName, true, true);
			while (rs.next()) {
				boolean nonUnique = rs.getBoolean("NON_UNIQUE");// 非唯一索引
				String indexQualifier = rs.getString("INDEX_QUALIFIER");// 索引目录（可能为空）
				String indexName = rs.getString("INDEX_NAME");// 索引的名称
				short type = rs.getShort("TYPE");// 索引类型
				short ordinalPosition = rs.getShort("ORDINAL_POSITION");// 在索引列顺序号
				String columnName = rs.getString("COLUMN_NAME");// 列名
				String ascOrDesc = rs.getString("ASC_OR_DESC");// 列排序顺序:升序还是降序
				int cardinality = rs.getInt("CARDINALITY"); // 基数
				System.out.println(nonUnique + "-" + indexQualifier + "-" + indexName + "-" + type + "-" + ordinalPosition + "-" + columnName + "-" + ascOrDesc + "-" + cardinality);
				if ("PRIMARY".equalsIgnoreCase(indexName)) {
					indexDesc += "PRIMARY KEY (" + primaryKeys + ") \n";
				} else if (nonUnique) {
					indexDesc += "UNIQUE KEY `" + indexName + "` (`" + columnName + "`,`...`) \n";
				} else {
					indexDesc += "KEY `" + indexName + "` (`" + columnName + "`,`...`) \n";
				}
			}
			table.setIndexDesc(indexDesc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return table;
	}

	public static List<Map<String, Object>> select(Connection connection, String sql) {
		return select(connection, sql, null);
	}

	public static List<Map<String, Object>> select(Connection connection, String sql, List<Object> params) {
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					stmt.setObject(i + 1, params.get(i));
				}
			}
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			List<String> nameList = new ArrayList<String>();
			for (int i = 0; i < count; i++) {
				nameList.add(rsmd.getColumnName(i + 1));
			}
			List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (String colunmName : nameList) {
					map.put(colunmName, rs.getObject(colunmName));
				}
				returnList.add(map);
			}
			return returnList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
