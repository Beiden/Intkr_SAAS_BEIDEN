package com.intkr.saas.manager.mms.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.mms.MsgSignatureDAO;
import com.intkr.saas.domain.bo.mms.MsgSignatureBO;
import com.intkr.saas.domain.dbo.mms.MsgSignatureDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.mms.MsgSignatureManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 19:02:23
 * @version 1.0
 */
@Repository("MsgSignatureManager")
public class MsgSignatureManagerImpl extends BaseManagerImpl<MsgSignatureBO, MsgSignatureDO> implements MsgSignatureManager {

	@Resource
	private MsgSignatureDAO mmsMsgSignatureDAO;

	public BaseDAO<MsgSignatureDO> getBaseDAO() {
		return mmsMsgSignatureDAO;
	}

}
