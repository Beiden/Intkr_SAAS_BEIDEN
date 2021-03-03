package com.intkr.saas.dao.mms.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.mms.EmailTemplateCategoryDAO;
import com.intkr.saas.domain.dbo.mms.EmailTemplateCategoryDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 18:57:31
 * @version 1.0
 */
@Repository("EmailTemplateCategoryDAO")
public class EmailTemplateCategoryDAOImpl extends BaseDAOImpl<EmailTemplateCategoryDO> implements EmailTemplateCategoryDAO {

	public String getNameSpace() {
		return "emailTemplateCategory";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
