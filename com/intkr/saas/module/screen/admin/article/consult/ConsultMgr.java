package com.intkr.saas.module.screen.admin.article.consult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.consult.ConsultBO;
import com.intkr.saas.domain.bo.consult.ConsultTypeBO;
import com.intkr.saas.domain.type.article.ConsultStatus;
import com.intkr.saas.manager.cms.consult.ConsultManager;
import com.intkr.saas.manager.cms.consult.ConsultTypeManager;
import com.intkr.saas.module.action.article.consult.ConsultAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class ConsultMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ConsultManager manager = IOC.get(ConsultManager.class);

	private ConsultTypeManager typeManager = IOC.get(ConsultTypeManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ConsultTypeBO typeQuery = new ConsultTypeBO();
		typeQuery.set_pageSize(1000);
		request.setAttribute("typeList", typeManager.select(typeQuery));

		ConsultBO query = ConsultAction.getParameter(request);
		query.setQuery("orderBy", "gmt_created");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		request.setAttribute("ConsultStatus", ConsultStatus.Error);
	}

}
