package com.intkr.saas.module.toolbox.theme;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.lang.LangContentBO;
import com.intkr.saas.domain.bo.lang.LangContentTranslateBO;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.lang.LangContentManager;
import com.intkr.saas.manager.lang.LangContentTranslateManager;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-9-20 上午9:50:56
 * @version 1.0
 */
public class LangDS extends BaseToolBox {

	public static Cache<String, LangContentBO> categoryContentCache = CacheBuilder.newBuilder()//
			.expireAfterAccess(30, TimeUnit.MINUTES).//
			maximumSize(10000).//
			build();

	public OptionManager optionManager = IOC.get("OptionManager");

	public LangContentManager langContentManager = IOC.get("LangContentManager");

	public LangContentTranslateManager langContentTranslateManager = IOC.get("LangContentTranslateManager");

	public String t(HttpServletRequest request, String content) {
		return t(request, null, content);
	}

	public String t(HttpServletRequest request, String category, String content) {
		Long saasId = SessionClient.getSaasId(request);
		String langId = optionManager.getValueByName(saasId, "website_lang");
		if (langId == null || "zh_CN".equals(langId)) {
			return content;
		}
		LangContentBO langContent = getLangContent(saasId, category, content);
		LangContentTranslateBO translate = langContent.getLang(langId);
		if (translate != null) {
			return translate.getContent();
		}
		return content;
	}

	private LangContentBO getLangContent(Long saasId, String category, String content) {
		final String key = saasId + "|" + category + "|" + content;
		if (category == null) {
			category = "";
		}
		try {
			LangContentBO langContent = categoryContentCache.get(key, new Callable<LangContentBO>() {
				public LangContentBO call() throws Exception {
					String saasId = key.split("\\|")[0];
					String category = key.split("\\|")[1];
					String content = key.split("\\|")[2];
					LangContentBO query = new LangContentBO();
					query.setCategory(category);
					query.setContent(content);
					LangContentBO contentBO = langContentManager.selectOne(query);
					if (contentBO == null) {
						contentBO = new LangContentBO();
						contentBO.setSaasId(Long.valueOf(saasId));
						contentBO.setLangId("cn");
						contentBO.setId(langContentManager.getId());
						if (category != null && !"".equals(category)) {
							contentBO.setCategory(category);
						}
						contentBO.setContent(content);
						langContentManager.insert(contentBO);
						return contentBO;
					}
					langContentTranslateManager.fill(contentBO);
					return contentBO;
				}
			});
			return langContent;
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}

}
