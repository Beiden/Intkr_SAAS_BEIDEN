package com.intkr.saas.domain.type.sns;

/**
 * 
 * @author Beiden
 * @date 2016-4-24 上午10:02:37
 * @version 1.0
 */
public enum MsgType {

	System("system", "系统"), //
	Order("order", "订单"), //

	Strange("strange", "陌生人"), //
	Friend("friend", "好友"), //
	Group("group", "群"), //
	Chatroom("chatroom", "聊天室"), //

	FriendVerify("friendVerify", "好友验证"), //
	GroupVerify("groupVerify", "群验证"), //

	Chat("chat", "站内消息"), //
	Error("error", "异常"); //

	private MsgType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	private String code;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static MsgType getByCode(String code) {
		MsgType[] values = values();
		for (MsgType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
