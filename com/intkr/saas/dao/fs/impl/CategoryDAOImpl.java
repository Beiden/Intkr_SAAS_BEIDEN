package com.intkr.saas.dao.fs.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.fs.CategoryDAO;
import com.intkr.saas.domain.dbo.fs.MediaCategoryDO;

/**
 * 
 * @author Beiden
 * @date 2017-08-03 16:33:22
 * @version 1.0
 */
@Repository("CategoryDAO")
public class CategoryDAOImpl extends BaseDAOImpl<MediaCategoryDO> implements CategoryDAO {

	public String getNameSpace() {
		return "category";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
