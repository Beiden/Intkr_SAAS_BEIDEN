package com.intkr.saas.dao.mms.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.mms.EmailTemplateDAO;
import com.intkr.saas.domain.dbo.mms.EmailTemplateDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 18:58:05
 * @version 1.0
 */
@Repository("EmailTemplateDAO")
public class EmailTemplateDAOImpl extends BaseDAOImpl<EmailTemplateDO> implements EmailTemplateDAO {

	public String getNameSpace() {
		return "emailTemplate";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
