package com.intkr.saas.manager.mms.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.mms.EmailGroupDAO;
import com.intkr.saas.domain.bo.mms.EmailGroupBO;
import com.intkr.saas.domain.dbo.mms.EmailGroupDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.mms.EmailGroupManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 18:56:31
 * @version 1.0
 */
@Repository("EmailGroupManager")
public class EmailGroupManagerImpl extends BaseManagerImpl<EmailGroupBO, EmailGroupDO> implements EmailGroupManager {

	@Resource
	private EmailGroupDAO mmsEmailGroupDAO;

	public BaseDAO<EmailGroupDO> getBaseDAO() {
		return mmsEmailGroupDAO;
	}

}
