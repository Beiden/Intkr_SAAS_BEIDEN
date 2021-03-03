package com.intkr.saas.dao.tag.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.tag.TagRelatedDAO;
import com.intkr.saas.domain.dbo.tag.TagRelatedDO;
import com.intkr.saas.dao.BaseDAOImpl;

/**
 * 
 * @author Beiden
 * @date 2011-9-30 下午6:06:10
 * @version 1.0
 */
@Repository("TagRelatedDAO")
public class TagRelatedDAOImpl extends BaseDAOImpl<TagRelatedDO> implements TagRelatedDAO {

	public String getNameSpace() {
		return "tagRelated";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
