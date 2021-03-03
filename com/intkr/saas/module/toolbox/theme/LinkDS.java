package com.intkr.saas.module.toolbox.theme;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
public class LinkDS extends BaseToolBox {

	private LinkManager linkManager = IOC.get("LinkManager");

	public List<LinkBO> select(HttpServletRequest request) {
		LinkBO query = new LinkBO();
		query.setType("friendLink");
		query = linkManager.selectAndCount(query);
		return query.getDatas();
	}

}
