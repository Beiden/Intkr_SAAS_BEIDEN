package com.intkr.saas.engine.spring;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 
 * @author Beiden
 * @date 2017-10-26
 * @version 1.0
 */
public class SpringContext {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	static ApplicationContext context;

	static AtomicBoolean running = new AtomicBoolean(false);

	public SpringContext() {
		if (context != null) {
			return;
		}
		synchronized (this) {
			new SpringContext("spring-config.xml");
		}
	}

	public SpringContext(String confFileName) {
		try {
			if (context != null) {
				return;
			}
			synchronized (this) {
				if (context != null) {
					return;
				}
				if (!running.get()) {
					running.set(true);
				} else {
					logger.error("bean loop init error !");
					return;
				}
				logger.info("sprint start init : spring-config.xml = " + confFileName + "; this = " + this);
				String fileName = null;
				File file = new File(confFileName);
				if (file.exists()) {
					fileName = file.getAbsolutePath();
				} else {
					try {
						file = Resources.getResourceAsFile(confFileName);
						fileName = file.getAbsolutePath();
					} catch (Exception e) {
						logger.error("confFileName=" + confFileName, e);
						throw new RuntimeException(e);
					}
				}
				context = new FileSystemXmlApplicationContext("file:" + fileName);
				logger.info("sprint inited : spring-config.xml = " + confFileName + "; this = " + this);
			}
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}
	}

	public static <T> T getBean(String beanName) {
		try {
			return (T) context.getBean(beanName);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T getBean(Class<T> claz) {
		return (T) context.getBean(claz);
	}

}
