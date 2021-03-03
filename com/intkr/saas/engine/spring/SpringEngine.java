package com.intkr.saas.engine.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Beiden
 * @date 2017-10-27
 * @version 1.0
 */
public class SpringEngine {

	protected static final Logger logger = LoggerFactory.getLogger(SpringEngine.class);

	private SpringEngine() {
	}

	public static <T> T getBean(String beanName) {
		init();
		return (T) SpringContext.getBean(beanName);
	}

	public static <T> T getBean(Class<T> claz) {
		init();
		return (T) SpringContext.getBean(claz);
	}

	private static void init() {
		if (SpringContext.context == null) {
			new SpringContext();
		}
	}

}
