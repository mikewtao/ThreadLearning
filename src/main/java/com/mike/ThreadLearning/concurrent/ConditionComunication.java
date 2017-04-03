package com.mike.ThreadLearning.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author scott
 * @description 子线程循环10次，主线程循环100，如此循环50次 ---用condition、lock实现同步互斥
 */

public class ConditionComunication {
	final static Business bus = new Business();

	public static void main(String[] args) {

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					bus.sub(i);
				}

			}
		}).start();

		for (int i = 0; i < 50; i++) {
			bus.main(i);
		}

	}

	static class Business {
		private boolean isMain = true;
		private Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();

		public synchronized void main(int j) {
			lock.lock();
			try {
				while (!isMain) {
					try {
						//this.wait();
						condition.await();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int i = 0; i < 100; i++) {
					System.out.println("main thread loop:" + i + "; at" + j + "st loop");
				}
				isMain = false;
				//this.notify();
				condition.signal();
			} finally {
               lock.unlock();
			}

		}

		public synchronized void sub(int j) {
			lock.lock();
			try{
				while (isMain) {
					try {
						condition.await();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int i = 0; i < 10; i++) {
					System.out.println("sub thread loop:" + i + "; at" + j + "st loop");
				}
				isMain = true;
				//this.notify();
				condition.signal();
			}finally {
				lock.unlock();
			}
			
		}
	}

}
