package com.intkr.saas.dao.mms.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.mms.MsgWarningDAO;
import com.intkr.saas.domain.dbo.mms.MsgWarningDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 19:03:07
 * @version 1.0
 */
@Repository("MsgWarningDAO")
public class MsgWarningDAOImpl extends BaseDAOImpl<MsgWarningDO> implements MsgWarningDAO {

	public String getNameSpace() {
		return "msgWarning";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
