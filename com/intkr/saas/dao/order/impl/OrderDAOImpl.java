package com.intkr.saas.dao.order.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.order.OrderDAO;
import com.intkr.saas.domain.dbo.order.OrderDO;
import com.intkr.saas.engine.db.MyBatisEngine;
import com.intkr.saas.util.map.TwoMap;

/**
 * 
 * @author Beiden
 * @date 2011-7-27 下午5:46:04
 * @version 1.0
 */
@Repository("OrderDAO")
public class OrderDAOImpl extends BaseDAOImpl<OrderDO> implements OrderDAO {

	public String getNameSpace() {
		return "order";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

	public List<TwoMap<String, Integer>> countGroupByStatus(OrderDO order) {
		List<TwoMap<String, Integer>> returnList = new ArrayList<TwoMap<String, Integer>>();
		SqlSession session = MyBatisEngine.getSession(getDataSourceName());
		try {
			List<Map> dtoList = session.selectList(getNameSpace() + ".countGroupByStatus", order);
			for (Map map : dtoList) {
				TwoMap<String, Integer> data = new TwoMap<String, Integer>();
				data.setOne((String) map.get("status"));
				data.setTwo(((Number) map.get("count")).intValue());
				returnList.add(data);
			}
			session.commit();
		} finally {
			session.close();
		}
		return returnList;
	}

	public List<TwoMap<String, Integer>> buyerCountByStatus(Long userId) {
		OrderDO order = new OrderDO();
		order.setUserId(userId);
		List<TwoMap<String, Integer>> list = countGroupByStatus(order);
		initStatus(list, "waitConfirm");
		initStatus(list, "waitPay");
		initStatus(list, "waitSendOut");
		initStatus(list, "waitReceipt");
		initStatus(list, "waitComment");
		initStatus(list, "finished");
		initStatus(list, "cancel");
		initStatus(list, "error");
		return list;
	}

	private void initStatus(List<TwoMap<String, Integer>> list, String status) {
		boolean has = false;
		for (TwoMap<String, Integer> data : list) {
			if (data.getOne().equals(status)) {
				has = true;
			}
		}
		if (!has) {
			TwoMap<String, Integer> data = new TwoMap<String, Integer>();
			data.setOne(status);
			data.setTwo(0);
			list.add(data);
		}
	}
}
