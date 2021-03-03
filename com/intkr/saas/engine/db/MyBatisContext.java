package com.intkr.saas.engine.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intkr.saas.conf.SystemProperties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Beiden
 * @date 2011-11-2 下午7:43:39
 * @version 1.0
 */
public class MyBatisContext {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SqlSessionFactory sqlSessionFactory;

	private String confFile;

	private String name;

	public MyBatisContext(String name, String conf) {
		this.confFile = conf;
		this.name = name;
		init();
		MyBatisEngine.sqlSessionFactorys.put(name, this);
	}

	private SqlSessionFactory init() {
		if (sqlSessionFactory != null) {
			return sqlSessionFactory;
		}
		synchronized (this) {
			if (sqlSessionFactory != null) {
				return sqlSessionFactory;
			}
			InputStream in = null;
			try {
				if (confFile == null) {
					throw new RuntimeException("can not find " + confFile + " mybatis config.");
				}
				try {
					in = Resources.getResourceAsStream(confFile);
				} catch (Exception e) {
					in = new FileInputStream(new File(SystemProperties.getWebappPath() + "/WEB-INF/classes/" + confFile));
				}
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(in, "default");
				return sqlSessionFactory;
			} catch (Exception e) {
				logger.error("confFile=" + confFile, e);
				throw new RuntimeException(e);
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						logger.error("", e);
					}
				}
			}
		}
	}

	public SqlSession getSession() {
		return sqlSessionFactory.openSession();
	}

	public Connection getConnection() {
		return sqlSessionFactory.openSession().getConnection();
	}

	public String getConfFile() {
		return confFile;
	}

	public void setConfFile(String confFile) {
		this.confFile = confFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		MyBatisContext content = new MyBatisContext("default", "mybatis-config-cms.xml");
		Configuration configuration = content.sqlSessionFactory.getConfiguration();
		// 从configuration中获取MappedStatement
		// statement 为 com.school.dao.SchoolCustomerDao.selectBySome
		System.out.println(configuration.getMappedStatementNames());
		// 此处拼装生成BoundSql
		MappedStatement ms = configuration.getMappedStatement("Advertisement.select");
		Map parameterObject = new HashMap();
		parameterObject.put("code", "test");
		BoundSql boundSql = ms.getBoundSql(parameterObject);
		String sql = boundSql.getSql();

		List<ParameterMapping> list = boundSql.getParameterMappings();
		for (ParameterMapping param : list) {
			Object value = null;
			Object paramObject = boundSql.getParameterObject();
			if (paramObject instanceof Map) {
				value = ((Map) paramObject).get(param.getProperty());
			} else {
				value = null;
			}
			if (param.getJavaType().equals(String.class)) {
				sql = sql.replaceFirst("\\?", "'" + value + "'");
			} else {
				sql = sql.replaceFirst("\\?", value.toString());
			}
		}
		System.out.println(sql);
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

}
