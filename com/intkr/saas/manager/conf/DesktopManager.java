package com.intkr.saas.manager.conf;


import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.domain.bo.conf.DesktopBO;
import com.intkr.saas.domain.dbo.conf.DesktopDO;

/**
 * 
 * @author Beiden
 * @date 2016-07-05 17:44:34
 * @version 1.0
 */
public interface DesktopManager extends BaseManager<DesktopBO, DesktopDO> {

	public void clear(Long userId);

}
