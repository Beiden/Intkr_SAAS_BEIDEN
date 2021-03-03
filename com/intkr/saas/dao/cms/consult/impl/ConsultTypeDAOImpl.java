package com.intkr.saas.dao.cms.consult.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.consult.ConsultTypeDAO;
import com.intkr.saas.domain.dbo.consult.ConsultTypeDO;
import com.intkr.saas.dao.BaseDAOImpl;

/**
 * 
 * @author Beiden
 * @date 2017-04-14 21:37:49
 * @version 1.0
 */
@Repository("ConsultTypeDAO")
public class ConsultTypeDAOImpl extends BaseDAOImpl<ConsultTypeDO> implements ConsultTypeDAO {

	public String getNameSpace() {
		return "consultType";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
