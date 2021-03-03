package com.intkr.saas.manager.fs;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.domain.bo.fs.MediaBO;
import com.intkr.saas.domain.dbo.fs.MediaDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 13:02:47
 * @version 1.0
 */
public interface MediaManager extends BaseManager<MediaBO, MediaDO> {

	public MediaBO getByName(String name);

}
