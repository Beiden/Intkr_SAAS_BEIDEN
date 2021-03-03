package com.intkr.saas.module.toolbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.citrus.service.pull.ToolFactory;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2017-2-26 下午4:53:51
 * @version 1.0
 */
public class BaseToolBox implements ToolFactory{
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public boolean isSingleton() {
		return true;
	}

	public Object createTool() throws Exception {
		return IOC.get(this.getClass());
	}

}
