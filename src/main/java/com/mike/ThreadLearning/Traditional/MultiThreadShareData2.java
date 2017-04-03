package com.mike.ThreadLearning.Traditional;

/**
 * 
 * @author scott
 * @DESC 线程共享变量
 * 设计四个线程，两个线程每次对j加1，另外两个对j减1
 */
public class MultiThreadShareData2 {
	private int j;

	public static void main(String[] args) {
		MultiThreadShareData2 data2=new MultiThreadShareData2();
		increThread inc=data2.new increThread();
		decreThread dec=data2.new decreThread();
        for(int i=0;i<2;i++){
    	   new Thread(inc).start();
    	   new Thread(dec).start();
       }
	}

	private synchronized void incre() {
		j++;
		System.out.println(Thread.currentThread().getName() + ":" + j);
	}

	private synchronized void decre() {
		j--;
		System.out.println(Thread.currentThread().getName() + ":" + j);
	}

	class increThread implements Runnable {

		@Override
		public void run() {
			for(int i=0;i<100;i++){
				incre();
			}
			

		}

	}

	class decreThread implements Runnable {

		@Override
		public void run() {
			for(int i=0;i<100;i++){
				decre();
			}
			

		}

	}

}
