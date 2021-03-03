package com.intkr.saas.manager.mms.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.mms.MsgGroupDAO;
import com.intkr.saas.domain.bo.mms.MsgGroupBO;
import com.intkr.saas.domain.dbo.mms.MsgGroupDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.mms.MsgGroupManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 19:00:31
 * @version 1.0
 */
@Repository("MsgGroupManager")
public class MsgGroupManagerImpl extends BaseManagerImpl<MsgGroupBO, MsgGroupDO> implements MsgGroupManager {

	@Resource
	private MsgGroupDAO mmsMsgGroupDAO;

	public BaseDAO<MsgGroupDO> getBaseDAO() {
		return mmsMsgGroupDAO;
	}

}
