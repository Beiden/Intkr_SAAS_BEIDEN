package com.intkr.saas.dao;

import java.util.List;

import com.intkr.saas.domain.BaseDO;
import com.intkr.saas.util.db.SqlResultDO;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-10-20 下午04:03:28
 * @version 1.0
 */
public interface BaseDAO<DO extends BaseDO> {

	public long insert(DO dto);

	public long insert(List<DO> dtoList);

	public long getId();

	public int delete(Object id);

	public void deleteAll();

	public int deleteAll(List<Object> ids);

	public int update(DO dto);

	public DO get(Object id);

	public List<DO> select();

	public List<DO> select(DO dto);

	public DO selectOne(DO dto);

	public long count(DO dto);

	public boolean exist(DO dto);

	public SqlResultDO executeSql(String sql);

	public SqlResultDO executeSql(String sql, List<Object> datas);

	public void foreachByOffset(ForeachCallback<DO> callback);

	public void foreachById(ForeachCallback<DO> callback);

	public static interface ForeachCallback<DO extends BaseDO> {
		public void process(List<DO> list);
	}

}
