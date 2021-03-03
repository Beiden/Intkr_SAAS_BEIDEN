package com.intkr.saas.manager.sns;

import com.intkr.saas.domain.bo.sns.MsgSignatureBO;
import com.intkr.saas.domain.dbo.sns.MsgSignatureDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2016-3-22 上午10:46:34
 * @version 1.0
 */
public interface MsgSignatureManager extends BaseManager<MsgSignatureBO, MsgSignatureDO> {

	public String getDefault(Long saasId);

}
