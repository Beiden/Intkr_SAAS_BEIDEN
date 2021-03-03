package com.intkr.saas.dao.mms.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.mms.EmailGroupDAO;
import com.intkr.saas.domain.dbo.mms.EmailGroupDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 18:56:31
 * @version 1.0
 */
@Repository("EmailGroupDAO")
public class EmailGroupDAOImpl extends BaseDAOImpl<EmailGroupDO> implements EmailGroupDAO {

	public String getNameSpace() {
		return "emailGroup";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
