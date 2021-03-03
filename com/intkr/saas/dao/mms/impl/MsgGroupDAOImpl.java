package com.intkr.saas.dao.mms.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.mms.MsgGroupDAO;
import com.intkr.saas.domain.dbo.mms.MsgGroupDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 19:00:31
 * @version 1.0
 */
@Repository("MsgGroupDAO")
public class MsgGroupDAOImpl extends BaseDAOImpl<MsgGroupDO> implements MsgGroupDAO {

	public String getNameSpace() {
		return "msgGroup";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
