package com.intkr.saas.dao.mms.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.mms.EmailSignatureDAO;
import com.intkr.saas.domain.dbo.mms.EmailSignatureDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 18:55:30
 * @version 1.0
 */
@Repository("EmailSignatureDAO")
public class EmailSignatureDAOImpl extends BaseDAOImpl<EmailSignatureDO> implements EmailSignatureDAO {

	public String getNameSpace() {
		return "emailSignature";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
