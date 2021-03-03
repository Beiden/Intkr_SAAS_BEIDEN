package com.intkr.saas.manager.sns.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.sns.LikeDAO;
import com.intkr.saas.domain.bo.sns.LikeBO;
import com.intkr.saas.domain.dbo.sns.LikeDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.sns.LikeManager;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 下午12:59:19
 * @version 1.0
 */
@Repository("LikeManager")
public class LikeManagerImpl extends BaseManagerImpl<LikeBO, LikeDO> implements LikeManager {

	@Resource
	private LikeDAO likeDAO;

	public BaseDAO<LikeDO> getBaseDAO() {
		return likeDAO;
	}

}
