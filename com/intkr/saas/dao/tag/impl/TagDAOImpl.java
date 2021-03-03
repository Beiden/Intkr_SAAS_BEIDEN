package com.intkr.saas.dao.tag.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.tag.TagDAO;
import com.intkr.saas.domain.dbo.tag.TagDO;
import com.intkr.saas.dao.BaseDAOImpl;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 下午4:04:51
 * @version 1.0
 */
@Repository("TagTagDAO")
public class TagDAOImpl extends BaseDAOImpl<TagDO> implements TagDAO {

	public String getNameSpace() {
		return "tag";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
