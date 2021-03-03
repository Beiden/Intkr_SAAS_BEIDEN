package com.intkr.saas.dao.cms.tool.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.tool.ToolDAO;
import com.intkr.saas.domain.dbo.tool.ToolDO;
import com.intkr.saas.dao.BaseDAOImpl;

/**
 * 
 * @author Beiden
 * @date 2017-03-19 10:58:26
 * @version 1.0
 */
@Repository("ToolDAO")
public class ToolDAOImpl extends BaseDAOImpl<ToolDO> implements ToolDAO {

	public String getNameSpace() {
		return "tool";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
