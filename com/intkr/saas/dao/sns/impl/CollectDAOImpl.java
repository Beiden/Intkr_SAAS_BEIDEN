package com.intkr.saas.dao.sns.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.sns.CollectDAO;
import com.intkr.saas.domain.dbo.sns.CollectDO;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 上午11:22:21
 * @version 1.0
 */
@Repository("CollectDAO")
public class CollectDAOImpl extends BaseDAOImpl<CollectDO> implements CollectDAO {

	public String getNameSpace() {
		return "collect";
	}

}
