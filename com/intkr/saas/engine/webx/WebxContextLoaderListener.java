package com.intkr.saas.engine.webx;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;

import com.alibaba.citrus.webx.context.WebxComponentsContext;
import com.alibaba.citrus.webx.context.WebxComponentsLoader;
import com.intkr.saas.conf.SystemProperties;
import com.intkr.saas.engine.UrlRewriteEngine;
import com.intkr.saas.engine.spring.SpringContext;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2019-1-15
 * @version 1.0
 */
public class WebxContextLoaderListener extends ContextLoaderListener {

	protected static final Logger logger = LoggerFactory.getLogger(SystemProperties.class);

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sct = sce.getServletContext();
		String SpringConfig = sct.getInitParameter("spring-config");
		if (SpringConfig != null && !"".equals(SpringConfig)) {
			WebContext.contextParam.put("spring-config", SpringConfig);
			new SpringContext(SpringConfig);
		}
		initObj(sct);
		String authorityValve = sct.getInitParameter("authority-valve");
		if (authorityValve == null) {
			authorityValve = "";
		}
		WebContext.contextParam.put("authorityValve", authorityValve);
		super.contextInitialized(sce);
		logger.info("SystemProperties.init();");
		SystemProperties.init();
		logger.info("webapp init finished !");
	}

	private void initObj(ServletContext sct) {
		String initObj = sct.getInitParameter("init-obj");
		if (initObj != null && !"".equals(initObj)) {
			WebContext.contextParam.put("init-obj", initObj);
			IOC.get(initObj);
		}
	}

	protected final ContextLoader createContextLoader() {
		return new WebxComponentsLoader() {
			protected Class<? extends WebxComponentsContext> getDefaultContextClass() {
				Class<? extends WebxComponentsContext> defaultContextClass = WebxContextLoaderListener.this.getDefaultContextClass();

				if (defaultContextClass == null) {
					defaultContextClass = super.getDefaultContextClass();
				}

				return defaultContextClass;
			}
		};
	}

	protected Class<? extends WebxComponentsContext> getDefaultContextClass() {
		return null;
	}

}