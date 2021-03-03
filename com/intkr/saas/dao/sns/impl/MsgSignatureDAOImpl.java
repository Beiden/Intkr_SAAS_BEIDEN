package com.intkr.saas.dao.sns.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.sns.MsgSignatureDAO;
import com.intkr.saas.domain.dbo.sns.MsgSignatureDO;

/**
 * 
 * @author Beiden
 * @date 2016-3-22 上午10:45:58
 * @version 1.0
 */
@Repository("sns.MsgSignatureDAO")
public class MsgSignatureDAOImpl extends BaseDAOImpl<MsgSignatureDO> implements MsgSignatureDAO {

	public String getNameSpace() {
		return "msgSignature";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
