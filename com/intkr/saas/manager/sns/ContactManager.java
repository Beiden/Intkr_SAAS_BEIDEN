package com.intkr.saas.manager.sns;


import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.domain.bo.sns.ContactBO;
import com.intkr.saas.domain.dbo.sns.ContactDO;

/**
 * 
 * @author Beiden
 * @date 2011-7-8 上午10:21:36
 * @version 1.0
 */
public interface ContactManager extends BaseManager<ContactBO, ContactDO> {

	public ContactBO get(Long userId, Long contactId);

}
