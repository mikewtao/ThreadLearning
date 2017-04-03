package com.mike.ThreadLearning.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * @author scott
 * @date 2017年4月3日
 * @desc 模拟类似jcache缓存系统
 */
public class CacheDemo {
	private Map<String, Object> cacheMap = new HashMap<String, Object>();
	private ReadWriteLock rwl = new ReentrantReadWriteLock();

	public Object getData(String key) {
		Object obj = null;
		// 先上读锁，不让写的线程进入
		rwl.readLock().lock();
		try {
			//获取数据
			obj = cacheMap.get(key);
			if (obj == null) {
				//释放读锁
				rwl.readLock().unlock();
				//挂上写锁 ，不让读、写的线程进入
				rwl.writeLock().lock();
				try {
					if (obj == null) {
						obj = "aa";
					}
				} finally {
					//释放写锁
					rwl.writeLock().unlock();
				}
				//挂上读锁
				rwl.readLock().lock();
			}
		} finally {
			//释放读锁
			rwl.readLock().unlock();
		}
		return obj;
	}
}
