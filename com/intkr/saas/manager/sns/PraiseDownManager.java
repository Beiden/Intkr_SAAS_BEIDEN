package com.intkr.saas.manager.sns;


import com.intkr.saas.domain.bo.sns.PraiseDownBO;
import com.intkr.saas.domain.dbo.sns.PraiseDownDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 下午1:36:51
 * @version 1.0
 */
public interface PraiseDownManager extends BaseManager<PraiseDownBO, PraiseDownDO> {

	public boolean existToday(Long userId, Long modelId, String type);

}
