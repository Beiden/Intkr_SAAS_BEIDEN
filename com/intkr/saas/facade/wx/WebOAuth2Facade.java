package com.intkr.saas.facade.wx;

import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * 
 * @author Beiden
 * @date 2016-3-22 下午9:00:04
 * @version 1.0
 */
public class WebOAuth2Facade {

	public static String getUrl(String redirectURI, String scope, String state) {
		String url = Config.wxService.oauth2buildAuthorizationUrl(redirectURI, scope, state);
		return url;
	}

	public static WxMpOAuth2AccessToken getAccessToken(String code) throws Exception {
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken = Config.wxService.oauth2getAccessToken(code);
		return wxMpOAuth2AccessToken;
	}

	public static WxMpUser getUserInfo(WxMpOAuth2AccessToken wxMpOAuth2AccessToken) throws Exception {
		WxMpUser wxMpUser = Config.wxService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
		return wxMpUser;
	}

	public static boolean isValid(WxMpOAuth2AccessToken wxMpOAuth2AccessToken) {
		return Config.wxService.oauth2validateAccessToken(wxMpOAuth2AccessToken);
	}

	public static WxMpOAuth2AccessToken refreshAccessToken(WxMpOAuth2AccessToken wxMpOAuth2AccessToken) throws Exception {
		wxMpOAuth2AccessToken = Config.wxService.oauth2refreshAccessToken(wxMpOAuth2AccessToken.getRefreshToken());
		return wxMpOAuth2AccessToken;
	}

	public static void main(String[] args) throws Exception {
		// https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx6887c749b105fcd8&redirect_uri=http%3A%2F%2Fwww.intkr.com&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect
		// https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx6887c749b105fcd8&redirect_uri=http%3A%2F%2Fwww.intkr.com&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect
//		String url = getUrl("http://bkw.intkr.com", WxConsts.OAUTH2_SCOPE_BASE, "");
//		System.out.println(url);
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken = getAccessToken("011Pe87m0hzGeg1OLd7m0Bw97m0Pe87H");
		System.out.println(wxMpOAuth2AccessToken);
	}

}
