package com.intkr.saas.distributed.mq.rocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * 
 * @author Beiden
 * @date 2018-10-4
 * @version 1.0
 */
public class SyncProducerImpl {

	public static void main(String[] args) {
		SyncProducerImpl producer = new SyncProducerImpl();
		producer.init();
		Message msg = new Message("TopicTest",// topic
				"TagB",// tag
				"OrderID002",// key
				("Hello MetaQ2").getBytes());// body
		SendResult sendResult = producer.send(msg);
		System.out.println(sendResult);
	}

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private String nameSrvAddr = "112.74.127.47:9876";

	private String name = "consumer1";

	private DefaultMQProducer producer;

	public void init() {
		try {
			// 声明并初始化一个producer
			// 需要一个producer group名字作为构造方法的参数，这里为producer1
			producer = new DefaultMQProducer(name);

			// 设置NameServer地址,此处应改为实际NameServer地址，多个地址之间用；分隔
			// NameServer的地址必须有，但是也可以通过环境变量的方式设置，不一定非得写死在代码里
			producer.setNamesrvAddr(nameSrvAddr);

			// 调用start()方法启动一个producer实例
			producer.start();
		} catch (MQClientException e) {
			logger.error("", e);
		}
	}

	/**
	 * 调用producer的send()方法发送消息 这里调用的是同步的方式，所以会有返回结果
	 * 
	 * @param message
	 * @return
	 */
	public SendResult send(Message message) {
		try {
			SendResult sendResult = producer.send(message);
			return sendResult;
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 发送完消息之后，调用shutdown()方法关闭producer
	 */
	public void close() {
		producer.shutdown();
	}

}
