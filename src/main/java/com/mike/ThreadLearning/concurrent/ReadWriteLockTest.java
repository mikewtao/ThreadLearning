package com.mike.ThreadLearning.concurrent;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 
 * @author scott
 * @date 2017年4月3日
 * @desc 读写锁：读写互斥 写写互斥 
 */
public class ReadWriteLockTest {

	public static void main(String[] args) {
		Queue queue=new Queue();
	    for(int i=0;i<3;i++){
	    	new Thread(new Runnable() {

				@Override
				public void run() {
					queue.put(new Random().nextInt(10000));
				}
			}).start();
			new Thread(new Runnable() {

				@Override
				public void run() {
					queue.get();
				}
			}).start();
	    }
		
	}

	static class Queue {
		private Object data = null;
		private ReadWriteLock rwl = new ReentrantReadWriteLock();

		public void get() {
			rwl.readLock().lock();
			try {
				System.out.println(Thread.currentThread().getName() + " be ready to get data");
				Thread.sleep((long) (Math.random() * 1000));
				System.out.println(Thread.currentThread().getName() + " have get:" + data);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				rwl.readLock().unlock();
			}
		}

		public void put(Object data) {
			rwl.writeLock().lock();
			try {
				System.out.println(Thread.currentThread().getName() + " be ready to put data");
				Thread.sleep((long) (Math.random() * 1000));
				this.data = data;
				System.out.println(Thread.currentThread().getName() + " have put:" + data);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				rwl.writeLock().unlock();
			}
		}
	}
}
