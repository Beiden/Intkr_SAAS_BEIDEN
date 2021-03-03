package com.intkr.saas.engine.db;

import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Beiden
 * @date 2011-11-2 下午7:43:39
 * @version 1.0
 */
public class MyBatisEngine {

	protected static final Logger logger = LoggerFactory.getLogger(MyBatisEngine.class);

	static final Map<String, MyBatisContext> sqlSessionFactorys = new ConcurrentHashMap<String, MyBatisContext>();

	public static void init() {
		if (sqlSessionFactorys.isEmpty()) {
			synchronized (logger) {
				if (!sqlSessionFactorys.isEmpty()) {
					return;
				}
				new MyBatisContext("default", "mybatis-config.xml");
			}
		}
	}

	public static void init(String fileName) {
		if (sqlSessionFactorys.isEmpty()) {
			synchronized (logger) {
				if (!sqlSessionFactorys.isEmpty()) {
					return;
				}
				new MyBatisContext("default", "mybatis-config.xml");
			}
		}
	}

	public static SqlSession getSession() {
		init();
		return getSession("default");
	}

	public static Connection getConnection() {
		init();
		return getConnection("default");
	}

	public static SqlSession getSession(String name) {
		if (MyTransaction.isTransaction()) {
			return MyTransaction.getSession(name);
		}
		return getSessionReal(name);
	}

	public static SqlSession getSessionReal(String name) {
		init();
		MyBatisContext content = sqlSessionFactorys.get(name);
		if (content == null) {
			throw new RuntimeException("MyBatisContext is null ! name=" + name);
		}
		return content.getSession();
	}

	public static MyBatisContext getContext(String name) {
		init();
		MyBatisContext content = sqlSessionFactorys.get(name);
		if (content == null) {
			throw new RuntimeException("MyBatisContext is null ! name=" + name);
		}
		return content;
	}

	public static Connection getConnection(String name) {
		init();
		MyBatisContext content = sqlSessionFactorys.get(name);
		if (content == null) {
			throw new RuntimeException("name=" + name);
		}
		return content.getConnection();
	}

}
