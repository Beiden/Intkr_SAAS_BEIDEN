package com.intkr.saas.manager.sns.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.sns.EmailTemplateDAO;
import com.intkr.saas.domain.bo.sns.EmailTemplateBO;
import com.intkr.saas.domain.dbo.sns.EmailTemplateDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.sns.EmailTemplateManager;

/**
 * 
 * @author Beiden
 * @date 2016-5-24 上午10:48:36
 * @version 1.0
 */
@Repository("sns.EmailTemplateManager")
public class EmailTemplateManagerImpl extends BaseManagerImpl<EmailTemplateBO, EmailTemplateDO> implements EmailTemplateManager {

	@Resource
	private EmailTemplateDAO emailTemplateDAO;

	public BaseDAO<EmailTemplateDO> getBaseDAO() {
		return emailTemplateDAO;
	}

	public EmailTemplateBO getByCode(String code) {
		if (code == null || "".equals(code)) {
			return null;
		}
		EmailTemplateBO query = new EmailTemplateBO();
		query.setCode(code);
		query.set_pageSize(1);
		return selectOne(query);
	}

}
