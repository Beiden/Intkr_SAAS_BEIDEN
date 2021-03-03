package com.intkr.saas.module;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.util.TurbineUtil;

/**
 * 
 * @author Beiden
 * @date 2015-4-29 下午1:30:32
 * @version 1.0
 */
public abstract class BaseScreen {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String method = request.getMethod();

		TurbineRunData runData = TurbineUtil.getTurbineRunData(request);
		String target = runData.getTarget();
		Class screenClass = null;
		String schemaConf = null;
		String schema = null;
		String requestScheam = null;
		String report = null;
		subExecute(request, response);
	}

	public abstract void subExecute(HttpServletRequest request, HttpServletResponse response);

}

class SchemaConf {
	String method;
	String schema;
	String errorHandler;
	String dataFormat;
}
