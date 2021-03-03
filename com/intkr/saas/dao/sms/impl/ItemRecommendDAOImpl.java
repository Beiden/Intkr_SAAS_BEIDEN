package com.intkr.saas.dao.sms.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.sms.ItemRecommendDAO;
import com.intkr.saas.domain.dbo.sms.ItemRecommendDO;

/**
 * 品牌推荐
 * 
 * @table item_recommend
 * 
 * @author Beiden
 * @date 2020-11-11 22:56:29
 * @version 1.0
 */
@Repository("ItemRecommendDAO")
public class ItemRecommendDAOImpl extends BaseDAOImpl<ItemRecommendDO> implements ItemRecommendDAO {

	public String getNameSpace() {
		return "itemRecommend";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
