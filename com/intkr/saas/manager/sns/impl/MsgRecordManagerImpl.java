package com.intkr.saas.manager.sns.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.sns.MsgRecordDAO;
import com.intkr.saas.domain.bo.sns.MsgRecordBO;
import com.intkr.saas.domain.dbo.sns.MsgRecordDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.sns.MsgRecordManager;

/**
 * 
 * @author Beiden
 * @date 2016-3-22 上午11:27:16
 * @version 1.0
 */
@Repository("sns.MsgRecordManager")
public class MsgRecordManagerImpl extends BaseManagerImpl<MsgRecordBO, MsgRecordDO> implements MsgRecordManager {

	@Resource
	private MsgRecordDAO snsMsgRecordDAO;

	public BaseDAO<MsgRecordDO> getBaseDAO() {
		return snsMsgRecordDAO;
	}

}
