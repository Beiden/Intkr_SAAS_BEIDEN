package com.intkr.saas.manager.mms.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.mms.EmailSettingDAO;
import com.intkr.saas.domain.bo.mms.EmailSettingBO;
import com.intkr.saas.domain.dbo.mms.EmailSettingDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.mms.EmailSettingManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 18:53:32
 * @version 1.0
 */
@Repository("EmailSettingManager")
public class EmailSettingManagerImpl extends BaseManagerImpl<EmailSettingBO, EmailSettingDO> implements EmailSettingManager {

	@Resource
	private EmailSettingDAO mmsEmailSettingDAO;

	public BaseDAO<EmailSettingDO> getBaseDAO() {
		return mmsEmailSettingDAO;
	}

}
