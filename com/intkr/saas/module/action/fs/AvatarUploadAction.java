package com.intkr.saas.module.action.fs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.screen.common.UploadConf;
import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.fs.MediaBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.type.fs.MediaType;

/**
 * 
 * @author Beiden
 * @date 2011-5-14 下午6:16:51
 * @version 1.0
 */
public class AvatarUploadAction extends BaseMediaUploadAction {

	private UploadConf conf = new UploadConf();
	{
		conf.setUploadPath("/_upload/img/userCustomAvatar/");
		conf.setMediaType(MediaType.Img.getCode());
	}

	public UploadConf getConf() {
		return conf;
	}

	public void setConf(UploadConf conf) {
		this.conf = conf;
	}

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserManager userManager = IOC.get("UserManager");

	public void doExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doExecute(request, response);
		if (request.getAttribute("file") != null) {
			MediaBO fileBO = (MediaBO) request.getAttribute("file");
			UserBO userBO = SessionClient.getLoginUser(request);
			UserBO user = userManager.get(userBO.getId());
			user.setAvatar(fileBO.getUri());
			userManager.update(user);
			SessionClient.login(request, response, user.getId());
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "上传成功！");
	}

}
