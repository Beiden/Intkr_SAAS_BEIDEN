package com.intkr.saas.dao.mms.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.mms.MsgSignatureDAO;
import com.intkr.saas.domain.dbo.mms.MsgSignatureDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 19:02:23
 * @version 1.0
 */
@Repository("MsgSignatureDAO")
public class MsgSignatureDAOImpl extends BaseDAOImpl<MsgSignatureDO> implements MsgSignatureDAO {

	public String getNameSpace() {
		return "msgSignature";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
