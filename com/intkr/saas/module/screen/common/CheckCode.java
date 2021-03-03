package com.intkr.saas.module.screen.common;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.buffered.BufferedRequestContext;
import com.alibaba.citrus.util.StringUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.VerifyCodeUtils;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2017-1-25 下午2:37:29
 * @version 1.0
 */
public class CheckCode {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private BufferedRequestContext brc;

	public void execute() throws Exception {
		try {
			if (StringUtil.isBlank(RequestUtil.getParam(request, "type"))) {
				return;
			}
			String type = RequestUtil.getParam(request, "type");
			// 为了节省内存，关闭buffering。
			brc.setBuffering(false);

			// 只要设置了正确的content type，你就可以输出任何文本或二进制的内容：
			// HTML、JSON、JavaScript、JPG、PDF、EXCEL等。
			response.setContentType("image/jpeg");

			OutputStream out = response.getOutputStream();
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "No-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");
			String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
			request.getSession().setAttribute(type, verifyCode);
			VerifyCodeUtils.outputImage(200, 80, response.getOutputStream(), verifyCode);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}
