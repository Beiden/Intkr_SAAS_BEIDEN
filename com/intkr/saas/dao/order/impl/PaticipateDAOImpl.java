package com.intkr.saas.dao.order.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.order.PaticipateDAO;
import com.intkr.saas.domain.dbo.order.PaticipateDO;

/**
 * 
 * @author Beiden
 * @date 2016-4-14 上午8:23:12
 * @version 1.0
 */
@Repository("PaticipateDAO")
public class PaticipateDAOImpl extends BaseDAOImpl<PaticipateDO> implements PaticipateDAO {

	public String getNameSpace() {
		return "paticipate";
	}

}
