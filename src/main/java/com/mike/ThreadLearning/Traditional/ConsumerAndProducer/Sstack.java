package com.mike.ThreadLearning.Traditional.ConsumerAndProducer;
/**
 * 
 * @author scott
 *
 */
public class Sstack {

	private String[] str = new String[6];

	private int index = 0;

	public synchronized void push(String st) {
		while (index == 6) {
			try {
				this.wait();// 释放对象锁,当前线程进入等待状态
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.notify();//通知其他等待的线程
		str[index] = st;
		this.index++;
	}

	public synchronized String pop() {
		while (index == 0) {
			try {
				this.wait();// 释放对象锁
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.notify();
		this.index--;
		return str[index];
	}

}
