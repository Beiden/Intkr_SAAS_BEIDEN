package com.intkr.saas.manager.conf;

import com.intkr.saas.domain.bo.conf.OptionBO;
import com.intkr.saas.domain.dbo.conf.OptionDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2011-5-7 下午12:46:09
 * @version 1.0
 */
public interface OptionManager extends BaseManager<OptionBO, OptionDO> {

	public String getValueByName(Long saasId, String name);

	public OptionBO getByName(Long saasId, String name);

	public OptionBO increaseCount(Long saasId, String name);

	public void updateOrInsert(Long saasId, String key, String value);
	
	public String getAdminPhone(Long saasId);
	
	public String getAdminEmail(Long saasId);
	
	public boolean taskOpen();

	public boolean domainMonitorOpen();

	public boolean dbMonitorOpen();

	public String getWebsiteTitle(Long saasId);

	public Integer getWaitPayOrderAutoCloseTime(Long saasId);

	public boolean compareAndSet(Long id, String expectValue, String value);

	public boolean authorityOpen(Long saasId);

	public void closeAuthority(Long saasId);

	public void openAuthority(Long saasId);

	public boolean isSysLog();

	public boolean isCollectRight();

}
