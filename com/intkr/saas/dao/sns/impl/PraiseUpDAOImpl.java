package com.intkr.saas.dao.sns.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.sns.PraiseUpDAO;
import com.intkr.saas.domain.dbo.sns.PraiseUpDO;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 下午1:36:16
 * @version 1.0
 */
@Repository("PraiseUpDAO")
public class PraiseUpDAOImpl extends BaseDAOImpl<PraiseUpDO> implements PraiseUpDAO {

	public String getNameSpace() {
		return "praiseUp";
	}

}
