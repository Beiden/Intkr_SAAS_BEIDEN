package com.intkr.saas.dao.sns.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.sns.MsgRecordDAO;
import com.intkr.saas.domain.dbo.sns.MsgRecordDO;

/**
 * 
 * @author Beiden
 * @date 2016-3-22 上午11:26:20
 * @version 1.0
 */
@Repository("sns.MsgRecordDAO")
public class MsgRecordDAOImpl extends BaseDAOImpl<MsgRecordDO> implements MsgRecordDAO {

	public String getNameSpace() {
		return "msgRecord";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
