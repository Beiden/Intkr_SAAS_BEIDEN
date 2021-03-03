package com.intkr.saas.manager.sns.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.sns.MsgSignatureDAO;
import com.intkr.saas.domain.bo.sns.MsgSignatureBO;
import com.intkr.saas.domain.dbo.sns.MsgSignatureDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.sns.MsgSignatureManager;

/**
 * 
 * @author Beiden
 * @date 2016-3-22 上午10:46:54
 * @version 1.0
 */
@Repository("sns.MsgSignatureManager")
public class MsgSignatureManagerImpl extends BaseManagerImpl<MsgSignatureBO, MsgSignatureDO> implements MsgSignatureManager {

	@Resource
	private MsgSignatureDAO snsMsgSignatureDAO;

	public BaseDAO<MsgSignatureDO> getBaseDAO() {
		return snsMsgSignatureDAO;
	}

	public String getDefault(Long saasId) {
		MsgSignatureBO query = new MsgSignatureBO();
		query.setSaasId(saasId);
		query.setIsDefault(1);
		MsgSignatureBO sign = selectOne(query);
		if (sign != null) {
			return sign.getContent();
		}
		return null;
	}

}
