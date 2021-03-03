package com.intkr.saas.timer.job;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.article.ArticleBO;
import com.intkr.saas.domain.type.cms.ArticleStatus;
import com.intkr.saas.manager.cms.article.ArticleManager;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 定时改变发布状态的文章
 * 
 * @author Beiden
 * @date 2013-7-25 下午11:53:23
 * @version 1.0
 */
public class ArticleAutoPublicTask {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void run() {
		OptionManager optionManager = IOC.get(OptionManager.class);
		if (!optionManager.taskOpen()) {
			return;
		}
		logger.warn("start");
		ArticleManager articleManager = IOC.get(ArticleManager.class);
		ArticleBO query = new ArticleBO();
		query.setStatus(ArticleStatus.Timing.getCode());
		query.set_pageSize(1000);
		articleManager.selectAndCount(query);
		while ((query.get_page() - 1) * query.get_pageSize() < query.get_count()) {
			for (Object obj : query.getDatas()) {
				ArticleBO article = (ArticleBO) obj;
				if (article.getPublicTime().before(new Date())) {
					article.setStatus(ArticleStatus.Public.getCode());
					articleManager.update(article);
				}
			}
			query.set_page(query.get_page() + 1);
			articleManager.selectAndCount(query);
		}
		logger.warn("end");
	}

}