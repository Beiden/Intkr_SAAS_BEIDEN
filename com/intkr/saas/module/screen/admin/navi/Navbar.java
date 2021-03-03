package com.intkr.saas.module.screen.admin.navi;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.intkr.saas.manager.log.SignLogManager;
import com.intkr.saas.manager.log.impl.SignLogManagerImpl;
import com.intkr.saas.manager.sns.MsgManager;
import com.intkr.saas.manager.sns.impl.MsgManagerImpl;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.log.SignLogBO;
import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.domain.bo.user.UserBO;

/**
 * 
 * @author Beiden
 * @date 2011-4-23 下午5:23:19
 * @version 1.0
 */
public class Navbar {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MsgManager messageManager = IOC.get(MsgManager.class);

	private SignLogManager signLogManager = IOC.get(SignLogManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	public static Cache<Long, List<MsgBO>> emailVerifyCodeCache = CacheBuilder.newBuilder()//
			.expireAfterAccess(1, TimeUnit.MINUTES).//
			maximumSize(1000).//
			build();

	public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getSession().getAttribute("signLogList") == null) {
			UserBO user = SessionClient.getLoginUser(request);
			SignLogBO signLogQuery = new SignLogBO();
			signLogQuery.setUserId(user.getId());
			List<SignLogBO> signLogList = signLogManager.select(signLogQuery);
			request.getSession().setAttribute("signLogList", signLogList);

			final Long userId = user.getId();
			List<MsgBO> sysMsgList = emailVerifyCodeCache.get(userId, new Callable<List<MsgBO>>() {
				public List<MsgBO> call() throws Exception {
					MsgBO msgQuery = new MsgBO();
					msgQuery.setToUserId(userId);
					msgQuery.setIsToUserRead(2);
					List<MsgBO> sysMsgList = messageManager.select(msgQuery);
					userManager.fill(sysMsgList);
					return sysMsgList;
				}
			});

			request.getSession().setAttribute("sysMsgList", sysMsgList);
		}
	}
}
