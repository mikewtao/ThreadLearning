package com.mike.ThreadLearning.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

	public static void main(String[] args) throws InterruptedException {
		final Lock lock=new ReentrantLock();
		lock.lock();
		Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					lock.lockInterruptibly();
				} catch (InterruptedException e) {
					System.out.println(" interrupted");
				}
			}
		});
		t1.start();
		t1.interrupt();
		Thread.sleep(10);
       
	}

}
