package com.mike.ThreadLearning.Traditional;

/**
 * 
 * @author scott
 * @description 子线程循环10次，主线程循环100，如此循环50次
 */

public class TwoThreadComunication {
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

		public synchronized void main(int j) {
			while (!isMain) {
                try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int i = 0; i < 100; i++) {
				System.out.println("main thread loop:" + i + "; at" + j + "st loop");
			}
			isMain=false;
			this.notify();
		}

		public synchronized void sub(int j) {
			while (isMain) {
                try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int i = 0; i < 10; i++) {
				System.out.println("sub thread loop:" + i + "; at" + j + "st loop");
			}
			isMain=true;
			this.notify();
		}
	}

}
