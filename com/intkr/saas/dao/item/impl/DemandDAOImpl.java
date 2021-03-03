package com.intkr.saas.dao.item.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.item.DemandDAO;
import com.intkr.saas.domain.dbo.item.DemandDO;

/**
 * 
 * @author Beiden
 * @date 2016-4-15 上午11:12:03
 * @version 1.0
 */
@Repository("DemandDAO")
public class DemandDAOImpl extends BaseDAOImpl<DemandDO> implements DemandDAO {

	public String getNameSpace() {
		return "demand";
	}

}
