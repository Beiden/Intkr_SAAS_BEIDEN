package com.intkr.saas.dao.item.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.item.AuctionDAO;
import com.intkr.saas.domain.dbo.item.AuctionDO;

/**
 * 
 * @author Beiden
 * @date 2016-5-18 上午10:30:56
 * @version 1.0
 */
@Repository("AuctionDAO")
public class AuctionDAOImpl extends BaseDAOImpl<AuctionDO> implements AuctionDAO {

	public String getNameSpace() {
		return "auction";
	}

}
