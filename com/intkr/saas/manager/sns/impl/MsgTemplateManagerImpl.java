package com.intkr.saas.manager.sns.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.sns.MsgTemplateDAO;
import com.intkr.saas.domain.bo.sns.MsgTemplateBO;
import com.intkr.saas.domain.dbo.sns.MsgTemplateDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.sns.MsgTemplateManager;

/**
 * 
 * @author Beiden
 * @date 2016-5-24 上午10:48:36
 * @version 1.0
 */
@Repository("sns.MsgTemplateManager")
public class MsgTemplateManagerImpl extends BaseManagerImpl<MsgTemplateBO, MsgTemplateDO> implements MsgTemplateManager {

	@Resource
	private MsgTemplateDAO snsMsgTemplateDAO;

	public BaseDAO<MsgTemplateDO> getBaseDAO() {
		return snsMsgTemplateDAO;
	}

	public MsgTemplateBO getByCode(Long saasId, String code) {
		MsgTemplateBO query = new MsgTemplateBO();
		query.setSaasId(saasId);
		query.setCode(code);
		query.set_pageSize(1);
		return selectOne(query);
	}

}
