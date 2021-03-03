package com.intkr.saas.manager.user;

import java.util.List;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.domain.bo.user.SecureQuestionBO;
import com.intkr.saas.domain.dbo.user.SecureQuestionDO;

/**
 * 
 * @author Beiden
 * @date 2016-5-28 下午10:52:52
 * @version 1.0
 */
public interface SecureQuestionManager extends BaseManager<SecureQuestionBO, SecureQuestionDO> {

	public List<SecureQuestionBO> getSecureQuestion(Long userId);

}
