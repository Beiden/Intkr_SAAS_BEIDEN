package com.intkr.saas.dao.sns.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.sns.EmailTemplateDAO;
import com.intkr.saas.domain.dbo.sns.EmailTemplateDO;

/**
 * 
 * @author Beiden
 * @date 2016-5-24 上午10:47:52
 * @version 1.0
 */
@Repository("sns.EmailTemplateDAO")
public class EmailTemplateDAOImpl extends BaseDAOImpl<EmailTemplateDO> implements EmailTemplateDAO {

	public String getNameSpace() {
		return "emailTemplate";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
