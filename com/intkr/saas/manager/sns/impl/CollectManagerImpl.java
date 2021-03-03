package com.intkr.saas.manager.sns.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.sns.CollectDAO;
import com.intkr.saas.domain.bo.sns.CollectBO;
import com.intkr.saas.domain.dbo.sns.CollectDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.sns.CollectManager;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 上午11:23:02
 * @version 1.0
 */
@Repository("CollectManager")
public class CollectManagerImpl extends BaseManagerImpl<CollectBO, CollectDO> implements CollectManager {

	@Resource
	private CollectDAO collectDAO;

	public BaseDAO<CollectDO> getBaseDAO() {
		return collectDAO;
	}

	public boolean exist(CollectBO collect) {
		Long count = count(collect);
		return count > 0;
	}

}
