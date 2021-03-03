package com.intkr.saas.module.toolbox.theme;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.domain.bo.tool.ToolBO;
import com.intkr.saas.manager.cms.tool.ToolManager;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-10-18 上午10:08:20
 * @version 1.0
 */
public class ToolDS extends BaseToolBox {

	private ToolManager toolManager = IOC.get("ToolManager");

	public List<ToolBO> get(HttpServletRequest request, String sidebar) {
		ToolBO query = new ToolBO();
		query.setSidebar(sidebar);
		query.set_pageSize(1000);
		return toolManager.select(query);
	}

}
