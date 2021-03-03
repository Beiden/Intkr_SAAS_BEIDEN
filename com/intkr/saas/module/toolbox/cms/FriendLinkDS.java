package com.intkr.saas.module.toolbox.cms;

import java.util.List;

import com.intkr.saas.domain.bo.link.LinkBO;
import com.intkr.saas.manager.saas.LinkManager;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-9-20 上午9:50:56
 * @version 1.0
 */
public class FriendLinkDS extends BaseToolBox {

	private LinkManager linkManager = IOC.get(LinkManager.class);

	public List<LinkBO> select() {
		LinkBO query = new LinkBO();
		query.setType("friendLink");
		List<LinkBO> list = linkManager.select(query);
		return list;
	}

}
