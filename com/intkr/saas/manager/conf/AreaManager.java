package com.intkr.saas.manager.conf;


import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.domain.bo.conf.AreaBO;
import com.intkr.saas.domain.dbo.conf.AreaDO;

/**
 * 
 * @author Beiden
 * @date 2016-5-15 上午9:39:44
 * @version 1.0
 */
public interface AreaManager extends BaseManager<AreaBO, AreaDO> {

	public AreaBO getFull();

}
