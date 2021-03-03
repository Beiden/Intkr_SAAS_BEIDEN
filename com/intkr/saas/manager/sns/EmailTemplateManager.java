package com.intkr.saas.manager.sns;


import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.domain.bo.sns.EmailTemplateBO;
import com.intkr.saas.domain.dbo.sns.EmailTemplateDO;

/**
 * 
 * @author Beiden
 * @date 2016-5-24 上午10:48:14
 * @version 1.0
 */
public interface EmailTemplateManager extends BaseManager<EmailTemplateBO, EmailTemplateDO> {

	public EmailTemplateBO getByCode(String code);

}
