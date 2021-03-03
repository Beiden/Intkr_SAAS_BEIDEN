package com.intkr.saas.distributed.mq.client;

/**
 * 
 * @author Beiden
 * @date 2018-10-4
 * @version 1.0
 */
public interface Producer {

	/**
	 * 同步发送
	 * 
	 * @param message
	 * @return
	 */
	public Result send(Message message);

	/**
	 * 异步发送
	 * 
	 * @param callBack
	 * @return
	 */
	public void sendAsyn(CallBack callBack);

	// 发送的消息
	public class Message {

	}

	// 发送的结果
	public class Result {

	}

	// 回调函数
	public static interface CallBack {

	}

}
