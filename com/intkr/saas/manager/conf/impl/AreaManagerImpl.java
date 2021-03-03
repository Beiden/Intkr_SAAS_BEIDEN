package com.intkr.saas.manager.conf.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.conf.AreaDAO;
import com.intkr.saas.domain.bo.conf.AreaBO;
import com.intkr.saas.domain.dbo.conf.AreaDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.conf.AreaManager;

/**
 * 
 * @author Beiden
 * @date 2016-5-15 上午9:40:12
 * @version 1.0
 */
@Repository("AreaManager")
public class AreaManagerImpl extends BaseManagerImpl<AreaBO, AreaDO> implements AreaManager {

	@Resource
	private AreaDAO areaDataDAO;

	public BaseDAO<AreaDO> getBaseDAO() {
		return areaDataDAO;
	}

	public AreaBO getFull() {
		AreaBO query = new AreaBO();
		query.set_pageSize(10000);
		List<AreaBO> datas = select(query);
		Map<Long, AreaBO> map = new HashMap<Long, AreaBO>();
		for (AreaBO areaData : datas) {
			map.put(areaData.getId(), areaData);
		}
		for (AreaBO areaData : datas) {
			Long pid = areaData.getPid();
			if (pid != null && !"".equals(pid)) {
				AreaBO parent = map.get(pid);
				if (parent != null) {
					parent.addChild(areaData);
				}
			}
		}
		return map.get(100000L);
	}

}
