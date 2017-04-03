package com.mike.ThreadLearning.Traditional;
/**
 * 
 * @author scott
 * @DESC 线程共享变量
 * 
 */
public class MultiThreadShareData {

	public static void main(String[] args) {
		final ShareData data=new MultiThreadShareData().new ShareData();
		new Thread(new Runnable() {

			@Override
			public void run() {
				data.increment();

			}
		}).start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				data.desrease();

			}
		}).start();

	}

	class ShareData {

		private int j = 100;

		public synchronized void increment() {
			j++;
		}

		public synchronized void desrease() {
			j--;
		}
	}
}
