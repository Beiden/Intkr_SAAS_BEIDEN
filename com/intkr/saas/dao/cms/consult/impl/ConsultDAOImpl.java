package com.intkr.saas.dao.cms.consult.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.consult.ConsultDAO;
import com.intkr.saas.domain.dbo.consult.ConsultDO;
import com.intkr.saas.dao.BaseDAOImpl;

/**
 * 
 * @author Beiden
 * @date 2017-04-14 21:37:40
 * @version 1.0
 */
@Repository("ConsultDAO")
public class ConsultDAOImpl extends BaseDAOImpl<ConsultDO> implements ConsultDAO {

	public String getNameSpace() {
		return "consult";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
