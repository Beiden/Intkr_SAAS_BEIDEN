package com.intkr.saas.util.dever;

import java.io.InputStream;
import java.io.StringWriter;

import org.apache.ibatis.io.Resources;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.intkr.saas.util.FileUtil;

/**
 * 
 * @author Beiden
 * @date 2017-11-18
 * @version 1.0
 */
public class TemplateEngine {

	public static String getContent(String vmFile) {
		try {
			InputStream is = Resources.getResourceAsStream(vmFile);
			String content = FileUtil.getContent(is);
			is.close();
			return content;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String merge(String vmFile, VelocityContext context) {
		try {
			InputStream is = Resources.getResourceAsStream(vmFile);
			String content = FileUtil.getContent(is);
			is.close();
			return mergeContent(content, context);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String mergeContent(String content, VelocityContext context) {
		VelocityEngine ve = new VelocityEngine();
		ve.init();
		StringWriter writer = new StringWriter();
		ve.evaluate(context, writer, "", content);
		return writer.toString();
	}

}
