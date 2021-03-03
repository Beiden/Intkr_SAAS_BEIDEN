package com.intkr.saas.manager.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.user.SecureQuestionDAO;
import com.intkr.saas.domain.bo.user.SecureQuestionBO;
import com.intkr.saas.domain.dbo.user.SecureQuestionDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.user.SecureQuestionManager;
import com.intkr.saas.client.conf.IdEngine;

/**
 * 
 * @author Beiden
 * @date 2016-5-28 下午10:53:17
 * @version 1.0
 */
@Repository("SecureQuestionManager")
public class SecureQuestionManagerImpl extends BaseManagerImpl<SecureQuestionBO, SecureQuestionDO> implements SecureQuestionManager {

	@Resource
	private SecureQuestionDAO secureQuestionDAO;

	public BaseDAO<SecureQuestionDO> getBaseDAO() {
		return secureQuestionDAO;
	}

	public List<SecureQuestionBO> getSecureQuestion(Long userId) {
		if (!IdEngine.isValidate(userId)) {
			return new ArrayList<SecureQuestionBO>();
		}
		SecureQuestionBO query = new SecureQuestionBO();
		query.setUserId(userId);
		query.setQuery("orderBy", "code");
		query.setQuery("order", "desc");
		List<SecureQuestionBO> list = select(query);
		return list;
	}

}
