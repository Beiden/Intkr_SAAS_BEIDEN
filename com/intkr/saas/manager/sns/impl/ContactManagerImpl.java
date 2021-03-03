package com.intkr.saas.manager.sns.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.sns.ContactDAO;
import com.intkr.saas.domain.bo.sns.ContactBO;
import com.intkr.saas.domain.dbo.sns.ContactDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.sns.ContactManager;

/**
 * 
 * @author Beiden
 * @date 2011-7-8 上午10:21:56
 * @version 1.0
 */
@Repository("sns.ContactManager")
public class ContactManagerImpl extends BaseManagerImpl<ContactBO, ContactDO> implements ContactManager {

	@Resource
	private ContactDAO contactDAO;

	public BaseDAO<ContactDO> getBaseDAO() {
		return contactDAO;
	}

	public ContactBO get(Long userId, Long contactId) {
		ContactBO query = new ContactBO();
		query.setUserId(userId);
		query.setContactId(contactId);
		query.set_pageSize(1);
		List<ContactBO> list = select(query);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

}
