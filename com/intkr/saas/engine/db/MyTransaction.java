package com.intkr.saas.engine.db;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.ibatis.session.SqlSession;

/**
 * 
 * @author Beiden
 */
public class MyTransaction {

	// 当前线程事务嵌套计数器
	public static ThreadLocal<AtomicInteger> transactionCounter = new ThreadLocal<AtomicInteger>();
	// 当前线程是否开户事务
	public static ThreadLocal<Boolean> isTransaction = new ThreadLocal<Boolean>();
	// 当前线程使用到的数据库连接
	public static ThreadLocal<Map<String, MySqlSession>> tSqlSession = new ThreadLocal<Map<String, MySqlSession>>();

	public static SqlSession getSession(String name) {
		if (!isTransaction()) {
			throw new RuntimeException("not transaction thread!");
		}
		Map<String, MySqlSession> map = tSqlSession.get();
		MySqlSession session = map.get(name);
		if (session != null) {
			return session;
		}
		SqlSession newSession = MyBatisEngine.getSessionReal(name);
		MySqlSession mySqlSession = new MySqlSession(newSession);
		map.put(name, mySqlSession);
		return mySqlSession;
	}

	public static boolean isTransaction() {
		Boolean result = isTransaction.get();
		if (result == null) {
			return false;
		}
		return result;
	}

	// 事务开始
	public static void begin() {
		isTransaction.set(true);
		if (transactionCounter.get() == null) {
			transactionCounter.set(new AtomicInteger(0));
		}
		transactionCounter.get().incrementAndGet();
		Map<String, MySqlSession> map = tSqlSession.get();
		if (map == null) {
			map = new ConcurrentHashMap<String, MySqlSession>();
			tSqlSession.set(map);
		}
	}

	// 事务提交
	public static void commit() {
		if (!isTransaction()) {
			return;
		}
		int result = transactionCounter.get().decrementAndGet();
		if (result != 0) {
			return;
		}
		Map<String, MySqlSession> map = tSqlSession.get();
		if (map != null && !map.isEmpty()) {
			for (MySqlSession session : map.values()) {
				try {
					session.commitReal();
				} finally {
					session.closeReal();
				}
			}
			map.clear();
		}
		isTransaction.set(false);
	}

	// 事务回滚
	public static void rollback() {
		if (!isTransaction()) {
			return;
		}
		transactionCounter.get().set(0);
		Map<String, MySqlSession> map = tSqlSession.get();
		if (map != null && !map.isEmpty()) {
			for (MySqlSession session : map.values()) {
				try {
					session.rollbackReal();
				} finally {
					session.closeReal();
				}
			}
			map.clear();
		}
		isTransaction.set(false);
	}

}
