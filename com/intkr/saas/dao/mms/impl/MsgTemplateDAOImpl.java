package com.intkr.saas.dao.mms.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.mms.MsgTemplateDAO;
import com.intkr.saas.domain.dbo.mms.MsgTemplateDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 19:03:45
 * @version 1.0
 */
@Repository("MsgTemplateDAO")
public class MsgTemplateDAOImpl extends BaseDAOImpl<MsgTemplateDO> implements MsgTemplateDAO {

	public String getNameSpace() {
		return "msgTemplate";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
