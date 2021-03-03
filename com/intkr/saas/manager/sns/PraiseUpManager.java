package com.intkr.saas.manager.sns;


import com.intkr.saas.domain.bo.sns.PraiseUpBO;
import com.intkr.saas.domain.dbo.sns.PraiseUpDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 下午1:36:51
 * @version 1.0
 */
public interface PraiseUpManager extends BaseManager<PraiseUpBO, PraiseUpDO> {

	public boolean existToday(Long userId, Long modelId, String type);

}
