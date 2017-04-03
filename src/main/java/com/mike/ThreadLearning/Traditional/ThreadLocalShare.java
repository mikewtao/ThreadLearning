package com.mike.ThreadLearning.Traditional;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * @author scott
 * @desc 不同模块：线程内共享，线程外独立  
 *        每个请求（线程）范围内变量是同步的
 *
 */
public class ThreadLocalShare {
	private static int data = 0;
	private static ThreadLocal<Integer> local = new ThreadLocal<Integer>();

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					synchronized (ThreadLocalShare.class) {
						data = ThreadLocalRandom.current().nextInt();
						local.set(data);
						System.out.println(Thread.currentThread().getName() + " put: " + data);
					}
					new A().get();
					new B().get();
				}
			}).start();
		}

	}

	static class A {
		public void get() {
			System.out.println("A get data from :" + Thread.currentThread().getName() + " " + local.get());
		}
	}

	static class B {
		public void get() {
			System.out.println("B get data from :" + Thread.currentThread().getName() + " " + local.get());
		}
	}
}
