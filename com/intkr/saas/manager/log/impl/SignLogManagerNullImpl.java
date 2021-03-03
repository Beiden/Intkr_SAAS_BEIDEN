package com.intkr.saas.manager.log.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.domain.bo.log.SignLogBO;
import com.intkr.saas.domain.dbo.log.SignLogDO;
import com.intkr.saas.manager.log.SignLogManager;
import com.intkr.saas.util.db.SqlResultDO;

/**
 * 
 * @author Beiden
 * @date 2016-4-24 上午11:43:41
 * @version 1.0
 */
public class SignLogManagerNullImpl implements SignLogManager {

	public long insert(SignLogBO object) {
		return 0;
	}

	public long getId() {
		return 0;
	}

	public int delete(Object primary) {
		return 0;
	}

	public void deleteAll() {
	}

	public int deleteAll(List<Object> ids) {
		return 0;
	}

	public int update(SignLogBO object) {
		return 0;
	}

	public SignLogBO get(Object primary) {
		return null;
	}

	public List<SignLogBO> select() {
		return new ArrayList<SignLogBO>();
	}

	public List<SignLogBO> select(SignLogBO object) {
		return new ArrayList<SignLogBO>();
	}

	public SignLogBO selectOne(SignLogBO object) {
		return null;
	}

	public long count(SignLogBO object) {
		return 0;
	}

	public boolean exist(SignLogBO object) {
		return false;
	}

	public SignLogBO selectAndCount(SignLogBO bo) {
		return null;
	}

	public List<SignLogBO> selectAll() {
		return new ArrayList<SignLogBO>();
	}

	public BaseDAO<SignLogDO> getBaseDAO() {
		return null;
	}

	public long insert(List<SignLogBO> boList) {
		return 0;
	}

	public SignLogBO selectCount(SignLogBO bo) {
		return selectAndCount(bo);
	}

	public SqlResultDO executeSql(String sql) {
		return null;
	}

	public SqlResultDO executeSql(String sql, List<Object> datas) {
		return null;
	}

	public void deleteReal(Date maxGmtCreated) {

	}
}
