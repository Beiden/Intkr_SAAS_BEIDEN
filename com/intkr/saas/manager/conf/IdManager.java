package com.intkr.saas.manager.conf;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.domain.bo.conf.IkIdBO;
import com.intkr.saas.domain.dbo.conf.IkIdDO;

/**
 * 
 * @author Beiden
 * @date 2016-07-31 18:04:42
 * @version 1.0
 */
public interface IdManager extends BaseManager<IkIdBO, IkIdDO> {

	public IkIdBO getByTableName(String tableName);

}
