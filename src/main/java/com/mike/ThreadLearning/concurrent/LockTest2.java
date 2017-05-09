package com.mike.ThreadLearning.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author scott
 * @date 2017年4月3日
 * @desc reetrantlock测试
 */
public class LockTest2 {

	public static void main(String[] args) {
		Business bus=new Business();
		new Thread(new Runnable() {
			@Override
			public void run() {
				bus.output("scottwt");
			}
		}).start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				bus.output("mikewt");
			}
		}).start();
	}

	static class Business {
		private Lock lock = new ReentrantLock();

		public void output(String str) {
			try {
				lock.lock();
				for (int i = 0; i < str.length(); i++) {
					System.out.print(str.charAt(i));
				}
			} finally {
				lock.unlock();
			}
		}

	}
}
