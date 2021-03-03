package com.intkr.saas.valve.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.manager.log.LogManager;
import com.intkr.saas.manager.log.impl.LogManagerImpl;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.UserAgentUtil;
import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.domain.bo.log.LogBO;
import com.intkr.saas.valve.Valve;

/**
 * 手机网站分离
 * 
 * @author Beiden
 * @date 2011-7-3 上午9:07:20
 * @version 1.0
 */
public class PhoneValve implements Valve {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private LogManager logManager = IOC.get(LogManagerImpl.class);

	private List<String> includeUrl = new ArrayList<String>();
	{
		includeUrl.add("^/m/.*");
	};

	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		if (!UserAgentUtil.isPhone(request.getHeader("User-Agent"))) {
			return true;
		}
		if (match(request)) {
			return true;
		}
		if (RequestUtil.existParam(request, "back_to_pc")) {
			request.getSession().setAttribute("back_to_pc", true);
		}
		if (request.getSession().getAttribute("back_to_pc") != null) {
			return true;
		}
		log(request);
		try {
			response.sendRedirect("/m/index.html");
			return false;
		} catch (IOException e) {
			logger.error("", e);
			return true;
		}
	}

	private void log(HttpServletRequest request) {
		LogBO log = new LogBO();
		log.setId(logManager.getId());
		log.setGroupName("userAgent");
		log.setContent(request.getHeader("User-Agent"));
		logManager.insert(log);
	}

	private boolean match(HttpServletRequest request) {
		for (String urlPartern : includeUrl) {
			Pattern p = Pattern.compile(urlPartern);
			Matcher m = p.matcher(request.getRequestURI());
			if (m.find()) {
				return true;
			}
		}
		return false;
	}

}
