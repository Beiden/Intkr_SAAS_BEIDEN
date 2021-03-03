package com.intkr.saas.facade.wx;

import me.chanjar.weixin.mp.bean.WxMpCustomMessage;


/**
 * 
 * @author Beiden
 * @date 2016-3-22 下午8:23:41
 * @version 1.0
 */
public class MsgAsyncFacade {

	public static void send(String openid, String content) throws Exception {
		WxMpCustomMessage message = WxMpCustomMessage.TEXT().toUser(openid).content(content).build();
		Config.wxService.customMessageSend(message);
	}

	public static void main(String[] args) throws Exception {
		send("o9DRLwPU-uz72H-2E4-DQ7PbGakI", "Hellow");
	}

}
