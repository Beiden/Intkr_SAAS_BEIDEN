package com.intkr.saas.dao.fs.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.fs.RelationshipDAO;
import com.intkr.saas.domain.dbo.fs.MediaRelationshipDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 12:30:23
 * @version 1.0
 */
@Repository("RelationshipDAO")
public class RelationshipDAOImpl extends BaseDAOImpl<MediaRelationshipDO> implements RelationshipDAO {

	public String getNameSpace() {
		return "relationship";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
