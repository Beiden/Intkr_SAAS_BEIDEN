package com.intkr.saas.manager.cms.menu;

import java.util.List;

import com.intkr.saas.domain.bo.menu.FrontMenuBO;
import com.intkr.saas.domain.bo.menu.FrontMenuDetailBO;
import com.intkr.saas.domain.dbo.menu.FrontMenuDetailDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2011-9-30 下午8:13:42
 * @version 1.0
 */
public interface FrontMenuDetailManager extends BaseManager<FrontMenuDetailBO, FrontMenuDetailDO> {

	public FrontMenuBO fill(FrontMenuBO frontMenuBO);

	public List<FrontMenuDetailBO> getByFrontMenuId(Long menuId);

}
