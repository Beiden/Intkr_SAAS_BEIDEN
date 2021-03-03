package com.intkr.saas.manager.sns.impl;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.sns.PraiseDownDAO;
import com.intkr.saas.domain.bo.sns.PraiseDownBO;
import com.intkr.saas.domain.dbo.sns.PraiseDownDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.sns.PraiseDownManager;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 下午1:37:14
 * @version 1.0
 */
@Repository("PraiseDownManager")
public class PraiseDownManagerImpl extends BaseManagerImpl<PraiseDownBO, PraiseDownDO> implements PraiseDownManager {

	@Resource
	private PraiseDownDAO praiseDAO;

	public BaseDAO<PraiseDownDO> getBaseDAO() {
		return praiseDAO;
	}

	public boolean existToday(Long userId, Long modelId, String type) {
		PraiseDownBO query = new PraiseDownBO();
		query.setUserId(userId);
		query.setType(type);
		query.setRelatedId(modelId);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		query.setQuery("startGmtCreated", c.getTime());
		List<PraiseDownBO> praiseDownBOList = select(query);
		if (praiseDownBOList.isEmpty()) {
			return false;
		}
		return true;
	}

}
