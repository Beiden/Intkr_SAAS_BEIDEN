package com.intkr.saas.manager.mms.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.mms.MsgWarningDAO;
import com.intkr.saas.domain.bo.mms.MsgWarningBO;
import com.intkr.saas.domain.dbo.mms.MsgWarningDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.mms.MsgWarningManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 19:03:07
 * @version 1.0
 */
@Repository("MsgWarningManager")
public class MsgWarningManagerImpl extends BaseManagerImpl<MsgWarningBO, MsgWarningDO> implements MsgWarningManager {

	@Resource
	private MsgWarningDAO mmsMsgWarningDAO;

	public BaseDAO<MsgWarningDO> getBaseDAO() {
		return mmsMsgWarningDAO;
	}

}
