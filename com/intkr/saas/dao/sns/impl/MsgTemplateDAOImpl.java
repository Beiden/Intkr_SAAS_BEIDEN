package com.intkr.saas.dao.sns.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.sns.MsgTemplateDAO;
import com.intkr.saas.domain.dbo.sns.MsgTemplateDO;

/**
 * 
 * @author Beiden
 * @date 2016-5-24 上午11:01:01
 * @version 1.0
 */
@Repository("sns.MsgTemplateDAO")
public class MsgTemplateDAOImpl extends BaseDAOImpl<MsgTemplateDO> implements MsgTemplateDAO {

	public String getNameSpace() {
		return "msgTemplate";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
