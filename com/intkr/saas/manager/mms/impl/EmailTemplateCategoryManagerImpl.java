package com.intkr.saas.manager.mms.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.mms.EmailTemplateCategoryDAO;
import com.intkr.saas.domain.bo.mms.EmailTemplateCategoryBO;
import com.intkr.saas.domain.dbo.mms.EmailTemplateCategoryDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.mms.EmailTemplateCategoryManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 18:57:31
 * @version 1.0
 */
@Repository("EmailTemplateCategoryManager")
public class EmailTemplateCategoryManagerImpl extends BaseManagerImpl<EmailTemplateCategoryBO, EmailTemplateCategoryDO> implements EmailTemplateCategoryManager {

	@Resource
	private EmailTemplateCategoryDAO mmsEmailTemplateCategoryDAO;

	public BaseDAO<EmailTemplateCategoryDO> getBaseDAO() {
		return mmsEmailTemplateCategoryDAO;
	}

}
