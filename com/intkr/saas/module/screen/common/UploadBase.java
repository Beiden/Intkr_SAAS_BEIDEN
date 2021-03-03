package com.intkr.saas.module.screen.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.parser.ParserRequestContext;
import com.alibaba.citrus.turbine.Context;
import com.intkr.saas.conf.SystemProperties;
import com.intkr.saas.domain.type.fs.MediaType;
import com.intkr.saas.module.action.fs.BaseMediaUploadAction;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.FileUtil;
import com.intkr.saas.util.RequestUtil;

/**
 * 
 * @author Beiden
 * @date 2017-1-25 下午3:50:58
 * @version 1.0
 */
public class UploadBase {

	protected final Logger logger = LoggerFactory.getLogger(BaseMediaUploadAction.class);

	@Autowired
	protected ParserRequestContext parserContext;

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;

	protected UploadConf conf = new UploadConf();
	{
		conf.setUploadPath("/_upload/file/");
		conf.setMediaType(MediaType.File.getCode());
	}

	protected Media upload(HttpServletRequest request) {
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
				if (saveToDisk(request, item, uri)) {
					Media media = new Media();
					media.setType(getConf().getMediaType());
					media.setName(mediaName);
					media.setRealname(realName);
					media.setUri(uri);
					request.setAttribute("file", media);
					return media;
				}
			}
			return null;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	private boolean saveToDisk(HttpServletRequest request, FileItem item, String uri) throws Exception {
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

	private String getUri(String realName) {
		String yearDate = DateUtil.format("yyyy/MM/", new Date());
		return getConf().getUploadPath() + yearDate + realName;
	}

	private String getOldFileName(FileItem item) {
		return item.getName().toLowerCase();
	}

	public UploadConf getConf() {
		return conf;
	}

	public void setConf(UploadConf conf) {
		this.conf = conf;
	}

	public Object execute(Context context) throws Exception {
		upload(request);
		return processReturn();
	}

	protected Map<String, Object> processReturn() {
		Map<String, Object> jsonResultMap = new HashMap<String, Object>();
		if (RequestUtil.existAttr(request, "file")) {
			request.setAttribute("result", true);
			request.setAttribute("msg", "上传成功");
			jsonResultMap.put("file", request.getAttribute("file"));
		} else {
			request.setAttribute("result", false);
			request.setAttribute("msg", "上传失败");
		}
		if (RequestUtil.existAttr(request, "result")) {
			jsonResultMap.put("result", request.getAttribute("result"));
		} else {
			jsonResultMap.put("result", false);
			jsonResultMap.put("msg", "系统异常！");
		}
		if (RequestUtil.existAttr(request, "msg")) {
			jsonResultMap.put("msg", request.getAttribute("msg"));
		}
		return jsonResultMap;
	}
	
	public static void main(String[] args) {
		String yearDate = DateUtil.format("yyyy/MM/", new Date());
		System.out.println(yearDate);
	}

	public static class Media {
		private String type;
		private String name;
		private String realname;
		private String uri;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getRealname() {
			return realname;
		}

		public void setRealname(String realname) {
			this.realname = realname;
		}

		public String getUri() {
			return uri;
		}

		public void setUri(String uri) {
			this.uri = uri;
		}

	}

}
