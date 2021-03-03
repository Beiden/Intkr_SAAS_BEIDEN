package com.intkr.saas.manager.sns;

import java.util.List;

import com.intkr.saas.domain.bo.sns.ContactBO;
import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.domain.dbo.sns.MsgDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2011-7-7 上午9:05:15
 * @version 1.0
 */
public interface MsgManager extends BaseManager<MsgBO, MsgDO> {

	public ContactBO fillLastMessage(ContactBO contact);

	public List<Object> fillLastMessage(List<Object> contactList);

	/**
	 * 阅读这个联系人发来的所有消息
	 * 
	 * @param contact
	 */
	public int readMessages(ContactBO contact);

	public void sendSysMsg(Long toUserId, String title, String content);
	
	public void sendSaasMsg(Long saas, String title, String content);

}
