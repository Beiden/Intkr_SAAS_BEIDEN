package com.intkr.saas.dao.mms.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.mms.MsgServerDAO;
import com.intkr.saas.domain.dbo.mms.MsgServerDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 19:04:27
 * @version 1.0
 */
@Repository("MsgServerDAO")
public class MsgServerDAOImpl extends BaseDAOImpl<MsgServerDO> implements MsgServerDAO {

	public String getNameSpace() {
		return "msgServer";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
