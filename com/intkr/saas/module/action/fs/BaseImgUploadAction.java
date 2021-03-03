package com.intkr.saas.module.action.fs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.fs.ImgBO;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 把ImgBO对象存到数据库
 * 
 * @author Beiden
 * @date 2011-5-15 下午8:01:22
 * @version 1.0
 */
public class BaseImgUploadAction extends ImgUploadAction {

	protected ImgManager imgManager = IOC.get(ImgManager.class);

	public ImgBO log(HttpServletRequest request, FileItem item, String mediaName, String realName, String uri) {
		ImgBO img = new ImgBO();
		img.setId(imgManager.getId());
		img.setSaasId(SessionClient.getSaasId(request));
		img.setName(mediaName);
		img.setRealname(realName);
		img.setCategoryId(RequestUtil.getParam(request, "categoryId", Long.class));
		img.setUri(uri);
		Long userId = SessionClient.getLoginUserId(request);
		img.setUserId(userId);
		imgManager.insert(img);
		img.setName(null);
		List<ImgBO> list = imgManager.select(img);
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

}
