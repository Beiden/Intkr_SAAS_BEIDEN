package com.intkr.saas.facade.wx;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;

/**
 * 
 * @author Beiden
 * @date 2016-3-23 下午7:56:49
 * @version 1.0
 */
public class UserFacade {

	public static WxMpUser userInfo(String openid) throws Exception {
		String lang = "zh_CN"; // 语言
		WxMpUser user = Config.wxService.userInfo(openid, lang);
		return user;
	}

	public static WxMpUserList userList(String next_openid) throws Exception {
		WxMpUserList wxUserList = Config.wxService.userList(next_openid);
		return wxUserList;
	}

	public static long userGetGroup(String openid) throws Exception {
		long groupid = Config.wxService.userGetGroup(openid);
		return groupid;
	}

	public static void userUpdateGroup(String openid, long to_groupid) throws Exception {
		Config.wxService.userUpdateGroup(openid, to_groupid);
	}

	public static void userUpdateRemark(String openid, String name) throws Exception {
		Config.wxService.userUpdateRemark(openid, name);
	}

	public static void main(String[] args) throws Exception {
		WxMpUser user = userInfo("o9DRLwPU-uz72H-2E4-DQ7PbGakI");
		System.out.println(user);
	}

}
