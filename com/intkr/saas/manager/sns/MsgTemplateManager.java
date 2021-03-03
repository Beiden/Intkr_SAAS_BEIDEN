package com.intkr.saas.manager.sns;

import com.intkr.saas.domain.bo.sns.MsgTemplateBO;
import com.intkr.saas.domain.dbo.sns.MsgTemplateDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2016-5-24 上午10:48:14
 * @version 1.0
 */
public interface MsgTemplateManager extends BaseManager<MsgTemplateBO, MsgTemplateDO> {

	public MsgTemplateBO getByCode(Long saasId, String code);

}
