package com.intkr.saas.util.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intkr.saas.engine.db.MyBatisEngine;

public class MysqlMetaUtil {

	private MysqlMetaUtil() {
	}

	/**
	 * 获得数据库的一些相关信息
	 */
	public void getDataBaseInformations(Connection con) {
		try {
			DatabaseMetaData dbMetaData = con.getMetaData();
			System.out.println("数据库已知的用户: " + dbMetaData.getUserName());
			System.out.println("数据库的系统函数的逗号分隔列表: " + dbMetaData.getSystemFunctions());
			System.out.println("数据库的时间和日期函数的逗号分隔列表: " + dbMetaData.getTimeDateFunctions());
			System.out.println("数据库的字符串函数的逗号分隔列表: " + dbMetaData.getStringFunctions());
			System.out.println("数据库供应商用于 'schema' 的首选术语: " + dbMetaData.getSchemaTerm());
			System.out.println("数据库URL: " + dbMetaData.getURL());
			System.out.println("是否允许只读:" + dbMetaData.isReadOnly());
			System.out.println("数据库的产品名称:" + dbMetaData.getDatabaseProductName());
			System.out.println("数据库的版本:" + dbMetaData.getDatabaseProductVersion());
			System.out.println("驱动程序的名称:" + dbMetaData.getDriverName());
			System.out.println("驱动程序的版本:" + dbMetaData.getDriverVersion());

			System.out.println();
			System.out.println("数据库中使用的表类型");
			ResultSet rs = dbMetaData.getTableTypes();
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
			rs.close();
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得该用户下面的所有表
	 */
	public static List<String> getAllTableList(Connection con, String schemaName) {
		List<String> tableNameList = new ArrayList<String>();
		try {
			DatabaseMetaData dbMetaData = con.getMetaData();
			// table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE",
			// "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM".
			String[] types = { "TABLE" };
			ResultSet rs = dbMetaData.getTables(null, schemaName, "%", types);
			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME"); // 表名
				tableNameList.add(tableName);
				String tableType = rs.getString("TABLE_TYPE"); // 表类型
				String remarks = rs.getString("REMARKS"); // 表备注
				System.out.println(tableName + "-" + tableType + "-" + remarks);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tableNameList;
	}

	/**
	 * 获得该用户下面的所有视图
	 */
	public void getAllViewList(Connection con, String schemaName) {
		try {
			DatabaseMetaData dbMetaData = con.getMetaData();
			String[] types = { "VIEW" };
			ResultSet rs = dbMetaData.getTables(null, schemaName, "%", types);
			while (rs.next()) {
				String viewName = rs.getString("TABLE_NAME"); // 视图名
				String viewType = rs.getString("TABLE_TYPE"); // 视图类型
				String remarks = rs.getString("REMARKS"); // 视图备注
				System.out.println(viewName + "-" + viewType + "-" + remarks);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得数据库中所有方案名称
	 */
	public void getAllSchemas(Connection con) {
		try {
			DatabaseMetaData dbMetaData = con.getMetaData();
			ResultSet rs = dbMetaData.getSchemas();
			while (rs.next()) {
				String tableSchem = rs.getString("TABLE_SCHEM");
				System.out.println(tableSchem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得一个表的索引信息
	 */
	public void getIndexInfo(Connection con, String schemaName, String tableName) {
		try {
			DatabaseMetaData dbMetaData = con.getMetaData();
			ResultSet rs = dbMetaData.getIndexInfo(null, schemaName, tableName, true, true);
			while (rs.next()) {
				boolean nonUnique = rs.getBoolean("NON_UNIQUE");// 非唯一索引(Can
																// index values
																// be
																// non-unique.
																// false when
																// TYPE is
																// tableIndexStatistic
																// )
				String indexQualifier = rs.getString("INDEX_QUALIFIER");// 索引目录（可能为空）
				String indexName = rs.getString("INDEX_NAME");// 索引的名称
				short type = rs.getShort("TYPE");// 索引类型
				short ordinalPosition = rs.getShort("ORDINAL_POSITION");// 在索引列顺序号
				String columnName = rs.getString("COLUMN_NAME");// 列名
				String ascOrDesc = rs.getString("ASC_OR_DESC");// 列排序顺序:升序还是降序
				int cardinality = rs.getInt("CARDINALITY"); // 基数
				System.out.println(nonUnique + "-" + indexQualifier + "-" + indexName + "-" + type + "-" + ordinalPosition + "-" + columnName + "-" + ascOrDesc + "-" + cardinality);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得一个表的主键信息
	 */
	public void getAllPrimaryKeys(Connection con, String schemaName, String tableName) {
		try {
			DatabaseMetaData dbMetaData = con.getMetaData();
			ResultSet rs = dbMetaData.getPrimaryKeys(null, schemaName, tableName);
			while (rs.next()) {
				String columnName = rs.getString("COLUMN_NAME");// 列名
				short keySeq = rs.getShort("KEY_SEQ");// 序列号(主键内值1表示第一列的主键，值2代表主键内的第二列)
				String pkName = rs.getString("PK_NAME"); // 主键名称
				System.out.println(columnName + "-" + keySeq + "-" + pkName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得一个表的外键信息
	 */
	public void getAllExportedKeys(Connection con, String schemaName, String tableName) {
		try {
			DatabaseMetaData dbMetaData = con.getMetaData();
			ResultSet rs = dbMetaData.getExportedKeys(null, schemaName, tableName);
			while (rs.next()) {
				String pkTableCat = rs.getString("PKTABLE_CAT");// 主键表的目录（可能为空）
				String pkTableSchem = rs.getString("PKTABLE_SCHEM");// 主键表的架构（可能为空）
				String pkTableName = rs.getString("PKTABLE_NAME");// 主键表名
				String pkColumnName = rs.getString("PKCOLUMN_NAME");// 主键列名
				String fkTableCat = rs.getString("FKTABLE_CAT");// 外键的表的目录（可能为空）出口（可能为null）
				String fkTableSchem = rs.getString("FKTABLE_SCHEM");// 外键表的架构（可能为空）出口（可能为空）
				String fkTableName = rs.getString("FKTABLE_NAME");// 外键表名
				String fkColumnName = rs.getString("FKCOLUMN_NAME"); // 外键列名
				short keySeq = rs.getShort("KEY_SEQ");// 序列号（外键内值1表示第一列的外键，值2代表在第二列的外键）。

				/**
				 * hat happens to foreign key when primary is updated:
				 * importedNoAction - do not allow update of primary key if it
				 * has been imported importedKeyCascade - change imported key to
				 * agree with primary key update importedKeySetNull - change
				 * imported key to NULL if its primary key has been updated
				 * importedKeySetDefault - change imported key to default values
				 * if its primary key has been updated importedKeyRestrict -
				 * same as importedKeyNoAction (for ODBC 2.x compatibility)
				 */
				short updateRule = rs.getShort("UPDATE_RULE");

				/**
				 * What happens to the foreign key when primary is deleted.
				 * importedKeyNoAction - do not allow delete of primary key if
				 * it has been imported importedKeyCascade - delete rows that
				 * import a deleted key importedKeySetNull - change imported key
				 * to NULL if its primary key has been deleted
				 * importedKeyRestrict - same as importedKeyNoAction (for ODBC
				 * 2.x compatibility) importedKeySetDefault - change imported
				 * key to default if its primary key has been deleted
				 */
				short delRule = rs.getShort("DELETE_RULE");
				String fkName = rs.getString("FK_NAME");// 外键的名称（可能为空）
				String pkName = rs.getString("PK_NAME");// 主键的名称（可能为空）

				/**
				 * can the evaluation of foreign key constraints be deferred
				 * until commit importedKeyInitiallyDeferred - see SQL92 for
				 * definition importedKeyInitiallyImmediate - see SQL92 for
				 * definition importedKeyNotDeferrable - see SQL92 for
				 * definition
				 */
				short deferRability = rs.getShort("DEFERRABILITY");

				System.out.println(pkTableCat + "-" + pkTableSchem + "-" + pkTableName + "-" + pkColumnName + "-" + fkTableCat + "-" + fkTableSchem + "-" + fkTableName + "-" + fkColumnName + "-" + keySeq + "-" + updateRule + "-" + delRule + "-" + fkName + "-" + pkName + "-" + deferRability);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		updateData();
		// metaData.getDataBaseInformations();
		// metaData.getAllTableList(null);
		// metaData.getAllViewList(null);
		// metaData.getAllSchemas();
		// Table table = metaData.getTableColumns(null, "car_staff");
		// System.out.println(table);
		// metaData.getIndexInfo(null, "zsc_admin");
		// metaData.getAllPrimaryKeys(null, "zsc_admin");
		// metaData.getAllExportedKeys(null, "zsc_admin");

	}

	private static void clearData() throws SQLException {
		Connection con = MyBatisEngine.getConnection();
		MysqlMetaUtil metaData = new MysqlMetaUtil();
		List<String> tables = metaData.getAllTableList(con, "root");
		for (String tableName : tables) {
			System.out.println(tableName);
			String sql = "delete from " + tableName + " where is_deleted =1";
			con.createStatement().execute(sql);
		}
		String sql = "delete from ik_sign_log";
		con.createStatement().execute(sql);
		sql = "delete from ik_sys_log";
		sql = "delete from car_user_log";
		con.createStatement().execute(sql);
	}

	private static void updateData() throws SQLException {
		Connection con = MyBatisEngine.getConnection();
		MysqlMetaUtil metaData = new MysqlMetaUtil();
		List<String> tables = metaData.getAllTableList(con, "root");
		for (String tableName : tables) {
			try {
				String sql = "update " + tableName + " set saas_id = 4 , app_id = 4;";
				System.out.println(sql);
				// Statement stat = metaData.con.createStatement();
				// stat.executeUpdate(sql);
				// stat.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		con.close();
	}

}
