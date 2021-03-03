package com.intkr.saas.dao.order;

import java.util.List;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.domain.dbo.order.OrderDO;
import com.intkr.saas.util.map.TwoMap;

/**
 * 
 * @author Beiden
 * @date 2011-7-27 下午5:45:20
 * @version 1.0
 */
public interface OrderDAO extends BaseDAO<OrderDO> {

	public List<TwoMap<String, Integer>> buyerCountByStatus(Long userId);

}
