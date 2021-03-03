package com.intkr.saas.manager.sns.impl;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.sns.PraiseUpDAO;
import com.intkr.saas.domain.bo.sns.PraiseUpBO;
import com.intkr.saas.domain.dbo.sns.PraiseUpDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.sns.PraiseUpManager;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 下午1:37:14
 * @version 1.0
 */
@Repository("PraiseUpManager")
public class PraiseUpManagerImpl extends BaseManagerImpl<PraiseUpBO, PraiseUpDO> implements PraiseUpManager {

	@Resource
	private PraiseUpDAO praiseDAO;

	public BaseDAO<PraiseUpDO> getBaseDAO() {
		return praiseDAO;
	}

	public boolean existToday(Long userId, Long modelId, String type) {
		PraiseUpBO query = new PraiseUpBO();
		query.setUserId(userId);
		query.setType(type);
		query.setRelatedId(modelId);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		query.setQuery("startGmtCreated", c.getTime());
		List<PraiseUpBO> praiseUpBOList = select(query);
		if (praiseUpBOList.isEmpty()) {
			return false;
		}
		return true;
	}
}
