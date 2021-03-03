package com.intkr.saas.dao.fs.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.fs.MediaDAO;
import com.intkr.saas.domain.dbo.fs.MediaDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 13:02:47
 * @version 1.0
 */
@Repository("MediaDAO")
public class MediaDAOImpl extends BaseDAOImpl<MediaDO> implements MediaDAO {

	public String getNameSpace() {
		return "media";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
