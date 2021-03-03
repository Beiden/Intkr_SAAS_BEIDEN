package com.intkr.saas.dao.fs.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.fs.ImgDAO;
import com.intkr.saas.domain.dbo.fs.ImgDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 13:02:47
 * @version 1.0
 */
@Repository("ImgDAO")
public class ImgDAOImpl extends BaseDAOImpl<ImgDO> implements ImgDAO {

	public String getNameSpace() {
		return "img";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
