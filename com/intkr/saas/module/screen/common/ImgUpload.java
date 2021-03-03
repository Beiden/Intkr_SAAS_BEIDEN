package com.intkr.saas.module.screen.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.intkr.saas.domain.type.fs.MediaType;
import com.intkr.saas.module.action.fs.BaseImgUploadAction;
import com.intkr.saas.util.RequestUtil;

/**
 * 
 * @author Beiden
 * @date 2017-1-25 下午3:50:58
 * @version 1.0
 */
public class ImgUpload extends BaseImgUploadAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	private UploadConf conf = new UploadConf();
	{
		conf.setUploadPath("/_upload/img/");
		conf.setMediaType(MediaType.Img.getCode());
	}

	public UploadConf getConf() {
		return conf;
	}

	public void setConf(UploadConf conf) {
		this.conf = conf;
	}

	public Object execute(Context context) throws Exception {
		doExecute(request, response);
		Map<String, Object> jsonResultMap = new HashMap<String, Object>();
		if (RequestUtil.existAttr(request, "result")) {
			jsonResultMap.put("result", request.getAttribute("result"));
		}
		if (RequestUtil.existAttr(request, "msg")) {
			jsonResultMap.put("msg", request.getAttribute("msg"));
		}
		if (RequestUtil.existAttr(request, "data")) {
			jsonResultMap.put("file", request.getAttribute("data"));
		}
		if (context.containsKey("result")) {
			jsonResultMap.put("result", context.get("result"));
		}
		if (context.containsKey("msg")) {
			jsonResultMap.put("msg", context.get("msg"));
		}
		if (context.containsKey("data")) {
			jsonResultMap.put("data", context.get("data"));
		}
		if (!jsonResultMap.containsKey("result")) {
			jsonResultMap.put("result", false);
			jsonResultMap.put("msg", "系统异常！");
		}
		return jsonResultMap;
	}

}
