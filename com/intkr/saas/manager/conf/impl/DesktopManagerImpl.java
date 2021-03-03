package com.intkr.saas.manager.conf.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.conf.DesktopDAO;
import com.intkr.saas.domain.bo.conf.DesktopBO;
import com.intkr.saas.domain.dbo.conf.DesktopDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.conf.DesktopManager;

/**
 * 
 * @author Beiden
 * @date 2016-07-05 17:44:34
 * @version 1.0
 */
@Repository("DesktopManager")
public class DesktopManagerImpl extends BaseManagerImpl<DesktopBO, DesktopDO> implements DesktopManager {

	@Resource
	private DesktopDAO carDesktopDAO;

	public BaseDAO<DesktopDO> getBaseDAO() {
		return carDesktopDAO;
	}

	public void clear(Long userId) {
		if (!IdEngine.isValidate(userId)) {
			return;
		}
		DesktopBO query = new DesktopBO();
		query.setUserId(userId);
		List<DesktopBO> list = select(query);
		for (DesktopBO bo : list) {
			delete(bo.getId());
		}
	}

}
