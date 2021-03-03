package com.intkr.saas.manager;

import java.util.List;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.BaseDO;
import com.intkr.saas.util.db.SqlResultDO;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-10-20 下午04:03:28
 * @version 1.0
 */
public interface BaseManager<BO extends BaseBO, DO extends BaseDO> {

	public long insert(BO object);

	public long insert(List<BO> boList);

	public long getId();

	public int delete(Object primary);

	public void deleteAll();

	public int deleteAll(List<Object> ids);

	public int update(BO object);

	public BO get(Object primary);

	public List<BO> select();

	public List<BO> select(BO object);

	public BO selectOne(BO object);

	public long count(BO object);

	public boolean exist(BO object);

	public BO selectAndCount(BO bo);
	
	public BO selectCount(BO bo);

	public List<BO> selectAll();

	public BaseDAO<DO> getBaseDAO();
	
	public SqlResultDO executeSql(String sql);

	public SqlResultDO executeSql(String sql, List<Object> datas);

}
