package com.mike.ThreadLearning.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author scott
 * @description 子线程循环10次，主线程循环100，如此循环50次 ---用condition、lock实现同步互斥
 */

public class ThreeConditionComunication {
	final static Business bus = new Business();

	public static void main(String[] args) {

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					bus.sub2(i);
				}

			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					bus.sub3(i);
				}

			}
		}).start();
		
		for (int i = 0; i < 50; i++) {
			bus.main(i);
		}

	}

	static class Business {
		private int shouldSub = 1;
		Lock lock = new ReentrantLock();
		Condition condition1 = lock.newCondition();
		Condition condition2 = lock.newCondition();
		Condition condition3 = lock.newCondition();
		public void main(int j) {
			lock.lock();
			try {
				while (shouldSub!=1) {
					try {
						//this.wait();
						condition1.await();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int i = 0; i < 100; i++) {
					System.out.println("main thread loop:" + i + "; at" + j + "st loop");
				}
				//this.notify();
				shouldSub=2;
				condition2.signal();
			} finally {
               lock.unlock();
			}

		}

		public  void sub2(int j) {
			lock.lock();
			try{
				while (shouldSub!=2) {
					try {
						condition2.await();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int i = 0; i < 10; i++) {
					System.out.println("sub2 thread loop:" + i + "; at" + j + "st loop");
				}
				shouldSub=3;
				condition3.signal();
			}finally {
				lock.unlock();
			}
			
		}
		public void sub3(int j) {
			lock.lock();
			try{
				while (shouldSub!=3) {
					try {
						condition3.await();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int i = 0; i < 20; i++) {
					System.out.println("sub3 thread loop:" + i + "; at" + j + "st loop");
				}
				shouldSub=1;
				condition1.signal();
			}finally {
				lock.unlock();
			}
			
		}
	}

}
