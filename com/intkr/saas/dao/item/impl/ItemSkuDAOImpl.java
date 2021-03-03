package com.intkr.saas.dao.item.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.item.ItemSkuDAO;
import com.intkr.saas.domain.dbo.item.ItemSkuDO;
import com.intkr.saas.engine.db.MyBatisEngine;

/**
 * 
 * @author Beiden
 * @date 2017-08-03 16:43:53
 * @version 1.0
 */
@Repository("ItemSkuDAO")
public class ItemSkuDAOImpl extends BaseDAOImpl<ItemSkuDO> implements ItemSkuDAO {

	public String getNameSpace() {
		return "itemSku";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

	public boolean updateInventory(Long skuId, Integer inventory) {
		int result;
		SqlSession session = MyBatisEngine.getSession(getDataSourceName());
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("skuId", skuId);
			param.put("inventory", inventory);
			result = session.update(getNameSpace() + ".updateInventory", param);
			session.commit();
		} finally {
			session.close();
		}
		return result > 0;
	}

	public boolean increaseInventory(Long skuId, Integer count) {
		int result;
		SqlSession session = MyBatisEngine.getSession(getDataSourceName());
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("skuId", skuId);
			param.put("count", count);
			result = session.update(getNameSpace() + ".increaseInventory", param);
			session.commit();
		} finally {
			session.close();
		}
		return result > 0;
	}

	public boolean decreaseInventory(Long skuId, Integer count) {
		int result;
		SqlSession session = MyBatisEngine.getSession(getDataSourceName());
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("skuId", skuId);
			param.put("count", count);
			result = session.update(getNameSpace() + ".decreaseInventory", param);
			session.commit();
		} finally {
			session.close();
		}
		return result > 0;
	}

}
