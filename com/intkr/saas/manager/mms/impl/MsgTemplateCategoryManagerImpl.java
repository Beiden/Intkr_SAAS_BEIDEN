package com.intkr.saas.manager.mms.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.mms.MsgTemplateCategoryDAO;
import com.intkr.saas.domain.bo.mms.MsgTemplateCategoryBO;
import com.intkr.saas.domain.dbo.mms.MsgTemplateCategoryDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.mms.MsgTemplateCategoryManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 19:01:46
 * @version 1.0
 */
@Repository("MsgTemplateCategoryManager")
public class MsgTemplateCategoryManagerImpl extends BaseManagerImpl<MsgTemplateCategoryBO, MsgTemplateCategoryDO> implements MsgTemplateCategoryManager {

	@Resource
	private MsgTemplateCategoryDAO mmsMsgTemplateCategoryDAO;

	public BaseDAO<MsgTemplateCategoryDO> getBaseDAO() {
		return mmsMsgTemplateCategoryDAO;
	}

}
