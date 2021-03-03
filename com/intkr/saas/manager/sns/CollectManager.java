package com.intkr.saas.manager.sns;


import com.intkr.saas.domain.bo.sns.CollectBO;
import com.intkr.saas.domain.dbo.sns.CollectDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 上午11:22:43
 * @version 1.0
 */
public interface CollectManager extends BaseManager<CollectBO, CollectDO> {

	public boolean exist(CollectBO collect);

}
