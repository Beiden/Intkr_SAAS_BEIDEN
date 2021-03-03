package com.intkr.saas.dao.sns.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.sns.SysImgDAO;
import com.intkr.saas.domain.dbo.sns.SysImgDO;

/**
 * 
 * @author Beiden
 * @date 2016-5-26 下午2:23:08
 * @version 1.0
 */
@Repository("SyImgDAO")
public class SysImgDAOImpl extends BaseDAOImpl<SysImgDO> implements SysImgDAO {

	public String getNameSpace() {
		return "sysImg";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
