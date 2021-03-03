package com.intkr.saas.dao.cms.link.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.domain.dbo.link.LinkDO;
import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.cms.link.LinkDAO;

/**
 * 
 * @author Beiden
 * @date 2011-10-2 下午9:40:19
 * @version 1.0
 */
@Repository("LinkDAO")
public class LinkDAOImpl extends BaseDAOImpl<LinkDO> implements LinkDAO {

	public String getNameSpace() {
		return "link";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
