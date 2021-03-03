package com.intkr.saas.manager.cms.menu.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.cms.menu.FrontMenuDetailDAO;
import com.intkr.saas.domain.bo.menu.FrontMenuBO;
import com.intkr.saas.domain.bo.menu.FrontMenuDetailBO;
import com.intkr.saas.domain.dbo.menu.FrontMenuDetailDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.cms.menu.FrontMenuDetailManager;

/**
 * 
 * @author Beiden
 * @date 2011-4-29 下午9:18:23
 * @version 1.0
 */
@Repository("FrontMenuDetailManager")
public class FrontMenuDetailManagerImpl extends BaseManagerImpl<FrontMenuDetailBO, FrontMenuDetailDO> implements FrontMenuDetailManager {

	@Resource
	private FrontMenuDetailDAO frontMenuDetailDAO;

	public BaseDAO<FrontMenuDetailDO> getBaseDAO() {
		return frontMenuDetailDAO;
	}

	public FrontMenuBO fill(FrontMenuBO frontMenuBO) {
		if (frontMenuBO == null) {
			return frontMenuBO;
		}
		FrontMenuDetailBO query = new FrontMenuDetailBO();
		query.set_pageSize(10000);
		query.setFrontMenuId(frontMenuBO.getId());
		List<FrontMenuDetailBO> detailList = select(query);
		for (FrontMenuDetailBO parent : detailList) {
			for (FrontMenuDetailBO child : detailList) {
				if (child.getPid() == null) {
					continue;
				}
				if (parent.getId().equals(child.getPid())) {
					parent.addChild(child);
					child.setParent(parent);
				}
			}
		}
		List<FrontMenuDetailBO> details = new ArrayList<FrontMenuDetailBO>();
		for (FrontMenuDetailBO detail : detailList) {
			if (detail.getParent() == null) {
				details.add(detail);
			}
			detail.setLevel(detail.calculateLevel());
		}
		frontMenuBO.setDetails(details);
		return frontMenuBO;
	}

	public List<FrontMenuDetailBO> getByFrontMenuId(Long menuId) {
		if (!IdEngine.isValidate(menuId)) {
			return new ArrayList<FrontMenuDetailBO>();
		}
		FrontMenuDetailBO query = new FrontMenuDetailBO();
		query.set_pageSize(1000);
		query.setFrontMenuId(menuId);
		return select(query);
	}

}
