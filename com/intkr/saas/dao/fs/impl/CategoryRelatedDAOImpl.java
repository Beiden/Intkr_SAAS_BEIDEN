package com.intkr.saas.dao.fs.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.fs.CategoryRelatedDAO;
import com.intkr.saas.domain.dbo.fs.MediaCategoryRelatedDO;

/**
 * 
 * @author Beiden
 * @date 2017-08-03 16:33:45
 * @version 1.0
 */
@Repository("CategoryRelatedDAO")
public class CategoryRelatedDAOImpl extends BaseDAOImpl<MediaCategoryRelatedDO> implements CategoryRelatedDAO {

	public String getNameSpace() {
		return "categoryRelated";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
