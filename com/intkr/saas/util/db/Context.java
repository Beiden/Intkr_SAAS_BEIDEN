package com.intkr.saas.util.db;

import java.io.File;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.StringUtil;

/**
 * 
 * @author Beiden
 * @date 2019-1-11
 * @version 1.0
 */
public class Context {

	public static Context instance = new Context();

	// 数据库驱动类
	private static String jdbcDriver;

	// 数据库连接地址
	private static String jdbcUrl;

	// 数据库用户名
	private static String jdbcUser;

	// 数据库用户密码
	private static String jdbcPasswd;

	// 表名
	private static String tableName;

	// 类名（当数据库表名跟类名不一样的时候）
	private static String tableClazName;

	// 表的描述
	private static String tableDesc = "";

	// 字段前缀
	private static String colPre;

	/***********************************/
	private static String esUrls;

	private static String esIndex;

	private static String esType;

	// 项目目录：com.intkr.saas
	private static String clazPackage;
	private static String clazDir;

	// 模块名：test
	private static String module = "";
	private static String moduleDir = "";

	private static Map<String, TableDO> tableMap = new HashMap<String, TableDO>();

	public static void init() {
		if (fromMysql()) {
			checkMysqlCon();
		} else {
			checkEs();
		}
	}

	public static SqlResultDO executeSql(String sql) {
		Connection con = DBUtil.getConnection(jdbcDriver, jdbcUrl, jdbcUser, jdbcPasswd);
		SqlResultDO sqlResult = DbExecutor.execute(con, sql);
		DBUtil.close(con);
		return sqlResult;
	}

	private static void checkEs() {
		if (esUrls == null) {
			throw new RuntimeException("esUrls 没有设置正确！");
		}
		if (esIndex == null) {
			throw new RuntimeException("esIndex 没有设置正确！");
		}
		if (esType == null) {
			throw new RuntimeException("esType 没有设置正确！");
		}
	}

	private static void checkMysqlCon() {
		if (clazPackage == null) {
			throw new RuntimeException("baseClassPackage 没有设置正确！");
		}
		if (jdbcDriver == null) {
			throw new RuntimeException("jdbcDriver 没有设置正确！");
		}
		if (jdbcUrl == null) {
			throw new RuntimeException("jdbcUrl 没有设置正确！");
		}
		if (jdbcUser == null) {
			throw new RuntimeException("jdbcUser 没有设置正确！");
		}
		if (jdbcPasswd == null) {
			throw new RuntimeException("jdbcPasswd 没有设置正确！");
		}
	}

	public static void setClazPackage(String clazPackage) {
		if (!clazPackage.endsWith(".")) {
			clazPackage += ".";
		}
		Context.clazPackage = clazPackage;
		Context.clazDir = Context.clazPackage.replace(".", "/");
	}

	/**
	 * 处理以 . 开关，不以 . 结尾
	 * 
	 * @param moduleName
	 */
	public static void setModule(String moduleName) {
		if (moduleName == null) {
			moduleName = "";
		}
		moduleName.replace("/", ".");
		if (!"".equals(moduleName) && !moduleName.startsWith(".")) {
			moduleName = "." + moduleName;
		}
		if (!"".equals(moduleName) && moduleName.endsWith(".")) {
			moduleName = moduleName.substring(0, moduleName.length() - 1);
		}
		Context.module = moduleName;
		Context.moduleDir = Context.module.replace(".", "/");
	}

	public static void setJdbcDriver(String jdbcDriver) {
		Context.jdbcDriver = jdbcDriver;
	}

	public static void setJdbcUrl(String jdbcUrl) {
		Context.jdbcUrl = jdbcUrl;
	}

	public static void setJdbcUser(String jdbcUser) {
		Context.jdbcUser = jdbcUser;
	}

	public static void setJdbcPasswd(String jdbcPasswd) {
		Context.jdbcPasswd = jdbcPasswd;
	}

	public static void setTableName(String tableName) {
		Context.tableName = tableName;
	}

	public static void setTableDesc(String tableDesc) {
		Context.tableDesc = tableDesc;
	}

	public static String getClazPackage() {
		return clazPackage;
	}

	public static String getModule() {
		return module;
	}

	public static String getModuleDir() {
		return Context.moduleDir;
	}

	public static String getClazDir() {
		return clazDir;
	}

	public static void setClazDir(String clazDir) {
		Context.clazDir = clazDir;
	}

	public static void setModuleDir(String moduleDir) {
		Context.moduleDir = moduleDir;
	}

	public static String getTime() {
		return DateUtil.formatDateTime(new Date());
	}

	public static String projectDir = "";

	static {
		File file = new File("1");
		projectDir = file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 1);
	}

	public static TableDO getTable() {
		if (tableMap.containsKey(tableName)) {
			return tableMap.get(tableName);
		}
		TableDO table = null;
		if (Context.fromMysql()) {
			Connection con = DBUtil.getConnection(jdbcDriver, jdbcUrl, jdbcUser, jdbcPasswd);
			table = DBUtil.getTable(con, tableName);
			DBUtil.close(con);
		}
		if ((table.getDesc() == null || "".equals(table.getDesc())) // 数据库没有表备注
				&& tableDesc != null) {
			table.setDesc(tableDesc);
		}
		if (tableClazName != null) {
			table.setClazName(tableClazName);
		}
		tableMap.put(tableName, table);
		return table;
	}

	/**
	 * 是否从Mysql生成 Or 从ES生成
	 * 
	 * @return
	 */
	public static boolean fromMysql() {
		return jdbcDriver != null;
	}

	public static void setEsUrls(String esUrls) {
		Context.esUrls = esUrls;
	}

	public static void setEsIndex(String esIndex) {
		Context.esIndex = esIndex;
	}

	public static void setEsType(String esType) {
		Context.esType = esType;
	}

	public static String getEsUrls() {
		return esUrls;
	}

	public static String getEsIndex() {
		return esIndex;
	}

	public static String getEsType() {
		return esType;
	}

	public static String getTableClazName() {
		return tableClazName;
	}

	public static void setTableClazName(String tableClazName) {
		String name = StringUtil.hump(tableClazName);
		Context.tableClazName = StringUtil.upperCaseFirstCharater(name);
	}

	public static String getColPre() {
		return colPre;
	}

	public static void setColPre(String colPre) {
		Context.colPre = colPre;
	}

}
