package com.mike.ThreadLearning.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheDemo {
	private Map<String, Object> cacheMap = new HashMap<String, Object>();
	private ReadWriteLock rwl = new ReentrantReadWriteLock();

	public Object getData(String key) {
		Object obj = null;
		// 先上读锁，不让写的线程进入
		rwl.readLock().lock();
		try {
			obj = cacheMap.get(key);
			if (obj == null) {
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try {
					if (obj == null) {
						obj = "aa";
					}
				} finally {
					rwl.writeLock().unlock();
				}
				rwl.readLock().lock();
			}
		} finally {
			rwl.readLock().unlock();
		}
		return obj;
	}
}
