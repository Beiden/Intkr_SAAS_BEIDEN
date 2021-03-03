package com.intkr.saas.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.citrus.util.StringUtil;
import com.intkr.saas.module.toolbox.BaseToolBox;

/**
 * 
 * @author Beiden
 * @date 2017-1-26 上午10:31:24
 * @version 1.0
 */
public class RequestUtil extends BaseToolBox {

	protected static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);

	public static boolean existAttr(ServletRequest request, String key) {
		return request.getAttribute(key) != null;
	}

	public static boolean existParam(Object request, String key) {
		if (request instanceof ServletRequest) {
			return existParam((ServletRequest) request, key);
		} else if (request instanceof Map) {
			return existParam((Map<String, Object>) request, key);
		}
		return false;
	}

	public static boolean existParam(ServletRequest request, String key) {
		String value = RequestUtil.getParam(request, key);
		return !StringUtil.isBlank(value);
	}

	public static boolean existParam(Map<String, Object> request, String key) {
		Object value = RequestUtil.getParam(request, key);
		return value != null;
	}

	public static void removeParam(ServletRequest request, String key, String value) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Map<String, String> parameter = (Map<String, String>) httpRequest.getAttribute("_parameter");
		if (parameter == null) {
			return;
		}
		parameter.remove(key);
	}

	public static void setParam(ServletRequest request, String key, String value) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Map<String, String> parameter = (Map<String, String>) httpRequest.getAttribute("_parameter");
		if (parameter == null) {
			parameter = new HashMap<String, String>();
		}
		parameter.put(key, value);
		httpRequest.setAttribute("_parameter", parameter);
	}

	public static <C> C getParam(Object request, String key, C obj) {
		if (request instanceof ServletRequest) {
			return getParam((ServletRequest) request, key, obj);
		} else if (request instanceof Map) {
			return getParam((Map<String, Object>) request, key, obj);
		}
		return null;
	}

	public static <C> C getParam(ServletRequest request, String key, C obj) {
		C value = (C) getParam(request, key, obj.getClass());
		if (value == null) {
			return obj;
		}
		return value;
	}

	public static String getParam(Object request, String key) {
		if (request instanceof ServletRequest) {
			return getParam((ServletRequest) request, key);
		} else if (request instanceof Map) {
			return getParam((Map<String, Object>) request, key);
		}
		return null;
	}

	public static List<String> getParams(ServletRequest request, String key, String split) {
		if (!existParam(request, key)) {
			return new ArrayList<String>();
		}
		String params = getParam(request, key);
		List<String> returnParams = new ArrayList<String>();
		if (params.contains(split)) {
			String[] datas = params.split(split);
			for (String data : datas) {
				returnParams.add(data);
			}
		} else {
			returnParams.add(params);
		}
		return returnParams;
	}

	public static String getParam(ServletRequest request, String key) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Map<String, String> parameter = (Map<String, String>) httpRequest.getAttribute("_parameter");
		if (parameter != null && parameter.containsKey(key)) {
			return parameter.get(key);
		}
		String value = request.getParameter(key);
		return value;
	}

	public static String getParam(Map<String, Object> request, String key) {
		if (request != null && request.containsKey(key)) {
			Object obj = request.get(key);
			if (obj instanceof String) {
				return (String) request.get(key);
			} else {
				return obj.toString();
			}
		}
		String value = (String) request.get(key);
		return value;
	}

	public static <C> C getParam(ServletRequest request, String key, Class<C> claz, C c) {
		C tc = getParam(request, key, claz);
		if (tc == null) {
			return c;
		}
		return tc;
	}

	public static <C> C getParam(Object request, String key, Class<C> claz) {
		if (request instanceof ServletRequest) {
			return getParam((ServletRequest) request, key, claz);
		} else if (request instanceof Map) {
			return getParam((Map<String, Object>) request, key, claz);
		}
		return null;
	}

	public static <C> C getParam(ServletRequest request, String key, Class<C> claz) {
		if (existParam(request, key)) {
			try {
				if (claz.equals(Long.class)) {
					return (C) Long.valueOf(getParam(request, key));
				} else if (claz.equals(Integer.class)) {
					return (C) Integer.valueOf(getParam(request, key));
				} else if (claz.equals(Double.class)) {
					return (C) Double.valueOf(getParam(request, key));
				} else if (claz.equals(Float.class)) {
					return (C) Float.valueOf(getParam(request, key));
				} else if (claz.equals(String.class)) {
					return (C) getParam(request, key);
				} else if (claz.equals(Boolean.class)) {
					return (C) Boolean.valueOf(getParam(request, key));
				} else if (claz.equals(Date.class)) {
					return (C) DateUtil.parse(getParam(request, key));
				} else {
					return null;
				}
			} catch (NumberFormatException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	public static <C> C getParam(Map<String, Object> request, String key, Class<C> claz) {
		if (existParam(request, key)) {
			try {
				if (claz.equals(Long.class)) {
					C c = (C) getParam(request, key);
					if (c instanceof Long) {
						return c;
					} else if (c instanceof Number) {
						Long l = ((Number) c).longValue();
						return (C) l;
					} else if (c instanceof String) {
						Long l = Double.valueOf(c.toString()).longValue();
						return (C) l;
					}
					return null;
				} else if (claz.equals(Integer.class)) {
					return (C) getParam(request, key);
				} else if (claz.equals(Double.class)) {
					return (C) getParam(request, key);
				} else if (claz.equals(Float.class)) {
					return (C) getParam(request, key);
				} else if (claz.equals(String.class)) {
					return (C) getParam(request, key);
				} else if (claz.equals(Boolean.class)) {
					return (C) getParam(request, key);
				} else if (claz.equals(Date.class)) {
					return (C) getParam(request, key);
				} else {
					return null;
				}
			} catch (NumberFormatException e) {
				logger.error("", e);
				return null;
			}
		} else {
			return null;
		}
	}

	public static String getGoUrl(HttpServletRequest request) {
		String goUrl = request.getRequestURI();
		if (goUrl.contains("-param-") || goUrl.contains("-p-")) {
			String parameterString = null;
			if (goUrl.contains("-param-")) {
				parameterString = goUrl.substring(goUrl.indexOf("-param-") + 7, goUrl.length() - 5);
			} else {
				parameterString = goUrl.substring(goUrl.indexOf("-p-") + 3, goUrl.length() - 5);
			}
			if (goUrl.contains("-param-")) {
				goUrl = goUrl.substring(0, goUrl.indexOf("-param-")) + ".html";
			} else {
				goUrl = goUrl.substring(0, goUrl.indexOf("-p-")) + ".html";
			}
		}
		return goUrl;
	}

	public static Map<String, String> getUrlParameter(HttpServletRequest request) {
		Map<String, String> parameter = null;
		String goUrl = request.getRequestURI();
		if (goUrl.contains("-param-") || goUrl.contains("-p-")) {
			String parameterString = null;
			if (goUrl.contains("-param-")) {
				parameterString = goUrl.substring(goUrl.indexOf("-param-") + 7, goUrl.length() - 5);
			} else {
				parameterString = goUrl.substring(goUrl.indexOf("-p-") + 3, goUrl.length() - 5);
			}
			String[] parameters = parameterString.split("-");
			for (int i = 0; i < parameters.length - 1; i += 2) {
				if (parameter == null) {
					parameter = new HashMap<String, String>();
				}
				if (parameters[i] != null && !"".equals(parameters[i]) && parameters[i + 1] != null && !"".equals(parameters[i + 1])) {
					parameter.put(parameters[i], parameters[i + 1]);
				}
			}
		}
		if (parameter == null) {
			return Collections.EMPTY_MAP;
		}
		return parameter;
	}

	public static Map<String, String> getExtParameter(HttpServletRequest request) {
		Map<String, String> parameter = (Map<String, String>) request.getAttribute("_parameter");
		String goUrl = request.getRequestURI();
		if (goUrl.contains("-param-") || goUrl.contains("-p-")) {
			String parameterString = null;
			if (goUrl.contains("-param-")) {
				parameterString = goUrl.substring(goUrl.indexOf("-param-") + 7, goUrl.length() - 5);
			} else {
				parameterString = goUrl.substring(goUrl.indexOf("-p-") + 3, goUrl.length() - 5);
			}
			String[] parameters = parameterString.split("-");
			for (int i = 0; i < parameters.length - 1; i += 2) {
				if (parameter == null) {
					parameter = new HashMap<String, String>();
				}
				if (parameters[i] != null && !"".equals(parameters[i]) && parameters[i + 1] != null && !"".equals(parameters[i + 1])) {
					parameter.put(parameters[i], parameters[i + 1]);
				}
			}
		}
		request.setAttribute("_parameter", parameter);
		return parameter;
	}

	public static void forward(HttpServletRequest request, String uri) {
		request.getSession().setAttribute("$_forward", uri);
	}

	public static Map<String, String> getParamMap(HttpServletRequest request) {
		Enumeration<String> keys = request.getParameterNames();
		Map<String, String> paramMap = new HashMap<String, String>();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			paramMap.put(key, request.getParameter(key));
		}
		Map<String, String> parameter = (Map<String, String>) request.getAttribute("_parameter");
		if (parameter != null) {
			paramMap.putAll(parameter);
		}
		return paramMap;
	}

	public static String getDomain(HttpServletRequest request) {
		String domain = request.getHeader("Host");
		if (domain == null || "".equals(domain)) {
			domain = request.getRemoteHost();
		}
		return domain;
	}

	public static boolean isJsonResponse(HttpServletRequest request) {
		return request.getRequestURI().contains("/json/") || request.getRequestURI().contains(".json");
	}

}
