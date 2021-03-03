package com.intkr.saas.manager.conf.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.conf.IdDAO;
import com.intkr.saas.domain.bo.conf.IkIdBO;
import com.intkr.saas.domain.dbo.conf.IkIdDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.conf.IdManager;

/**
 * 
 * @author Beiden
 * @date 2016-07-31 18:04:42
 * @version 1.0
 */
@Repository("IdManager")
public class IdManagerImpl extends BaseManagerImpl<IkIdBO, IkIdDO> implements IdManager {

	@Resource
	private IdDAO ikIdDAO;

	public BaseDAO<IkIdDO> getBaseDAO() {
		return ikIdDAO;
	}

	public IkIdBO getByTableName(String tableName) {
		if (tableName == null || "".equals(tableName)) {
			return null;
		}
		IkIdBO query = new IkIdBO();
		query.setTableName(tableName);
		return selectOne(query);
	}

}
