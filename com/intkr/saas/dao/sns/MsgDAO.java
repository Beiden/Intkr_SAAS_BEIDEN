package com.intkr.saas.dao.sns;


import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.domain.dbo.sns.ContactDO;
import com.intkr.saas.domain.dbo.sns.MsgDO;

/**
 * 
 * @author Beiden
 * @date 2011-7-7 上午9:04:29
 * @version 1.0
 */
public interface MsgDAO extends BaseDAO<MsgDO> {

	public int readMessages(ContactDO contact);

}
