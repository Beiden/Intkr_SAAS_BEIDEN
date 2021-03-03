package com.intkr.saas.manager.sns;


import com.intkr.saas.domain.bo.sns.AttentionBO;
import com.intkr.saas.domain.dbo.sns.AttentionDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2011-8-14 下午8:24:33
 * @version 1.0
 */
public interface AttentionManager extends BaseManager<AttentionBO, AttentionDO> {

	public Long countByUserId(Long userId);

	public Long countByRelatedId(Long relatedId);

}
