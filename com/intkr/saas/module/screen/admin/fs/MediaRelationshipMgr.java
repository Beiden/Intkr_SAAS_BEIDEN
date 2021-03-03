package com.intkr.saas.module.screen.admin.fs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.fs.MediaRelationshipBO;
import com.intkr.saas.manager.fs.RelationshipManager;
import com.intkr.saas.module.action.fs.MediaRelationshipAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class MediaRelationshipMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private RelationshipManager relationshipManager = IOC.get(RelationshipManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MediaRelationshipBO query = MediaRelationshipAction.getParameter(request);
		query.setQuery("orderBy", "gmt_created");
		query.setQuery("order", "desc");
		query = relationshipManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
