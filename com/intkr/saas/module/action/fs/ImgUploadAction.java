package com.intkr.saas.module.action.fs;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.parser.ParserRequestContext;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.conf.SystemProperties;
import com.intkr.saas.domain.bo.fs.ImgBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.module.screen.common.UploadConf;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.FileUtil;

/**
 * 图片上传到Web下的目录，生成对象ImgBO
 * 
 * @author Beiden
 * @date 2011-5-15 下午8:01:22
 * @version 1.0
 */
public abstract class ImgUploadAction {

	@Autowired
	private ParserRequestContext parserContext;

	protected final Logger logger = LoggerFactory.getLogger(ImgUploadAction.class);

	private UploadConf conf = new UploadConf();

	public UploadConf getConf() {
		return conf;
	}

	public void setConf(UploadConf conf) {
		this.conf = conf;
	}

	/**
	 * 执行方法
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ImgBO media = upload(request);
		request.setAttribute("data", media);
		request.setAttribute("file", media);
		request.setAttribute("media", media);
		if (media != null) {
			request.setAttribute("result", true);
			request.setAttribute("msg", "上传成功");
		} else {
			request.setAttribute("result", false);
			request.setAttribute("msg", "上传失败");
		}
	}

	/**
	 * 验证是否已登录
	 * 
	 * @param request
	 * @return
	 */
	private boolean verifyIsLogin(HttpServletRequest request) {
		UserBO userBO = SessionClient.getLoginUser(request);
		if (userBO == null) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请登录后再上传文件！");
			return false;
		}
		return true;
	}

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @return
	 */
	private ImgBO upload(HttpServletRequest request) {
		try {
			FileItem[] items = parserContext.getParameters().getFileItems("file");
			List<FileItem> validateItems = new ArrayList<FileItem>();
			for (FileItem mediaItem : items) {
				if (mediaItem.getName() != null && !"".equals(mediaItem.getName())) {
					validateItems.add(mediaItem);
				}
			}
			if (!validateItems.isEmpty()) {
				FileItem item = validateItems.get(0);
				String mediaName = getOldFileName(item);
				String realName = FileUtil.getRandomName(mediaName);
				String uri = getUri(realName);
				if (saveFile(request, item, uri)) {
					ImgBO mediaBO = log(request, item, mediaName, realName, uri);
					return mediaBO;
				}
			}
			return null;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 保存文件
	 * 
	 * @param request
	 * @param item
	 * @param uri
	 * @return
	 * @throws Exception
	 */
	private boolean saveFile(HttpServletRequest request, FileItem item, String uri) throws Exception {
		if (!item.isFormField()) {
			File directory = new File(SystemProperties.getWebappPath() + uri.substring(0, uri.lastIndexOf("/")));
			if (!directory.exists()) {
				directory.mkdirs();
			}
			item.write(new File(SystemProperties.getWebappPath() + uri));
			return true;
		}
		return false;
	}

	/**
	 * 获得文件存到服务器后的URI
	 * 
	 * @param realName
	 * @return
	 */
	private String getUri(String realName) {
		String yearDate = DateUtil.format("yyyy/MM/", new Date());
		return getConf().getUploadPath() + yearDate + realName;
	}

	/**
	 * 获得上传的文件名
	 * 
	 * @param item
	 * @return
	 */
	private String getOldFileName(FileItem item) {
		return item.getName().toLowerCase();
	}

	/**
	 * 
	 * @param request
	 * @param item
	 * @param mediaName
	 * @param realName
	 * @param uri
	 * @return
	 */
	public abstract ImgBO log(HttpServletRequest request, FileItem item, String mediaName, String realName, String uri);

}
