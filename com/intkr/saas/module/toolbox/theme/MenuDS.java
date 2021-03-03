package com.intkr.saas.module.toolbox.theme;

import java.util.List;

import javax.servlet.ServletRequest;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.conf.OptionBO;
import com.intkr.saas.domain.bo.menu.FrontMenuBO;
import com.intkr.saas.domain.bo.menu.FrontMenuDetailBO;
import com.intkr.saas.manager.cms.menu.FrontMenuDetailManager;
import com.intkr.saas.manager.cms.menu.FrontMenuManager;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-9-20 上午9:50:56
 * @version 1.0
 */
public class MenuDS extends BaseToolBox {

	public String mainMenu = "cms_main_menu";

	public String subMenu = "cms_sub_menu";

	private OptionManager optionManager = IOC.get("OptionManager");

	private FrontMenuManager frontMenuManager = IOC.get("FrontMenuManager");

	private FrontMenuDetailManager frontMenuDetailManager = IOC.get("FrontMenuDetailManager");

	/**
	 * 通过菜单名称获得菜单
	 * 
	 * @param name
	 *            菜单位置名
	 * @return
	 */
	public FrontMenuBO get(ServletRequest request, String name) {
		OptionBO config = optionManager.getByName(SessionClient.getSaas(request).getId(), name);
		if (config == null || config.getValue() == null || "-1".equals(config.getValue()) || "".equals(config.getValue())) {
			return getDefaultMenu();
		}
		FrontMenuBO bo = frontMenuManager.get(config.getValue());
		if (bo == null) {
			return getDefaultMenu();
		}
		frontMenuDetailManager.fill(bo);
		return bo;
	}

	public FrontMenuBO getById(Object menuId) {
		FrontMenuBO menu = frontMenuManager.get(menuId);
		frontMenuDetailManager.fill(menu);
		return menu;
	}

	private FrontMenuBO getDefaultMenu() {
		FrontMenuBO bo = new FrontMenuBO();
		FrontMenuDetailBO detail = new FrontMenuDetailBO();
		detail.setType("link");
		detail.setName("首页");
		detail.setUrl("/");
		bo.addDetail(detail);
		return bo;
	}

	public List<FrontMenuBO> select(ServletRequest request) {
		FrontMenuBO query = new FrontMenuBO();
		query.setSaasId(SessionClient.getSaas(request).getId());
		query.set_pageSize(100);
		List<FrontMenuBO> list = frontMenuManager.select(query);
		for (FrontMenuBO bo : list) {
			frontMenuDetailManager.fill(bo);
		}
		return list;
	}

}
