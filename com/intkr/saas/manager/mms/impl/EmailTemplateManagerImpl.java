package com.intkr.saas.manager.mms.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.mms.EmailTemplateDAO;
import com.intkr.saas.domain.bo.mms.EmailTemplateBO;
import com.intkr.saas.domain.dbo.mms.EmailTemplateDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.mms.EmailTemplateManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 18:58:05
 * @version 1.0
 */
@Repository("EmailTemplateManager")
public class EmailTemplateManagerImpl extends BaseManagerImpl<EmailTemplateBO, EmailTemplateDO> implements EmailTemplateManager {

	@Resource
	private EmailTemplateDAO mmsEmailTemplateDAO;

	public BaseDAO<EmailTemplateDO> getBaseDAO() {
		return mmsEmailTemplateDAO;
	}

}
