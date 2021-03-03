package com.intkr.saas.module.screen.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.citrus.turbine.Context;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.fs.MediaBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.manager.fs.MediaManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2017-1-25 下午3:50:58
 * @version 1.0
 */
public class Upload extends UploadBase {

	protected MediaManager mediaManager = IOC.get(MediaManager.class);

	public Object execute(Context context) throws Exception {
		Media media = upload(request);
		MediaBO mediaNew = saveToDb(request, media);
		request.setAttribute("file", mediaNew);
		return processReturn();
	}

	private MediaBO saveToDb(HttpServletRequest request, Media mediaParam) {
		if (mediaParam == null) {
			return null;
		}
		MediaBO media = new MediaBO();
		media.setType(getConf().getMediaType());
		media.setId(mediaManager.getId());
		media.setName(mediaParam.getName());
		media.setRealname(mediaParam.getRealname());
		media.setCategoryId(RequestUtil.getParam(request, "categoryId", Long.class));
		media.setUri(mediaParam.getUri());
		UserBO userBO = SessionClient.getLoginUser(request);
		media.setUserId(userBO.getId());
		mediaManager.insert(media);
		media.setName(null);
		List<MediaBO> list = mediaManager.select(media);
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

}
