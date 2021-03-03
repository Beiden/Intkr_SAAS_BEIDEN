package com.intkr.saas.manager.user.impl;

import java.util.List;

import javax.annotation.Resource;

import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import org.springframework.stereotype.Repository;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.user.WxAccountDAO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.bo.user.WxAccountBO;
import com.intkr.saas.domain.dbo.user.WxAccountDO;
import com.intkr.saas.facade.wx.UserFacade;
import com.intkr.saas.facade.wxpay.WXPayUtil;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.user.RoleManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.manager.user.WxAccountManager;

/**
 * 
 * @author Beiden
 * @date 2016-5-3 下午10:15:48
 * @version 1.0
 */
@Repository("WxAccountManager")
public class WxAccountManagerImpl extends BaseManagerImpl<WxAccountBO, WxAccountDO> implements WxAccountManager {

	@Resource
	private WxAccountDAO wxAccountDAO;

	@Resource
	private UserManager userManager;

	// @Resource
	// private AuthManager authManager;

	@Resource
	private RoleManager roleManager;

	public BaseDAO<WxAccountDO> getBaseDAO() {
		return wxAccountDAO;
	}

	public WxAccountBO getByOpenId(Long saasId, String openId) {
		if (openId == null || "".equals(openId)) {
			return null;
		}
		WxAccountBO query = new WxAccountBO();
		query.setSaasId(saasId);
		query.setOpenId(openId);
		return selectOne(query);
	}

	public WxAccountBO getByUserId(Long userId) {
		if (!IdEngine.isValidate(userId)) {
			return null;
		}
		WxAccountBO query = new WxAccountBO();
		query.setUserId(userId);
		query.set_pageSize(1);
		List<WxAccountBO> list = select(query);
		if (list == null || list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public WxAccountBO insert(UserBO user, WxMpOAuth2AccessToken accessToken) {
		try {
			WxAccountBO query = new WxAccountBO();
			query.setOpenId(accessToken.getOpenId());
			WxAccountBO wxAccountSelect = selectOne(query);
			if (wxAccountSelect != null) {
				return wxAccountSelect;
			}
			if (accessToken.getScope().equalsIgnoreCase("snsapi_base")) {
				WxAccountBO wxAccount = new WxAccountBO();
				wxAccount.setUserId(user.getId());
				wxAccount.setSaasId(user.getSaasId());
				wxAccount.setId(getId());
				wxAccount.setNickName(WXPayUtil.generateNonceStr());
				wxAccount.setOpenId(accessToken.getOpenId());
				insert(wxAccount);
				return wxAccount;
			} else {
				WxMpUser wxMpUser = UserFacade.userInfo(accessToken.getOpenId());
				WxAccountBO wxAccount = new WxAccountBO();
				wxAccount.setUserId(user.getId());
				wxAccount.setSaasId(user.getSaasId());
				wxAccount.setId(getId());
				wxAccount.setNickName(wxMpUser.getNickname());
				wxAccount.setHeadImgUrl(wxMpUser.getHeadImgUrl());
				wxAccount.setOpenId(wxMpUser.getOpenId());
				wxAccount.setSexId(wxMpUser.getSexId());
				wxAccount.setSex(wxMpUser.getSex());
				wxAccount.setCity(wxMpUser.getCity());
				wxAccount.setProvince(wxMpUser.getProvince());
				wxAccount.setCountry(wxMpUser.getCountry());
				wxAccount.setUnionId(wxMpUser.getUnionId());
				wxAccount.setRemark(wxMpUser.getRemark());
				wxAccount.setGroupId(wxMpUser.getGroupId());
				wxAccount.setSubscribe(wxMpUser.getSubscribe() ? 1 : 2);
				wxAccount.setSubscribeTime(wxMpUser.getSubscribeTime());
				wxAccount.setLanguage(wxMpUser.getLanguage());
				insert(wxAccount);
				return wxAccount;
			}
		} catch (Exception e) {
			throw new RuntimeException("", e);
		}
	}

}
