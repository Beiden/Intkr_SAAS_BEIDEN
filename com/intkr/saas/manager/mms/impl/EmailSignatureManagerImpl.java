package com.intkr.saas.manager.mms.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.mms.EmailSignatureDAO;
import com.intkr.saas.domain.bo.mms.EmailSignatureBO;
import com.intkr.saas.domain.dbo.mms.EmailSignatureDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.mms.EmailSignatureManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 18:55:30
 * @version 1.0
 */
@Repository("EmailSignatureManager")
public class EmailSignatureManagerImpl extends BaseManagerImpl<EmailSignatureBO, EmailSignatureDO> implements EmailSignatureManager {

	@Resource
	private EmailSignatureDAO mmsEmailSignatureDAO;

	public BaseDAO<EmailSignatureDO> getBaseDAO() {
		return mmsEmailSignatureDAO;
	}

}
