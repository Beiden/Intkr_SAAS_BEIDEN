package com.intkr.saas.distributed.conf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.engine.SmsEngine;

/**
 * 
 * @author Beiden
 * @date 2019-1-17
 * @version 1.0
 */
public class DistributedLock implements Lock, Watcher {

	protected static final Logger logger = LoggerFactory.getLogger(SmsEngine.class);

	private ZooKeeper zk = null;

	// 根节点
	private String ROOT_LOCK = "/locks";

	// 竞争的资源
	private String lockName;

	// 等待的前一个锁
	private String WAIT_LOCK;

	// 当前锁
	private String CURRENT_LOCK;

	// 计数器
	private CountDownLatch countDownLatch;
	private int sessionTimeout = 30000;
	private List<Exception> exceptionList = new ArrayList<Exception>();

	/**
	 * 配置分布式锁
	 * 
	 * @param config
	 *            连接的url
	 * @param lockName
	 *            竞争资源
	 */
	public DistributedLock(String config, String lockName) {
		if (lockName.contains("/")) {
			this.lockName = lockName.substring(lockName.lastIndexOf("/") + 1);
			this.ROOT_LOCK = lockName.substring(0, lockName.lastIndexOf("/"));
		} else {
			this.lockName = lockName;
			this.ROOT_LOCK = "";
		}
		try {
			// 连接zookeeper
			zk = new ZooKeeper(config, sessionTimeout, this);
			Stat stat = zk.exists(ROOT_LOCK, false);
			if (stat == null) {
				// 如果根节点不存在，则创建根节点
				zk.create(ROOT_LOCK, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
		} catch (Exception e) {
			logger.error("config=" + config + ";lockName=" + lockName, e);
			throw new RuntimeException(e);
		}
	}

	// 节点监视器
	public void process(WatchedEvent event) {
		if (this.countDownLatch != null) {
			this.countDownLatch.countDown();
		}
	}

	public void lock() {
		if (exceptionList.size() > 0) {
			throw new RuntimeException(exceptionList.get(0));
		}
		try {
			if (this.tryLock()) {
				logger.warn(Thread.currentThread().getName() + " " + lockName + "获得了锁");
				return;
			} else {
				// 等待锁
				waitForLock(WAIT_LOCK, sessionTimeout);
			}
		} catch (Exception e) {
			logger.error("lockName=" + lockName);
		}
	}

	public boolean tryLock() {
		try {
			String splitStr = "_lock_";
			if (lockName.contains(splitStr)) {
				throw new RuntimeException("锁名有误");
			}
			// 创建临时有序节点
			CURRENT_LOCK = zk.create(ROOT_LOCK + "/" + lockName + splitStr, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
			logger.warn(CURRENT_LOCK + " 已经创建");
			// 取所有子节点
			List<String> subNodes = zk.getChildren(ROOT_LOCK, false);
			// 取出所有lockName的锁
			List<String> lockObjects = new ArrayList<String>();
			for (String node : subNodes) {
				String _node = node.split(splitStr)[0];
				if (_node.equals(lockName)) {
					lockObjects.add(node);
				}
			}
			Collections.sort(lockObjects);
			logger.warn(Thread.currentThread().getName() + " 的锁是 " + CURRENT_LOCK);
			// 若当前节点为最小节点，则获取锁成功
			if (CURRENT_LOCK.equals(ROOT_LOCK + "/" + lockObjects.get(0))) {
				return true;
			}

			// 若不是最小节点，则找到自己的前一个节点
			String prevNode = CURRENT_LOCK.substring(CURRENT_LOCK.lastIndexOf("/") + 1);
			WAIT_LOCK = lockObjects.get(Collections.binarySearch(lockObjects, prevNode) - 1);
		} catch (Exception e) {
			logger.error("lockName=" + lockName);
		}
		return false;
	}

	public boolean tryLock(long timeout, TimeUnit unit) {
		try {
			if (this.tryLock()) {
				return true;
			}
			return waitForLock(WAIT_LOCK, timeout);
		} catch (Exception e) {
			logger.error("lockName=" + lockName);
		}
		return false;
	}

	// 等待锁
	private boolean waitForLock(String prev, long waitTime) throws KeeperException, InterruptedException {
		Stat stat = zk.exists(ROOT_LOCK + "/" + prev, true);
		if (stat != null) {
			logger.warn(Thread.currentThread().getName() + "等待锁 " + ROOT_LOCK + "/" + prev);
			this.countDownLatch = new CountDownLatch(1);
			// 计数等待，若等到前一个节点消失，则precess中进行countDown，停止等待，获取锁
			this.countDownLatch.await(waitTime, TimeUnit.MILLISECONDS);
			this.countDownLatch = null;
			logger.warn(Thread.currentThread().getName() + " 等到了锁");
		}
		return true;
	}

	public void unlock() {
		try {
			logger.warn("释放锁 " + CURRENT_LOCK);
			zk.delete(CURRENT_LOCK, -1);
			CURRENT_LOCK = null;
			zk.close();
		} catch (Exception e) {
			logger.error("lockName=" + lockName);
			throw new RuntimeException(e);
		}
	}

	public Condition newCondition() {
		return null;
	}

	public void lockInterruptibly() throws InterruptedException {
		this.lock();
	}

	public static void main(String[] args) {
		test();
	}

	private static void test() {
		Runnable runnable = new Runnable() {
			public void run() {
				DistributedLock lock = null;
				try {
					lock = new DistributedLock("127.0.0.1:2181", "test1");
					lock.lock();
					System.out.println(--n);
					System.out.println(Thread.currentThread().getName() + "正在运行");
				} finally {
					if (lock != null) {
						lock.unlock();
					}
				}
			}
		};

		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(runnable);
			t.start();
		}
	}

	static int n = 500;

}
