package com.intkr.saas.engine.db;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

/**
 * 
 * @author Beiden
 */
public class MySqlSession implements SqlSession {

	private SqlSession sqlSession;

	public MySqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public <T> T selectOne(String statement) {
		return sqlSession.selectOne(statement);
	}

	public <T> T selectOne(String statement, Object parameter) {
		return sqlSession.selectOne(statement, parameter);
	}

	public <E> List<E> selectList(String statement) {
		return sqlSession.selectList(statement);
	}

	public <E> List<E> selectList(String statement, Object parameter) {
		return sqlSession.selectList(statement, parameter);
	}

	public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
		return sqlSession.selectList(statement, parameter, rowBounds);
	}

	public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
		return sqlSession.selectMap(statement, mapKey);
	}

	public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
		return sqlSession.selectMap(statement, parameter, mapKey);
	}

	public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
		return sqlSession.selectMap(statement, parameter, mapKey, rowBounds);
	}

	public void select(String statement, Object parameter, ResultHandler handler) {
		sqlSession.select(statement, parameter, handler);
	}

	public void select(String statement, ResultHandler handler) {
		sqlSession.select(statement, handler);
	}

	public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
		sqlSession.select(statement, parameter, rowBounds, handler);
	}

	public int insert(String statement) {
		return sqlSession.insert(statement);
	}

	public int insert(String statement, Object parameter) {
		return sqlSession.insert(statement, parameter);
	}

	public int update(String statement) {
		return sqlSession.update(statement);
	}

	public int update(String statement, Object parameter) {
		return sqlSession.update(statement, parameter);
	}

	public int delete(String statement) {
		return sqlSession.delete(statement);
	}

	public int delete(String statement, Object parameter) {
		return sqlSession.delete(statement, parameter);
	}

	public void commit() {
		// sqlSession.commit();
	}

	public void commitReal() {
		sqlSession.commit();
	}

	public void commit(boolean force) {
		// sqlSession.commit(force);
	}

	public void commitReal(boolean force) {
		sqlSession.commit(force);
	}

	public void rollback() {
		// sqlSession.rollback();
	}

	public void rollbackReal() {
		sqlSession.rollback();
	}

	public void rollback(boolean force) {
		// sqlSession.rollback(force);
	}

	public void rollbackReal(boolean force) {
		// sqlSession.rollback(force);
	}

	public List<BatchResult> flushStatements() {
		return sqlSession.flushStatements();
	}

	public void close() {
		// sqlSession.close();
	}

	public void closeReal() {
		sqlSession.close();
	}

	public void clearCache() {
		sqlSession.clearCache();
	}

	public Configuration getConfiguration() {
		return sqlSession.getConfiguration();
	}

	public <T> T getMapper(Class<T> type) {
		return sqlSession.getMapper(type);
	}

	public Connection getConnection() {
		return sqlSession.getConnection();
	}

}
