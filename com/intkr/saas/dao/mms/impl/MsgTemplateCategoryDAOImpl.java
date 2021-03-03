package com.intkr.saas.dao.mms.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.mms.MsgTemplateCategoryDAO;
import com.intkr.saas.domain.dbo.mms.MsgTemplateCategoryDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 19:01:46
 * @version 1.0
 */
@Repository("MsgTemplateCategoryDAO")
public class MsgTemplateCategoryDAOImpl extends BaseDAOImpl<MsgTemplateCategoryDO> implements MsgTemplateCategoryDAO {

	public String getNameSpace() {
		return "msgTemplateCategory";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
