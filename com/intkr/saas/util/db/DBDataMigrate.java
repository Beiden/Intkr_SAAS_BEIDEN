package com.intkr.saas.util.db;

import java.sql.Connection;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.intkr.saas.util.ExecutorsUtil;

/**
 * 
 * @author Beiden
 * @date 2011-11-14 下午7:42:47
 * @version 1.0
 */
public class DBDataMigrate {

	public static void migrateData(Connection srcConn, String srcTable, final Connection descConn, String descTable) {
		String countSql = "select count(*) from " + srcTable + " limit 1";
		Long count = DbExecutor.count(srcConn, countSql);
		Integer pageSize = 500;
		Integer page = 1;
		while ((page - 1) * pageSize < count) {
			String selectSql = "select * from " + srcTable + " limit " + ((page - 1) * pageSize) + "," + pageSize;
			SqlResultDO result = DbExecutor.execute(srcConn, selectSql);
			final String insertSql = getInsertSql(descTable, result);
			final CountDownLatch countDownLatch = new CountDownLatch(result.getDatas().size());
			for (final List<Object> row : result.getDatas()) {
				ExecutorsUtil.execute(new Runnable() {
					public void run() {
						DbExecutor.execute(descConn, insertSql, row);
						countDownLatch.countDown();
					}
				});
			}
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			page++;
		}
		System.out.println(count);
	}

	private static String getInsertSql(String descTable, SqlResultDO result) {
		List<String> headers = result.getHeaders();
		String fields = "";
		String fieldDs = "";
		for (String field : headers) {
			fields += "," + field;
			fieldDs += ",?";
		}
		fields = fields.substring(1);
		fieldDs = fieldDs.substring(1);
		String insertSql = "insert into " + descTable + "(" + fields + ") values(" + fieldDs + ")";
		return insertSql;
	}

	public static void main(String[] args) {
		Connection conn = DBUtil.getConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/saas_cms?useUnicode=true&amp;characterEncoding=utf8", "root", "IntKr!@34");
		migrateData(conn, "shop_cart", conn, "shop_cart_copy");
	}

}
