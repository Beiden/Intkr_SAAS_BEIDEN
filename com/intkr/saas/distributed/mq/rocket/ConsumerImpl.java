package com.intkr.saas.distributed.mq.rocket;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.intkr.saas.distributed.mq.client.Consumer;

/**
 * 
 * @author Beiden
 * @date 2018-10-4
 * @version 1.0
 */
public class ConsumerImpl implements Consumer {

	public static void main(String[] args) {
		ConsumerImpl consumer = new ConsumerImpl();
		consumer.init();
		System.out.println();
	}

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private String nameSrvAddr = "112.74.127.47:9876";

	private String name = "consumer1";

	private String consumerStrategy;

	private String topic = "TopicTest";

	private String tags = "*";

	private MessageListenerConcurrently callback = new CallbackTest();

	public void init() {
		try {
			checkParam();
			// 声明并初始化一个consumer
			// 需要一个consumer group名字作为构造方法的参数，这里为consumer1
			DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(name);

			// 同样也要设置NameServer地址
			consumer.setNamesrvAddr(nameSrvAddr);

			// 这里设置的是一个consumer的消费策略
			// CONSUME_FROM_LAST_OFFSET 默认策略，从该队列最尾开始消费，即跳过历史消息
			// CONSUME_FROM_FIRST_OFFSET 从队列最开始开始消费，即历史消息（还储存在broker的）全部消费一遍
			// CONSUME_FROM_TIMESTAMP
			// 从某个时间点开始消费，和setConsumeTimestamp()配合使用，默认是半个小时以前
			consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

			// 设置consumer所订阅的Topic和Tag，*代表全部的Tag
			consumer.subscribe(topic, tags);

			// 设置一个Listener，主要进行消息的逻辑处理
			consumer.registerMessageListener(callback);
			// 调用start()方法启动consumer
			consumer.start();
			logger.info("Consumer Started.");
		} catch (MQClientException e) {
			logger.error("", e);
		}
	}

	private void checkParam() {
		if (nameSrvAddr == null || "".equals(nameSrvAddr)) {
			throw new RuntimeException("nameSrvAddr = null");
		}
		if (topic == null || "".equals(topic)) {
			throw new RuntimeException("topic = null");
		}
		if (name == null || "".equals(name)) {
			throw new RuntimeException("name = null");
		}
	}

	public String getNameSrvAddr() {
		return nameSrvAddr;
	}

	public void setNameSrvAddr(String nameSrvAddr) {
		this.nameSrvAddr = nameSrvAddr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConsumerStrategy() {
		return consumerStrategy;
	}

	public void setConsumerStrategy(String consumerStrategy) {
		this.consumerStrategy = consumerStrategy;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public MessageListenerConcurrently getCallback() {
		return callback;
	}

	public void setCallback(MessageListenerConcurrently callback) {
		this.callback = callback;
	}

}

class CallbackTest implements MessageListenerConcurrently {
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
		System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs);
		// 返回消费状态
		// CONSUME_SUCCESS 消费成功
		// RECONSUME_LATER 消费失败，需要稍后重新消费
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}
}