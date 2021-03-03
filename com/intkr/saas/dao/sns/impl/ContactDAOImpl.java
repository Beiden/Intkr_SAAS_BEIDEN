package com.intkr.saas.dao.sns.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.sns.ContactDAO;
import com.intkr.saas.domain.dbo.sns.ContactDO;

/**
 * 
 * @author Beiden
 * @date 2011-7-8 上午10:21:12
 * @version 1.0
 */
@Repository("sns.ContactDAO")
public class ContactDAOImpl extends BaseDAOImpl<ContactDO> implements ContactDAO {

	public String getNameSpace() {
		return "contact";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
