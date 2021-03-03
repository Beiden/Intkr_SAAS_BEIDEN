package com.intkr.saas.manager.mms.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.mms.MsgTemplateDAO;
import com.intkr.saas.domain.bo.mms.MsgTemplateBO;
import com.intkr.saas.domain.dbo.mms.MsgTemplateDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.mms.MsgTemplateManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 19:03:45
 * @version 1.0
 */
@Repository("MsgTemplateManager")
public class MsgTemplateManagerImpl extends BaseManagerImpl<MsgTemplateBO, MsgTemplateDO> implements MsgTemplateManager {

	@Resource
	private MsgTemplateDAO mmsMsgTemplateDAO;

	public BaseDAO<MsgTemplateDO> getBaseDAO() {
		return mmsMsgTemplateDAO;
	}

}
