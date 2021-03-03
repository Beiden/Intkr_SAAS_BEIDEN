package com.intkr.saas.dao.sns.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.sns.LikeDAO;
import com.intkr.saas.domain.dbo.sns.LikeDO;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 下午12:58:40
 * @version 1.0
 */
@Repository("LikeDAO")
public class LikeDAOImpl extends BaseDAOImpl<LikeDO> implements LikeDAO {

	public String getNameSpace() {
		return "like";
	}

}
