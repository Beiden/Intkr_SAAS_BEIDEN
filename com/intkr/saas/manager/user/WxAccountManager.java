package com.intkr.saas.manager.user;

import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.bo.user.WxAccountBO;
import com.intkr.saas.domain.dbo.user.WxAccountDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2016-5-3 下午10:15:08
 * @version 1.0
 */
public interface WxAccountManager extends BaseManager<WxAccountBO, WxAccountDO> {

	public WxAccountBO getByOpenId(Long saasId ,String openId);

	public WxAccountBO getByUserId(Long userId);

	public WxAccountBO insert(UserBO user, WxMpOAuth2AccessToken accessToken);

}
