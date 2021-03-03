package com.intkr.saas.manager.mms.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.mms.MsgServerDAO;
import com.intkr.saas.domain.bo.mms.MsgServerBO;
import com.intkr.saas.domain.dbo.mms.MsgServerDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.mms.MsgServerManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 19:04:27
 * @version 1.0
 */
@Repository("MsgServerManager")
public class MsgServerManagerImpl extends BaseManagerImpl<MsgServerBO, MsgServerDO> implements MsgServerManager {

	@Resource
	private MsgServerDAO mmsMsgServerDAO;

	public BaseDAO<MsgServerDO> getBaseDAO() {
		return mmsMsgServerDAO;
	}

}
