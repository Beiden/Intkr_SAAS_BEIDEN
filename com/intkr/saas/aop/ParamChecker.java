package com.intkr.saas.aop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.LogLevel;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.intkr.saas.conf.SystemProperties;
import com.intkr.saas.util.FileUtil;
import com.intkr.saas.util.JsonUtil;

/**
 * 
 * @author Beiden
 */
public class ParamChecker {

	static final Logger logger = LoggerFactory.getLogger(ParamChecker.class);

	public static boolean check(ServletRequest request, String jsonSchema) {
		try {
			if (!jsonSchema.startsWith("jsonSchema/")) {
				jsonSchema = "jsonSchema/" + jsonSchema;
			}
			List<String> returnMsg = new ArrayList<String>();
			Map<String, Object> params = getParams(request);
			String json = JsonUtil.toJson(params);
			JsonSchema schema = getJsonSchema(jsonSchema);
			JsonNode dataNode = JsonLoader.fromString(json);
			ProcessingReport processingReport = schema.validate(dataNode);
			Iterator<ProcessingMessage> iterator = processingReport.iterator();
			while (iterator.hasNext()) {
				ProcessingMessage msg = iterator.next();
				LogLevel level = msg.getLogLevel();
				if (LogLevel.ERROR.equals(level)) {
					System.out.println(msg.getMessage());
					returnMsg.add(msg.getMessage());
				}
			}
			if (!returnMsg.isEmpty()) {
				request.setAttribute("msg", returnMsg.toString());
				request.setAttribute("result", false);
				return false;
			}
			return true;
		} catch (Exception e) {
			logger.error("", e);
			request.setAttribute("msg", "系统异常");
			request.setAttribute("result", false);
			return false;
		}
	}

	private static Map<String, Object> getParams(ServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		for (Object nameObj : request.getParameterMap().keySet()) {
			String name = (String) nameObj;
			String[] value = request.getParameterValues(name);
			if (value == null || value.length == 0) {
				continue;
			} else if (value.length == 1) {
				params.put(name, value[0]);
			} else {
				params.put(name, value);
			}
		}
		return params;
	}

	public static void main(String[] args) throws Exception {
		JsonSchema schema = getJsonSchema("jsonSchema/test.json");
		// 获得处理的报告信息
		String json = getContent("jsonSchema/test.value");
		JsonNode dataNode = JsonLoader.fromString(json);
		ProcessingReport processingReport = schema.validate(dataNode);
		Iterator<ProcessingMessage> iterator = processingReport.iterator();
		while (iterator.hasNext()) {
			ProcessingMessage msg = iterator.next();
			msg.getMessage();
		}
		// 获取完整报告信息
		System.out.println(processingReport);
		// 判断校验是否成功，如果为true表示成功，否则失败
		System.out.println(processingReport.isSuccess());
	}

	private static JsonSchema getJsonSchema(String fileName) {
		try {
			String jsonSchema = getContent(fileName);
			JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
			JsonNode schemaNode = JsonLoader.fromString(jsonSchema);
			JsonSchema schema = factory.getJsonSchema(schemaNode);
			return schema;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	private static String getContent(String fileName) {
		InputStream in = null;
		try {
			in = Resources.getResourceAsStream(fileName);
		} catch (Exception e) {
			try {
				in = new FileInputStream(new File(SystemProperties.getWebappPath() + "/WEB-INF/classes/" + fileName));
			} catch (FileNotFoundException e1) {
				throw new RuntimeException(e1);
			}
		}
		return FileUtil.getContent(in);
	}

}
