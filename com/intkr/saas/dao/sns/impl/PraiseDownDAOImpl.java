package com.intkr.saas.dao.sns.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.sns.PraiseDownDAO;
import com.intkr.saas.domain.dbo.sns.PraiseDownDO;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 下午1:36:16
 * @version 1.0
 */
@Repository("PraiseDownDAO")
public class PraiseDownDAOImpl extends BaseDAOImpl<PraiseDownDO> implements PraiseDownDAO {

	public String getNameSpace() {
		return "praiseDown";
	}

}
