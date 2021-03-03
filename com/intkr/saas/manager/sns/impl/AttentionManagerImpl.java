package com.intkr.saas.manager.sns.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.sns.AttentionDAO;
import com.intkr.saas.domain.bo.sns.AttentionBO;
import com.intkr.saas.domain.dbo.sns.AttentionDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.sns.AttentionManager;

/**
 * 
 * @author Beiden
 * @date 2011-8-14 下午8:24:53
 * @version 1.0
 */
@Repository("AttentionManager")
public class AttentionManagerImpl extends BaseManagerImpl<AttentionBO, AttentionDO> implements AttentionManager {

	@Resource
	private AttentionDAO attentionDAO;

	public BaseDAO<AttentionDO> getBaseDAO() {
		return attentionDAO;
	}

	public Long countByUserId(Long userId) {
		if (userId == null || "".equals(userId)) {
			return 0L;
		}
		AttentionBO query = new AttentionBO();
		query.setUserId(userId);
		return count(query);
	}

	public Long countByRelatedId(Long relatedId) {
		if (relatedId == null || "".equals(relatedId)) {
			return 0L;
		}
		AttentionBO query = new AttentionBO();
		query.setRelatedId(relatedId);
		return count(query);
	}

}
