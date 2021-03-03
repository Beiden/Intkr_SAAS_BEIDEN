package com.intkr.saas.module.action.fs;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.fs.RelationshipManager;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.fs.MediaRelationshipBO;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class MediaRelationshipAction extends BaseAction<MediaRelationshipBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private RelationshipManager relationshipManager = IOC.get(RelationshipManager.class);

	public MediaRelationshipBO getBO(HttpServletRequest request) {
		MediaRelationshipBO mediaRelationshipBO = getParameter(request);
		return mediaRelationshipBO;
	}

	public static MediaRelationshipBO getParameter(HttpServletRequest request) {
		MediaRelationshipBO mediaRelationshipBO = new MediaRelationshipBO();
		mediaRelationshipBO.setId(RequestUtil.getParam(request, "id", Long.class));
		mediaRelationshipBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		mediaRelationshipBO.setType(RequestUtil.getParam(request, "type"));
		mediaRelationshipBO.setMediaId(RequestUtil.getParam(request, "mediaId" , Long.class));
		mediaRelationshipBO.setRelatedId(RequestUtil.getParam(request, "relatedId" , Long.class));
		mediaRelationshipBO.setSort(RequestUtil.getParam(request, "sort" , Double.class));
		PagerUtil.initPage(request, mediaRelationshipBO);
		return mediaRelationshipBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return relationshipManager;
	}

}
